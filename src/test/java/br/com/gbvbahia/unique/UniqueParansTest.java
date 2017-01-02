/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.unique;

import br.com.gbvbahia.dao.model.UniqueIdTest;
import br.com.gbvbahia.dao.model.UniqueManyTest;
import br.com.gbvbahia.dao.model.UniqueTest;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme
 */
public class UniqueParansTest {

    public UniqueParansTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getQueryUnique method, of class UniqueParans.
     */
    @org.junit.Test
    public void testGetQueryUnique() {
        System.out.println("getQueryUnique");
        String expected_field1 = "f1";
        Integer expected_field2 = 2;
        String queryExpected = "Select e From UniqueTest e Where e.field2 = :field2_p And e.field1 = :field1_p";
        Object model = new UniqueTest();
        UniqueParans instance = new UniqueParans();
        Map<String, Object> query = instance.getMapQuery(model);
        assertEquals(expected_field1, query.get("field1_p"));
        assertEquals(expected_field2, query.get("field2_p"));
        assertEquals(queryExpected, query.get(UniqueParans.QUERY).toString().trim());

    }

    @org.junit.Test
    public void testGetQueryUniqueMany() {
        System.out.println("getQueryUniqueMany");
        Long expected_one = 1L;
        Double expected_three = 3.0;
        float expected_four = 4.0F;
        String expected_six = "6";
        String queryExpected = "Select e From UniqueManyTest e Where e.one = :one_p And e.three = :three_p And e.four = :four_p And e.six = :six_p";
        Object model = new UniqueManyTest();
        UniqueParans instance = new UniqueParans();
        Map<String, Object> query = instance.getMapQuery(model);
        assertEquals(expected_one, query.get("one_p"));
        assertEquals(expected_three, query.get("three_p"));
        assertEquals(expected_four, query.get("four_p"));
        assertEquals(expected_six, query.get("six_p"));
        assertEquals(queryExpected, query.get(UniqueParans.QUERY).toString().trim());
    }

    @Test
    public void testGetQueryUniqueFromId() {
        System.out.println("testGetQueryUniqueFromId");
        Long expected_idOne = 1L;
        long expected_idTwo = 2L;
        String queryExpected = "Select e From UniqueIdTest e Where e.id.idOne = :idOne_p And e.id.idTwo = :idTwo_p";
        Object model = new UniqueIdTest();
        UniqueParans instance = new UniqueParans();
        Map<String, Object> query = instance.getMapQuery(model);
        assertEquals(expected_idOne, query.get("idOne_p"));
        assertEquals(expected_idTwo, query.get("idTwo_p"));
        assertEquals(queryExpected, query.get(UniqueParans.QUERY).toString().trim());
    }
}
