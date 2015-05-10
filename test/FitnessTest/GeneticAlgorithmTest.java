package FitnessTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import General.Examples;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import General.GeneticAlgorithm;

/**
 *
 * @author Malte
 */
//@RunWith(Suite.class)
//@Suite.SuiteClasses({})
public class GeneticAlgorithmTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testPopulation() {
        //GeneticAlgorithm.GeneticAlgorithm(50, 1000, Examples.SEQ20bits);
        GeneticAlgorithm.GeneticAlgorithm(500, 20000, Examples.SEQ20bits);
    }
}
