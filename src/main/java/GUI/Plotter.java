package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Plotter extends JPanel {

    private static final int SCALE = 40;

    private final Color bgColor = new Color(255, 255, 240);
    private final Color gridColor = new Color(210, 215, 220);

    private final Color[] graphColors = {
            Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.CYAN, Color.GREEN.darker()
    };

    private List<List<Point2D>> screenGraphs = new ArrayList<>();
    private BufferedImage graphCache;
    private boolean dirty = true;

    public Plotter() {
        setPreferredSize(new Dimension(800, 600));
    }

    public void setMultiPoints(List<List<Point2D>> mathPoints) {
        this.screenGraphs = toScreenCoords(mathPoints);
        dirty = true;
        repaint();
    }

    private List<List<Point2D>> toScreenCoords(List<List<Point2D>> mathGraphs) {
        List<List<Point2D>> result = new ArrayList<>();
        for (List<Point2D> graph : mathGraphs) {
            List<Point2D> screenPoints = new ArrayList<>(graph.size());
            for (Point2D p : graph) {
                screenPoints.add(new Point2D.Double(
                        p.getX() * SCALE,
                        p.getY() * SCALE * -1
                ));
            }
            result.add(screenPoints);
        }
        return result;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (dirty || graphCache == null
                || graphCache.getWidth() != getWidth()
                || graphCache.getHeight() != getHeight()) {

            graphCache = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = graphCache.createGraphics();
            renderAll(g2d);
            g2d.dispose();
            dirty = false;
        }

        g.drawImage(graphCache, 0, 0, null);
    }

    private void renderAll(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.translate(getWidth() / 2, getHeight() / 2);

        drawGrid(g2d);
        drawAxes(g2d);

        for (int i = 0; i < screenGraphs.size(); i++) {
            drawGraph(g2d, screenGraphs.get(i), graphColors[i % graphColors.length]);
        }
    }

    private void drawGrid(Graphics2D g2d) {
        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;

        g2d.setColor(gridColor);
        g2d.setStroke(new BasicStroke(1.0f));

        for (int x = 0; x <= halfWidth + SCALE; x += SCALE) {
            g2d.drawLine( x, -halfHeight,  x, halfHeight);
            g2d.drawLine(-x, -halfHeight, -x, halfHeight);
        }

        for (int y = 0; y <= halfHeight + SCALE; y += SCALE) {
            g2d.drawLine(-halfWidth,  y, halfWidth,  y);
            g2d.drawLine(-halfWidth, -y, halfWidth, -y);
        }
    }

    private void drawAxes(Graphics2D g2d) {
        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2.0f));

        g2d.drawLine(-halfWidth, 0, halfWidth, 0);
        g2d.drawLine(0, -halfHeight, 0, halfHeight);
    }

    private void drawGraph(Graphics2D g2d, List<Point2D> points, Color color) {
        if (points == null || points.size() < 2) return;

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2.0f));

        GeneralPath path = new GeneralPath();
        path.moveTo(points.get(0).getX(), points.get(0).getY());

        for (int i = 1; i < points.size() - 1; i++) {
            double x1 = points.get(i).getX();
            double y1 = points.get(i).getY();
            double x2 = points.get(i + 1).getX();
            double y2 = points.get(i + 1).getY();

            double mx = (x1 + x2) / 2.0;
            double my = (y1 + y2) / 2.0;
            path.quadTo(x1, y1, mx, my);
        }

        Point2D last = points.get(points.size() - 1);
        path.lineTo(last.getX(), last.getY());

        g2d.draw(path);
    }
}