/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

import java.util.Random;

/**
 *
 * @author Malte
 */
public class GeneticAlgorithm {
    public static void GeneticAlgorithm(int maxGeneration, int generationSize, int[] sequence){
        Population p = new Population(sequence, generationSize);
        p.EvaluateFitness();
        
        while(maxGeneration>0){
            p.FitnessBasedSelection(generationSize);
            p.crossover(0.25);
            p.mutation(0.01);
            p.EvaluateFitness();
            int mutations = 0;
            for(Fold f : p.folds){
                if(f.getChanged()==true){
                    mutations++;
                    f.setChanged(false);
                }
            }
            maxGeneration--;
            System.out.println("Average Fitness: " + p.averageFitness);
            System.out.println("   Best Fitness: " + p.best.getFitness()); 
            System.out.println("      Mutations: " + mutations);
            //System.out.println(p.toString() + " " + mutations);
        }
    }
    
    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
