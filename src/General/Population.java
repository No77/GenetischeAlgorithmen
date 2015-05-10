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
public class Population {
    public Fold[] folds;
    public Fold best = null;
    public double averageFitness = 0.0;
    public double totalFitness = 0.0;
    public int[] sequence;
    
    public Population(int[] aminoSequence){
        sequence = aminoSequence;
    }
    
    public Population(int[] aminoSequence, int populationSize){
        sequence = aminoSequence;
        folds = new Fold[populationSize];
        initializePopulation(populationSize);
    }
    
    public final void initializePopulation(int size){
        for (int i = 0; i < size; i++) {
            folds[i] = new Fold(sequence, generateFold(sequence.length));
        }
    }
    
    private int[] generateFold(int length) {
        int[] myFold = new int[length-1];
        for (int i = 0; i < length-1; i++) {
            myFold[i] = randInt(0,2);
        }
        return myFold;
    }
    
    private int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    public void EvaluateFitness() {
        double avFitness = 0;
        double bestFitness = 0;
        
        for(int i = 0; i < folds.length; i++){
            double fitness = Fitness.Fitness(folds[i]);
            folds[i].setFitness(fitness);
            avFitness += folds[i].getFitness();
            if(folds[i].getFitness() > bestFitness){
                best = folds[i];
            }
        }
        
        totalFitness = avFitness;
        averageFitness = avFitness/folds.length;
    }
    
    public void FitnessBasedSelection(int size) {
        Fold[] newPopulation = new Fold[size];
        int n = folds.length;
        double max_weight = 0.0;
	double [] weight = new double [n];
        
        //Gewichtung für jede Faltung berechnen
        for (int i = 0; i < weight.length; i++) {
            double w = folds[i].getFitness()/totalFitness;
            if(w > max_weight){
                max_weight = w;
            }
            weight[i] = w;
        }
        
        //zähler initialisieren
	int  [] counter = new int[n];
        //anzahl auszuwaehlender elemente
	int toSelect = size;
	int index = 0;
	boolean notaccepted;
	for (int i=0; i< toSelect; i++){
		notaccepted = true;
		while (notaccepted){
			index= (int)(n*Math.random());
			if(Math.random() < weight[index] / max_weight) {
                            notaccepted = false;
                        }
		}
		counter[index]++;
        }
        //population mit neuer groesse
        int index_2 = 0;
        for (int i = 0; i < counter.length; i++) {
            if(counter[i]>0){
                for (int j = 0; j < counter[i]; j++) {
                    newPopulation[index_2] = new Fold(folds[i].sequence, folds[i].directions);
                    index_2++;
                }
            }
        }
        folds = newPopulation;
    }

    void crossover(double d) {
        int percent = (int)(100*d);
        for (int i = 0; i < folds.length; i++) {
            int test = randInt(0, 100);
            if(test<percent){
                int index = randInt(0, folds.length-1);
                folds[i].crossover(folds[index]);
            }
        }
    }

    void mutation(double d) {
        for (Fold fold : folds) {
            fold.mutate(d);
        }
    }
    
    @Override
    public String toString() {
        String output = "";
        for(Fold f : folds){
            output += f.toString() + '\n';
        }
        return output;
    }
}
