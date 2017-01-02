/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.unique;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class UniqueParans {

    public static final String QUERY = "QUERY_MAP";

   public Query getJpaQuery(Object uniqueObj, EntityManager manager){
       Map<String, Object> map = getMapQuery(uniqueObj);
       String queryString = map.get(QUERY).toString();
       map.remove(QUERY);
       Query query = manager.createQuery(queryString);
       Set<String> keys = map.keySet();
       for(String key : keys){
           query.setParameter(key, map.get(key));
       }
       return query;
   }
    
    public Map<String, Object> getMapQuery(Object uniqueObj) {
        try {
            UniqueMap modelHasUnique = getObjHasUnique(uniqueObj);
            if (modelHasUnique == null || modelHasUnique.objHasAnnotation == null) {
                return null;
            }
            Map<String, Object> toReturn = new HashMap<>();
            Object objUnique = modelHasUnique.objHasAnnotation;
            Unique u = null;
            for(Annotation annotation :  objUnique.getClass().getDeclaredAnnotations()){
                if(Unique.class.equals(annotation.annotationType())){
                    u = (Unique) annotation;
                }
            }
            String[] fields = u.fields();
            String genericQuery = createGenericQuery(modelHasUnique, fields.length);
            for (int i = 1; i <= fields.length; i++) {
                genericQuery = genericQuery.replace("parameterName" + i, fields[i - 1]);
                genericQuery = genericQuery.replace("parameterValue" + i, fields[i - 1] + "_p");
                Field field = objUnique.getClass().getDeclaredField(fields[i - 1]);
                field.setAccessible(true);
                toReturn.put(fields[i - 1] + "_p", field.get(objUnique));
                field.setAccessible(false);
            }
            toReturn.put(UniqueParans.QUERY, genericQuery);
            return toReturn;
        } catch (Exception e) {
            throw new RuntimeException("UniqueParans: error to consult @Unique", e);
        }
    }

    private String createGenericQuery(UniqueMap uniqueObj, int length) {
        StringBuilder start = new StringBuilder("Select e From " + uniqueObj.evaluated.getClass().getSimpleName() + " e");
        if (!uniqueObj.isField) {
            for (int i = 1; i <= length; i++) {
                if (i == 1) {
                    start.append(" Where e.parameterName" + i + " = :parameterValue" + i);
                } else {
                    start.append(" And e.parameterName" + i + " = :parameterValue" + i);
                }
            }
            return start.toString();
        } else {
            for (int i = 1; i <= length; i++) {
                if (i == 1) {
                    start.append(" Where e."+ uniqueObj.fieldName + ".parameterName" + i + " = :parameterValue" + i);
                } else {
                    start.append(" And e."+ uniqueObj.fieldName + ".parameterName" + i + " = :parameterValue" + i);
                }
            }
            return start.toString();
        }
    }

    private UniqueMap getObjHasUnique(Object uniqueObj) {
        UniqueMap uniqueMap = new UniqueMap();
        uniqueMap.evaluated = uniqueObj;
        try {
            if (uniqueObj.getClass().isAnnotationPresent(Unique.class)) {
                uniqueMap.objHasAnnotation = uniqueObj;
                uniqueMap.isField = false;
            } else {
                Field[] fields = uniqueObj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    boolean accessible = f.isAccessible();
                    f.setAccessible(true);
                    try {
                        if (f.getType().isAnnotationPresent(Unique.class) && f.get(uniqueObj) != null) {
                             uniqueMap.objHasAnnotation = f.get(uniqueObj);
                             uniqueMap.isField = true;
                             uniqueMap.fieldName = f.getName();
                            break;
                        }
                    } finally {
                        f.setAccessible(accessible);
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return uniqueMap;
    }
    
    private class UniqueMap {
        Object evaluated;
        Object objHasAnnotation;
        boolean isField;
        String fieldName;
    }
}
