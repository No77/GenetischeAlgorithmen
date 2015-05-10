package General;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Malte
 */
public class Fitness {
    public static double Fitness(Fold f){
        int[] sequence = f.sequence;
        
        int[] directions = f.directions;
        
        Point[] points = getPoints2(directions);

        AminoAcid[] aminos = getAminoAcids2(sequence,points);
        
        double first = FitnessFirst2(aminos);
        
        double second = FitnessSecond2(points);
        
        int maxPairs = getMaxPairs2(sequence);
        
        return first/second/maxPairs;
    }
    
    private static int getMaxPairs(Fold f){
        double maxPairs;
        int even = 0;
        int odd = 0;
        int min;
        
        for(int i = 0; i < f.sequence.length; i++){
            if(i%2 == 0 && f.sequence[i] == 1){
                even++;
            }
            if(i%2 != 0 && f.sequence[i] == 1){
                odd++;
            }
        }
        
        if(even<odd){
            min = even;
        }else{
            min = odd;
        }
        
        return (2*min)+2;
    }
    
    private static int getMaxPairs2(int[] sequence){
        double maxPairs;
        int even = 0;
        int odd = 0;
        int min;
        
        for(int i = 0; i < sequence.length; i++){
            if(i%2 == 0){
                if (sequence[i] == 1) {
                    even++;
                }
            }
            if(i%2 != 0){
                if (sequence[i] == 1) {
                    odd++;
                }
            }
        }
        
        if(even <= odd){
            min = even;
        }else{
            min = odd;
        }
        
        return (2*min)+2;
    }
    
    private static AminoAcid[] getAminoAcids2(int[] sequence, Point[] points){
        AminoAcid[] aminos = new AminoAcid[sequence.length];
        
        for(int i = 0; i < sequence.length; i++){
            aminos[i] = new AminoAcid(sequence[i], points[i]);
        }
        
        return aminos;
    }
    
    private static List<AminoAcid> getAminoAcids(Fold f, List<Point> points){
        List<AminoAcid> aminos = new ArrayList<>();
        
        for(int i = 0; i < f.sequence.length; i++){
            aminos.add(new AminoAcid(f.sequence[i], points.get(i)));
        }
        
        return aminos;
    }
    
    //tested
    private static List<Point> getPoints(List<Integer> d){
        List<Point> points = new ArrayList<>();
        Point active = new Point(0,0);
        points.add(active);
        Point vector = new Point(0,1);
        
        for(int direction : d){
            vector = getVectorFromDirection(vector, direction);
            Point newPoint = new Point(active.x + vector.x, active.y + vector.y);
            points.add(newPoint);
            vector = new Point(newPoint.x - active.x,newPoint.y - active.y);
            active = newPoint;
        }
        return points;
    }
    
