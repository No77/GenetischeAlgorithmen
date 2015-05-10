/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

/**
 *
 * @author Malte
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

@SuppressWarnings("serial")
public class Drawing extends JPanel {

    private static final int MAX_SCORE = 20;
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 100;
    private static final Color GRAPH_COLOR = Color.BLACK;
    private static final Color HYDROPHOBE_COLOR = Color.BLACK;
    private static final Color HYDROPHIL_COLOR = Color.LIGHT_GRAY;
    private static final Stroke GRAPH_STROKE = new BasicStroke(1f);
    private static final int GRAPH_POINT_WIDTH = 10;
    private static final int Y_HATCH_CNT = 10;
    private AminoAcid[] scores;

    public Drawing(Fold f) {
        Point[] points = Fitness.getPoints2(f.directions);
        scores = Fitness.getAminoAcids2(f.sequence, points);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.length - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < scores.length - 1; i++) {
            int x1 = (int) (scores[i].coordinate.x * xScale + 100);
            int y1 = (int) (scores[i].coordinate.y * yScale + 100);
            int x2 = (int) (scores[i + 1].coordinate.x * xScale + 100);
            int y2 = (int) (scores[i + 1].coordinate.y * yScale + 100);
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        for (int i = 0; i < scores.length; i++) {
            if (scores[i].hydrophobe == 1) {
                g2.setColor(HYDROPHOBE_COLOR);
            }
            if (scores[i].hydrophobe == 0) {
                g2.setColor(HYDROPHIL_COLOR);
            }
            int x = (int) (scores[i].coordinate.x * xScale - GRAPH_POINT_WIDTH / 2 + 100);
            int y = (int) (scores[i].coordinate.y * yScale - GRAPH_POINT_WIDTH / 2 + 100);
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    private static void createAndShowGui() {

        try {
            Fold f = GeneticAlgorithm.GeneticAlgorithm(500, 20000, Examples.SEQ20bits);
            Drawing mainPanel = new Drawing(f);
            JFrame frame = new JFrame("DrawGraph");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(Drawing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGui();
        });
    }
}
