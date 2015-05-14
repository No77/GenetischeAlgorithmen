/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DrawingGeneticAlgorithm;
import General.AminoAcid;
import General.Fold;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 *
 * @author Malte
 */
public class DrawFoldPanel extends JPanel {
    public Fold fold;
    private static final Color GRAPH_COLOR = Color.BLACK;
    private static final Color HYDROPHOBE_COLOR = Color.BLACK;
    private static final Color HYDROPHIL_COLOR = Color.LIGHT_GRAY;
    private static final Stroke GRAPH_STROKE = new BasicStroke(1f);
    private static final int GRAPH_POINT_WIDTH = 10;
    
    /*
    public DrawFoldPanel(Fold f){
        super();
        fold = f;
    }*/
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(fold == null){
            int[] x = {1,1};
            int[] y = {0};
            fold = new Fold(x,y);
        }
        
        AminoAcid[] aminos = fold.getAminoAcids();
        double yScale = ((double) getHeight() / (aminos.length - 1));
        double xScale = yScale;//((double) getWidth() / (aminos.length - 1));
        //AminoAcids in x -y umwandeln
        //offsets um immer in die richtige Ecke zu schieben
        int lowestX = 0;
        int lowestY = 0;

        for(AminoAcid a : aminos){
            a.coordinate.y = a.coordinate.y * -1;
            if(a.coordinate.x < lowestX){
                lowestX = a.coordinate.x;
            }
            if(a.coordinate.y < lowestY){
                lowestY = a.coordinate.y;
            }
        }

        lowestX = (int)(lowestX*-1*xScale)+(GRAPH_POINT_WIDTH / 2);
        lowestY = (int)(lowestY*-1*yScale)+(GRAPH_POINT_WIDTH / 2);

        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(GRAPH_COLOR);
        g2d.setStroke(GRAPH_STROKE);
        for (int i = 0; i < aminos.length - 1; i++) {
            int x1 = (int)((aminos[i].coordinate.x*xScale)+ lowestX);
            int y1 = (int)((aminos[i].coordinate.y *yScale)+ lowestY);
            int x2 = (int)((aminos[i + 1].coordinate.x*xScale)+ lowestX);
            int y2 = (int)((aminos[i + 1].coordinate.y*yScale)+ lowestY);
            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.setStroke(oldStroke);
        for (int i = 0; i < aminos.length; i++) {
            if (aminos[i].hydrophobe == 1) {
                g2d.setColor(HYDROPHOBE_COLOR);
            }
            if (aminos[i].hydrophobe == 0) {
                g2d.setColor(HYDROPHIL_COLOR);
            }
            int x = (int)((aminos[i].coordinate.x*xScale - (GRAPH_POINT_WIDTH / 2))+ lowestX);
            int y = (int)((aminos[i].coordinate.y*yScale - (GRAPH_POINT_WIDTH / 2))+ lowestY);
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2d.fillOval(x, y, ovalW, ovalH);
        }
    }
    
    public void setFold(Fold f){
        fold = f;
    }
}
