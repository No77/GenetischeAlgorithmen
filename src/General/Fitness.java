package General;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Malte
 */
public class Fitness {
    public static double Fitness(Fold f){

        AminoAcid[] aminos = f.getAminoAcids();
        
        Point[] points = getPoints(aminos);
        
        double first = FitnessFirst(aminos);
        
        double second = FitnessSecond(points);
        
        int maxPairs = getMaxPairs(f.sequence);
        
        return first/second/maxPairs;
    }
    
    private static int getMaxPairs(int[] sequence){
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
    
    private static double FitnessFirst(AminoAcid[] aminos){
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
                        List<AminoAcid> ups = getPossiblePairs3D(up, exception, aminos);
                        down = new Point(activePoint.x, activePoint.y -1);
                        List<AminoAcid> downs = getPossiblePairs3D(down, exception, aminos);
                        left = new Point(activePoint.x -1, activePoint.y);
                        List<AminoAcid> lefts = getPossiblePairs3D(left, exception, aminos);
                        right = new Point(activePoint.x + 1, activePoint.y);
                        List<AminoAcid> rights = getPossiblePairs3D(right, exception, aminos);

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
                        List<AminoAcid> ups = getPossiblePairs3D(up, exception, aminos);
                        down = new Point(activePoint.x, activePoint.y -1);
                        List<AminoAcid> downs = getPossiblePairs3D(down, exception, aminos);
                        left = new Point(activePoint.x -1, activePoint.y);
                        List<AminoAcid> lefts = getPossiblePairs3D(left, exception, aminos);
                        right = new Point(activePoint.x + 1, activePoint.y);
                        List<AminoAcid> rights = getPossiblePairs3D(right, exception, aminos);

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
                    List<AminoAcid> ups = getPossiblePairs2D(up, exception1, exception2, aminos);
                    down = new Point(activePoint.x, activePoint.y -1);
                    List<AminoAcid> downs = getPossiblePairs2D(down, exception1, exception2, aminos);
                    left = new Point(activePoint.x -1, activePoint.y);
                    List<AminoAcid> lefts = getPossiblePairs2D(left, exception1, exception2, aminos);
                    right = new Point(activePoint.x + 1, activePoint.y);
                    List<AminoAcid> rights = getPossiblePairs2D(right, exception1, exception2, aminos);
                    
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
    
    private static List<AminoAcid> getPossiblePairs3D(Point toTest, Point exception, AminoAcid[] start){
        
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
    
    private static List<AminoAcid> getPossiblePairs2D(Point toTest, Point exception, Point exception2, AminoAcid[] start){
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
    
    private static double FitnessSecond(Point[] points){
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

    private static Point[] getPoints(AminoAcid[] aminos) {
        Point[] points = new Point[aminos.length];
        for (int i = 0; i < aminos.length; i++) {
            points[i] = aminos[i].coordinate;
        }
        return points;
    }
}
