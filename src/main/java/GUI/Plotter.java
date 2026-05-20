package GUI;

import javax.swing.*;
import java.awt.*;

public class Plotter extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final Color bgColor = new Color(243,19,243);
    public Plotter() {

        setTitle("Plotter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setBackground(bgColor);
    }
}
