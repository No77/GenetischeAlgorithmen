/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package General;

// benchmark sequences for the 2d HP model
// 0 = hydrophil, "white"
// 1 = hydrophob, "black"
// data source: Ron Unger, John Moult: Genetic Algorithms for Protein Folding Simulations,
//              Journal of Molecular Biology, Vol. 231, No. 1, May 1993

public class Examples {

	public static final String SEQ20 = "10100110100101100101";
        public static final int[] SEQ20bits = {1,0,1,0,0,1,1,0,1,0,0,1,0,1,1,0,0,1,0,1};
	public static final String SEQ24 = "110010010010010010010011";
        public static final int[] SEQ24bits = {1,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,1};
	public static final String SEQ25 = "0010011000011000011000011";
        public static final int[] SEQ25bits = {0,0,1,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1};
	public static final String SEQ36 = "000110011000001111111001100001100100";
        public static final int[] SEQ36bits = {0,0,0,1,1,0,0,1,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,1,1,0,0,0,0,1,1,0,0,1,0,0};
	public static final String SEQ48 = "001001100110000011111111110000001100110010011111";
        public static final int[] SEQ48bits = {0,0,1,0,0,1,1,0,0,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,0,0,1,1,0,0,1,0,0,1,1,1,1,1};
	public static final String SEQ50 = "11010101011110100010001000010001000101111010101011";
        public static final int[] SEQ50bits = {1,1,0,1,0,1,0,1,0,1,1,1,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1};
}
