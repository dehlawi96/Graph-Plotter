package GUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {

        setTitle("Graph Plotter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(250, 600));
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Buttons for testing
        controlPanel.add(new JLabel("Enter Equation:"));
        controlPanel.add(new JTextField(15));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        controlPanel.add(new JLabel("Limits:"));
        controlPanel.add(new JTextField("0 to 10", 15));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        controlPanel.add(new JButton("Plot Graph"));

        add(controlPanel, BorderLayout.WEST);

        setLocationRelativeTo(null);
    }
}
