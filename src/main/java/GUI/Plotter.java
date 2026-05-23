package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Plotter extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private final Color bgColor = new Color(255,255,240);

    List<Point2D.Double> points = new ArrayList<>();

    public Plotter() {

        setTitle("Plotter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
    }

    public void setPoints(List<Point2D.Double> points) {
        this.points = points;
        repaint();
    }

    //Grids on the canvas

    @Override
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        g2d.translate(WIDTH / 2, HEIGHT / 2);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2d);
        drawAxes(g2d);
        drawWaves(g2d, points);

    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(1.0f));

        int halfWidth = WIDTH / 2;
        int halfHeight = HEIGHT / 2;
        int increment = 10;

        for (int x = increment; x < halfWidth; x += increment) {

            g2d.drawLine(x, -halfHeight, x, halfHeight);
            g2d.drawLine(-x, -halfHeight, -x, halfHeight);
        }

        for (int y = increment; y < halfHeight; y += increment) {

            g2d.drawLine(-halfWidth, y, halfWidth, y);
            g2d.drawLine(-halfWidth, -y, halfWidth, -y);
        }

    }

    private void drawAxes(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2.0f));

        g2d.drawLine( -(WIDTH*2), 0, WIDTH*2, 0);
        g2d.drawLine( 0, -(HEIGHT*2), 0, HEIGHT*2);

    }

    private void drawWaves(Graphics2D g2d,  List<Point2D.Double> points) {

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(1.5f));

        Point2D.Double previousPoint = null;

        double xScale = 50;
        double yScale = 50;

        for (Point2D.Double point : points) {

            double displayX = point.x * xScale;
            double displayY = point.y * yScale * -1;

            Point2D.Double currentPoint = new Point2D.Double(displayX, displayY);
            if (previousPoint != null) {

                g2d.draw( new Line2D.Double(
                        previousPoint.x, previousPoint.y,
                        currentPoint.x,  currentPoint.y
                ));
            }
            previousPoint = currentPoint;
        }
    }
}
