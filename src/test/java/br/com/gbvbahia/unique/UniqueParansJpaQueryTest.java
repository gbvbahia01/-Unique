/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.unique;

import br.com.gbvbahia.dao.DaoEntityManager;
import br.com.gbvbahia.dao.model.UniqueTest;
import javax.persistence.Query;
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
public class UniqueParansJpaQueryTest {

    private DaoEntityManager daoManager;

    public UniqueParansJpaQueryTest() {
    }

    @Before
    public void setUp() {
        daoManager = new DaoEntityManager();
        daoManager.getEntityManager();
    }

    @After
    public void tearDown() {
        daoManager.getEntityManager().close();
    }

    @Test
    public void testJpaQuery() throws Exception {
        System.out.println("getJpaQuery");
        UniqueTest ut = new UniqueTest();
        daoManager.create(ut);
        UniqueParans uniqueParans = new UniqueParans();
        Query query = uniqueParans.getJpaQuery(ut, daoManager.getEntityManager());
        UniqueTest utSearched = (UniqueTest) query.getSingleResult();
        assertNotNull(utSearched);
        assertEquals(utSearched.getField1(), ut.getField1());
        assertEquals(utSearched.getField2(), ut.getField2());
    }
}
