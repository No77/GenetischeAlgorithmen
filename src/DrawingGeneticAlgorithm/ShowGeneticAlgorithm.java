/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DrawingGeneticAlgorithm;

import General.Examples;
import General.Fold;
import General.Population;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Malte
 */
public class ShowGeneticAlgorithm {
    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame frame = new JFrame("Genetic Algorithm");
        DrawFoldPanel panel = new DrawFoldPanel();
        frame.add(panel);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int maxGeneration = 500;
        int generationSize = 50000;
        int[] sequence = Examples.SEQ50bits;
        
        boolean diverity = false;
        Population p;
        int generation = maxGeneration;
        try (FileWriter writer = new FileWriter("C:\\Users\\Malte\\Documents\\Uni\\5.Semester\\GA\\ga.csv")) {
            p = new Population(sequence, generationSize);
            p.EvaluateFitness();
            panel.setFold(p.best);
            while(generation > 0 ){//&& p.best.getFitness() < 0.65){
                p.FitnessBasedSelection(generationSize);
                p.crossover(0.25);
                p.mutation(0.01);
                p.EvaluateFitness();
                panel.setFold(p.best);
                panel.repaint();
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
    }
}
