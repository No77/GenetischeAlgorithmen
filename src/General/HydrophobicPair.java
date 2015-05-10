/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

import java.awt.Point;
import java.util.Objects;

/**
 *
 * @author Malte
 */
public class HydrophobicPair {
    public Point first;
    public Point second;
    
    public HydrophobicPair(Point f, Point s){
        first = f;
        second = s;
    }
    
    @Override
    public boolean equals(Object obj) {
        //same object
        if(obj==this){
            return true;
        }
        //no object at all
        if(obj==null){
            return false;
        }
        //check if numerator and denominator are equal
        if (obj instanceof HydrophobicPair) {
            HydrophobicPair o = (HydrophobicPair) obj;
            return o.first.equals(first) && o.second.equals(second) ||
                    o.first.equals(second) && o.second.equals(first);
                     
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.first);
        hash = 71 * hash + Objects.hashCode(this.second);
        return hash;
    }
}
