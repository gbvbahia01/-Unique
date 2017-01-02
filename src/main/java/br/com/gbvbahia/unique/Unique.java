/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.unique;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Do NOT use for auto generated values. Like sequences or auto increment ids.
 * You should not use for nullable fields. The generated query for null fields will be like:
 * Select p From Product where p.field = null.
 * The right query for null fields is something like:
 * Select p From Product where p.field is null.
 * @author Guilherme
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Unique {
    
    String[] fields();
}