    private static Point[] getPoints2(int[] d){
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
    
    private static double FitnessFirst(List<AminoAcid> aminos){
        //entfernung vom optimalwert nach oben und unten berücksichtigen
        
        int count = 0;
        Point up;
        Point down;
        Point left;
        Point right;
        
        List<HydrophobicPair> pairs = new ArrayList<>();
        
        for(int i = 0; i < aminos.size();i++){
            Point activePoint = aminos.get(i).coordinate;
            Integer activeInt = aminos.get(i).hydrophobe;
            
                if(activeInt == 1){
                    if(i == 0){
                        Point exception = aminos.get(i+1).coordinate;
                        up = new Point(activePoint.x, activePoint.y + 1);
                        List<AminoAcid> ups = getPossiblePairs(up, exception, aminos);
                        down = new Point(activePoint.x, activePoint.y -1);
                        List<AminoAcid> downs = getPossiblePairs(down, exception, aminos);
                        left = new Point(activePoint.x -1, activePoint.y);
                        List<AminoAcid> lefts = getPossiblePairs(left, exception, aminos);
                        right = new Point(activePoint.x + 1, activePoint.y);
                        List<AminoAcid> rights = getPossiblePairs(right, exception, aminos);

                        for(AminoAcid aa : ups){
                            HydrophobicPair hp = new HydrophobicPair(up, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : downs){
                            HydrophobicPair hp = new HydrophobicPair(down, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : lefts){
                            HydrophobicPair hp = new HydrophobicPair(left, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : rights){
                            HydrophobicPair hp = new HydrophobicPair(right, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                    }else if(i == aminos.size()-1){
                        Point exception = aminos.get(i-1).coordinate;
                        up = new Point(activePoint.x, activePoint.y + 1);
                        List<AminoAcid> ups = getPossiblePairs(up, exception, aminos);
                        down = new Point(activePoint.x, activePoint.y -1);
                        List<AminoAcid> downs = getPossiblePairs(down, exception, aminos);
                        left = new Point(activePoint.x -1, activePoint.y);
                        List<AminoAcid> lefts = getPossiblePairs(left, exception, aminos);
                        right = new Point(activePoint.x + 1, activePoint.y);
                        List<AminoAcid> rights = getPossiblePairs(right, exception, aminos);

                        for(AminoAcid aa : ups){
                            HydrophobicPair hp = new HydrophobicPair(up, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : downs){
                            HydrophobicPair hp = new HydrophobicPair(down, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : lefts){
                            HydrophobicPair hp = new HydrophobicPair(left, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : rights){
                            HydrophobicPair hp = new HydrophobicPair(right, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                    }else{
                    Point exception1 = aminos.get(i+1).coordinate;
                    Point exception2 = aminos.get(i-1).coordinate;
                    up = new Point(activePoint.x, activePoint.y + 1);
                    List<AminoAcid> ups = getPossiblePairsTwo(up, exception1, exception2, aminos);
                    down = new Point(activePoint.x, activePoint.y -1);
                    List<AminoAcid> downs = getPossiblePairsTwo(down, exception1, exception2, aminos);
                    left = new Point(activePoint.x -1, activePoint.y);
                    List<AminoAcid> lefts = getPossiblePairsTwo(left, exception1, exception2, aminos);
                    right = new Point(activePoint.x + 1, activePoint.y);
                    List<AminoAcid> rights = getPossiblePairsTwo(right, exception1, exception2, aminos);
                    
                    for(AminoAcid aa : ups){
                        HydrophobicPair hp = new HydrophobicPair(up, activePoint);
                        
                        if(!containsPair(pairs, hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                    for(AminoAcid aa : downs){
                        HydrophobicPair hp = new HydrophobicPair(down, activePoint);
                        if(!containsPair(pairs, hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                    for(AminoAcid aa : lefts){
                        HydrophobicPair hp = new HydrophobicPair(left, activePoint);
                        if(!pairs.contains(hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                    for(AminoAcid aa : rights){
                        HydrophobicPair hp = new HydrophobicPair(right, activePoint);
                        if(!pairs.contains(hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                }
            }   
        }
        return count;
    }
    
    private static double FitnessFirst2(AminoAcid[] aminos){
        //entfernung vom optimalwert nach oben und unten berücksichtigen
        
        int count = 0;
        Point up;
        Point down;
        Point left;
        Point right;
        
        List<HydrophobicPair> pairs = new ArrayList<>();
        
        for(int i = 0; i < aminos.length; i++){
            Point activePoint = aminos[i].coordinate;
            Integer activeInt = aminos[i].hydrophobe;
            
                if(activeInt == 1){
                    if(i == 0){
                        Point exception = aminos[i+1].coordinate;
                        up = new Point(activePoint.x, activePoint.y + 1);
                        List<AminoAcid> ups = getPossiblePairs2_1(up, exception, aminos);
                        down = new Point(activePoint.x, activePoint.y -1);
                        List<AminoAcid> downs = getPossiblePairs2_1(down, exception, aminos);
                        left = new Point(activePoint.x -1, activePoint.y);
                        List<AminoAcid> lefts = getPossiblePairs2_1(left, exception, aminos);
                        right = new Point(activePoint.x + 1, activePoint.y);
                        List<AminoAcid> rights = getPossiblePairs2_1(right, exception, aminos);

                        for(AminoAcid aa : ups){
                            HydrophobicPair hp = new HydrophobicPair(up, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : downs){
                            HydrophobicPair hp = new HydrophobicPair(down, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : lefts){
                            HydrophobicPair hp = new HydrophobicPair(left, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : rights){
                            HydrophobicPair hp = new HydrophobicPair(right, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                    }else if(i == aminos.length - 1){
                        Point exception = aminos[i-1].coordinate;
                        up = new Point(activePoint.x, activePoint.y + 1);
                        List<AminoAcid> ups = getPossiblePairs2_1(up, exception, aminos);
                        down = new Point(activePoint.x, activePoint.y -1);
                        List<AminoAcid> downs = getPossiblePairs2_1(down, exception, aminos);
                        left = new Point(activePoint.x -1, activePoint.y);
                        List<AminoAcid> lefts = getPossiblePairs2_1(left, exception, aminos);
                        right = new Point(activePoint.x + 1, activePoint.y);
                        List<AminoAcid> rights = getPossiblePairs2_1(right, exception, aminos);

                        for(AminoAcid aa : ups){
                            HydrophobicPair hp = new HydrophobicPair(up, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : downs){
                            HydrophobicPair hp = new HydrophobicPair(down, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : lefts){
                            HydrophobicPair hp = new HydrophobicPair(left, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                        for(AminoAcid aa : rights){
                            HydrophobicPair hp = new HydrophobicPair(right, activePoint);
                            if(!pairs.contains(hp)){
                                pairs.add(hp);
                                count++;
                            }
                        }
                    }else{
                    Point exception1 = aminos[i+1].coordinate;
                    Point exception2 = aminos[i-1].coordinate;
                    up = new Point(activePoint.x, activePoint.y + 1);
                    List<AminoAcid> ups = getPossiblePairs2_2(up, exception1, exception2, aminos);
                    down = new Point(activePoint.x, activePoint.y -1);
                    List<AminoAcid> downs = getPossiblePairs2_2(down, exception1, exception2, aminos);
                    left = new Point(activePoint.x -1, activePoint.y);
                    List<AminoAcid> lefts = getPossiblePairs2_2(left, exception1, exception2, aminos);
                    right = new Point(activePoint.x + 1, activePoint.y);
                    List<AminoAcid> rights = getPossiblePairs2_2(right, exception1, exception2, aminos);
                    
                    for(AminoAcid aa : ups){
                        HydrophobicPair hp = new HydrophobicPair(up, activePoint);
                        
                        if(!containsPair(pairs, hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                    for(AminoAcid aa : downs){
                        HydrophobicPair hp = new HydrophobicPair(down, activePoint);
                        if(!containsPair(pairs, hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                    for(AminoAcid aa : lefts){
                        HydrophobicPair hp = new HydrophobicPair(left, activePoint);
                        if(!pairs.contains(hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                    for(AminoAcid aa : rights){
                        HydrophobicPair hp = new HydrophobicPair(right, activePoint);
                        if(!pairs.contains(hp)){
                            pairs.add(hp);
                            count++;
                        }
                    }
                }
            }   
        }
        return count;
    }
    
    private static boolean containsPair(List<HydrophobicPair> pairs, HydrophobicPair pair){
        for(HydrophobicPair p : pairs){
            if(p.equals(pair)){
                return true;
            }
        }
        return false;
    }
    
    private static List<AminoAcid> getPossiblePairs(Point toTest, Point exception, List<AminoAcid> start){
        List<AminoAcid> temp = new ArrayList<>();
        if(!toTest.equals(exception)){
            for(AminoAcid aa : start){
                if(aa.coordinate.equals(toTest) && aa.hydrophobe == 1){
                    temp.add(aa);
                }
            }
        }   
        return temp;
    }
    
    private static List<AminoAcid> getPossiblePairs2_1(Point toTest, Point exception, AminoAcid[] start){
        
        List<AminoAcid> temp = new ArrayList<>();
        if(!toTest.equals(exception)){
            for (AminoAcid test : start) {
                if (test.coordinate.equals(toTest)) {
                    if (test.hydrophobe == 1) {
                        temp.add(test);
                    }
                }
            }
        } 
        return temp;
    }
    
    private static List<AminoAcid> getPossiblePairsTwo(Point toTest, Point exception, Point exception2, List<AminoAcid> start){
        List<AminoAcid> temp = new ArrayList<>();
        
        if(!toTest.equals(exception) && !toTest.equals(exception2)){
            for(AminoAcid aa : start){
                if(aa.coordinate.equals(toTest) && aa.hydrophobe == 1){
                    temp.add(aa);
                }
            }
        }
        return temp;
    }
    
    private static List<AminoAcid> getPossiblePairs2_2(Point toTest, Point exception, Point exception2, AminoAcid[] start){
        List<AminoAcid> temp = new ArrayList<>();
        
        if(!toTest.equals(exception) && !toTest.equals(exception2)){
            for(AminoAcid aa : start){
                if(aa.coordinate.equals(toTest) && aa.hydrophobe == 1){
                    temp.add(aa);
                }
            }
        }
        return temp;
    }
    
    private static double FitnessSecond(List<Point> points){
        int occurrences = 0;
        
        for(Point p : points){
            int tempOccurrences = Collections.frequency(points, p);
            occurrences += tempOccurrences - 1;
        }
        return occurrences + 1;
        //linearer kongruenz generator
    }
    
        private static double FitnessSecond2(Point[] points){
        int occurrences = 0;
        
        for (Point point : points) {
            int tempOccurences = 0; 
            for (Point point1 : points) {
                if (point.equals(point1)) {
                    tempOccurences++;
                }
            }
            occurrences += tempOccurences - 1;
        }
        return occurrences + 1;
    }
    
    //tested
    private static Point getVectorFromDirection(Point vector, int direction){
        
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
}
