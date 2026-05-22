package GUI;

import javax.swing.*;
import java.awt.*;

public class Plotter extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final Color bgColor = new Color(255,255,240);
    private int increment = 10;

    public Plotter() {

        setTitle("Plotter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setBackground(bgColor);
        setLocationRelativeTo(null);
    }

    //Grids on the canvas

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(WIDTH / 2, HEIGHT / 2);

        Stroke oldStroke = g2d.getStroke();
        Color oldColor = Color.darkGray;

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2.0f));

        g2d.drawLine(-(WIDTH*2),0,(WIDTH*2),0);
        g2d.drawLine(0,-(HEIGHT*2),0,(HEIGHT*2));

        g2d.setStroke(oldStroke);
        g2d.setColor(oldColor);

        for (int x = increment; x < WIDTH/2; x += increment) {

            g2d.drawLine(x, -(HEIGHT*2), x, (HEIGHT*2));
            g2d.drawLine(-x, -(HEIGHT*2), -x, (HEIGHT*2));
        }
    }
}
