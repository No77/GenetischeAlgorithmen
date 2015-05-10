package FitnessTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import General.Fitness;
import General.Fold;
import General.HydrophobicPair;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Malte
 */
//@RunWith(Suite.class)
//@Suite.SuiteClasses({})
public class JUnit4TestSuite {

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
    
    
    /**
     * BLA
     */
    @Test
    public void testFoldStringConstructor() {
        System.out.println("Fold: StringConstructor");
        int[] sequence = new int[10];
        sequence[0] = 1;
        sequence[1] = 0;
        sequence[2] = 1;
        sequence[3] = 0;
        sequence[4] = 0;
        sequence[5] = 1;
        sequence[6] = 1;
        sequence[7] = 0;
        sequence[8] = 1;
        sequence[9] = 0;
        int[] directions = new int[9];
        directions[0] = 0;
        directions[1] = 2;
        directions[2] = 0;
        directions[3] = 2;
        directions[4] = 2;
        directions[5] = 1;
        directions[6] = 0;
        directions[7] = 1;
        directions[8] = 2;
        
        String s = "1010011010";
        String d = "SRSRRLSLR";
        
        Fold myFold = new Fold(s,d);
        Assert.assertArrayEquals(sequence,myFold.getSequence());
        Assert.assertArrayEquals(directions,myFold.getDirections());
    }
    
    /**
     * BLA
     */
    @Test
    public void testFitness() {
        System.out.println("FitnessFirst");
        
        String s = "1010011010";
        //String d = "SRSRRLSLR";
        String d = "SSSSSSSSS";
        
        
        Double result = Fitness.Fitness(new Fold(s,d));
        Double expected = 0.0;
        
        assertEquals(expected,result);
    }
    
    @Test
    public void testPairEquals() {
        System.out.println("Equals");
        List<HydrophobicPair> myPairs = new ArrayList<>();
        
        HydrophobicPair pair1 = new HydrophobicPair(new Point(1,0), new Point(0,1));
        HydrophobicPair pair2 = new HydrophobicPair(new Point(0,1), new Point(1,0));
        
        myPairs.add(pair1);
        myPairs.add(pair2);
        
        assertTrue(myPairs.contains(pair1));
    }
    
    
}
