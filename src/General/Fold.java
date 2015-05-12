package General;


import java.awt.Point;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Malte
 */
public class Fold {
    public int[] sequence;
    //0=S 1=L 2=R
    public int[] directions;
    
    private double fitness = 0;
    
    private boolean changed;
    
    public static int mutation = 0;
    
    public Fold(int[] s, int[] d){
        changed = false;
        sequence = s;
        directions = d;
    }
    
    public Fold(ArrayList<Integer> s, ArrayList<Integer> d){
        this.changed = false;
        int[] newSequence = new int[s.size()];
        for (int i = 0; i < newSequence.length; i++) {
            newSequence[i] = s.get(i);
        }
        sequence = newSequence;
        
        int[] newDirections = new int[d.size()];
        for (int i = 0; i < newSequence.length; i++) {
            newDirections[i] = d.get(i);
        }
        directions = newDirections;
    }
    
    public Fold(String s, String f){
        this.changed = false;
        
        sequence = new int[s.length()];
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '1'){
               sequence[i] = 1;
            }
            if(c == '0'){
               sequence[i]= 0; 
            }
        }
        
        directions = new int[f.length()];
        for(int i = 0; i < f.length(); i++){
            char c = f.charAt(i);
            if(c=='S'){
               directions[i] = 0;
            }
            if(c=='L'){
               directions[i] = 1; 
            }
            if(c=='R'){
               directions[i] = 2; 
            }
        }
    }
    
    public AminoAcid[] getAminoAcids(){
        AminoAcid[] aminos = new AminoAcid[sequence.length];
        Point[] points = getPoints(directions);
        
        for(int i = 0; i < sequence.length; i++){
            aminos[i] = new AminoAcid(sequence[i], points[i]);
        }
        
        return aminos;    }
    
    private Point[] getPoints(int[] d){
        Point[] points = new Point[d.length + 1];
        Point active = new Point(0,0);
        int index = 0;
        points[index] = active;
        index++;
        Point vector = new Point(0,1);
        
        for (int i = 0; i < d.length; i++) {
            vector = getVectorFromDirection(vector, d[i]);
            Point newPoint = new Point(active.x + vector.x, active.y + vector.y);
            points[index] = newPoint;
            index++;
            vector = new Point(newPoint.x - active.x, newPoint.y - active.y);
            active = newPoint;
        }
        return points;
    }
    
    private Point getVectorFromDirection(Point vector, int direction){
        
        switch(direction){
            case 0:
                return vector;
            case 1:
                if(vector.equals(new Point(0,1))){
                    return new Point(-1,0);
                }
                if(vector.equals(new Point(1,0))){
                    return new Point(0,1);
                }
                if(vector.equals(new Point(0,-1))){
                    return new Point(1,0);
                }
                if(vector.equals(new Point(-1,0))){
                    return new Point(0,-1);
                }
            case 2:
                if(vector.equals(new Point(0,1))){
                    return new Point(1,0);
                }
                if(vector.equals(new Point(1,0))){
                    return new Point(0,-1);
                }
                if(vector.equals(new Point(0,-1))){
                    return new Point(-1,0);
                }
                if(vector.equals(new Point(0,-1))){
                    return new Point(0,1);
                }
            default: 
                return new Point(0,1);
        }
    }
    
    public void mutate(double d){
        int percent = (int)(100*d);
        for (int i = 0; i < directions.length; i++) {
            int test = GeneticAlgorithm.randInt(0, 100);
            if(test<percent){
                int new_direction = GeneticAlgorithm.randInt(0, 2);
                directions[i] = new_direction;
                changed = true;
                mutation++;
            }
        }
    }
    
    public void crossover(Fold f){
        //if(!f.equals(this)){
            int max_index = directions.length - 1;
            int index = GeneticAlgorithm.randInt(0, max_index);
            int[] temp_directions = new int[directions.length];

            //vorne ich hinten du
            for (int i = 0; i < index; i++) {
                temp_directions[i] = directions[i];
            }
            for (int i = index; i < directions.length; i++) {
                temp_directions[i] = f.directions[i];
            }
            //vorne du hinten ich
            for (int i = index; i < directions.length; i++) {
                f.directions[i] = directions[i];
            }
            directions = temp_directions;

            changed = true;
            f.setChanged(true);
            mutation++;
        //}
    }
    
    @Override
    public String toString(){
        ArrayList<String> myDirections = new ArrayList<>();
        for (Integer i : directions) {
            switch(i){
                case 0: myDirections.add("S");
                    break;
                case 1: myDirections.add("L");
                    break;
                case 2: myDirections.add("R");
                    break;
            }
        }
        return myDirections.toString() + " " + fitness;
    }
    
    public int getMinimalEnergy(){
        int maxPairs = Fitness.getMaxPairs(sequence);
        
        return (int)(maxPairs*fitness)*-1;
    }
    
    public int[] getSequence(){
        return sequence;
    }
    
    public int[] getDirections(){
        return directions;
    }
    
    public double getFitness(){
        return fitness;
    }
    
    public void setFitness(double fitness){
        this.fitness = fitness;
        changed = true;
    }
    
    public boolean getChanged(){
        return changed;
    }
    
    public void setChanged(boolean changed){
        this.changed = changed;
    }
}
