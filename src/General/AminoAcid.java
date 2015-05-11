/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

import java.awt.Point;
import java.util.Objects;

/**
 * Class holding an int representing an aminoacid (hydrophobe = 1, hydrophil = 0)
 * As well as a Point representing the coordinate of an aminoacid in 2D coordinate 
 * system.
 * @author Malte Lammers
 */
public class AminoAcid {
    public int hydrophobe;
    public Point coordinate;
    
    public AminoAcid(int h, Point c){
        hydrophobe = h;
        coordinate = c;
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
        if (obj instanceof AminoAcid) {
            AminoAcid o = (AminoAcid) obj;
            return o.hydrophobe == hydrophobe && o.coordinate == coordinate;
                     
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.hydrophobe;
        hash = 43 * hash + Objects.hashCode(this.coordinate);
        return hash;
    }
}
