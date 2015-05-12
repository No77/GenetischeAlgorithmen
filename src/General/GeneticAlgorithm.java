/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Malte
 */
public class GeneticAlgorithm {
    public static Fold GeneticAlgorithm(int maxGeneration, int generationSize, int[] sequence) throws IOException{
        boolean diverity = true;
        Population p;
        int generation = maxGeneration;
        try (FileWriter writer = new FileWriter("C:\\Users\\Malte\\Documents\\Uni\\5.Semester\\GA\\ga.csv")) {
            p = new Population(sequence, generationSize);
            p.EvaluateFitness();
            //
            while(generation > 0 ){//&& p.best.getFitness() < 0.65){
                p.FitnessBasedSelection(generationSize);
                p.crossover(0.25);
                p.mutation(0.01);
                p.EvaluateFitness();
                if(diverity){
                    p.geneticDiversity();
                }
                int mutations = Fold.mutation;
                generation--;
                
                String test = ("" + p.averageFitness).replace(".", ",");
                writer.append(test);
                writer.append(";");
                test = ("" + p.best.getFitness()).replace(".", ",");
                writer.append(test);
                writer.append('\n');
                
                System.out.println("Average Fitness: " + p.averageFitness);
                System.out.println("   Best Fitness: " + p.best.getFitness());
                System.out.println("         Energy: " + p.best.getMinimalEnergy());
                System.out.println("      Mutations: " + mutations);
                System.out.println("     Generation: " + (maxGeneration-generation));
                System.out.println("     Diversität: " + p.diversity);
                Fold.mutation = 0;
            }   
            System.out.println(p.best.toString() + p.best.directions.length);
            writer.flush();
        }
        return p.best;
    }
    
    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
