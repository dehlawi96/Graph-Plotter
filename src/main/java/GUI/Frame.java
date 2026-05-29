package GUI;

import Engine.EquationHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {

    private final Plotter plotter;
    private final List<JTextField> equationFields = new ArrayList<>();
    private final JPanel fieldsContainer;

    public Frame() {
        setTitle("Graph Plotter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        plotter = new Plotter();
        add(plotter, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setPreferredSize(new Dimension(280, 600));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        fieldsContainer = new JPanel();
        fieldsContainer.setLayout(new BoxLayout(fieldsContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(fieldsContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        controlPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionButtonPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        JButton addFieldButton = new JButton("+ Add Equation");
        JButton plotButton = new JButton("Plot Graphs");
        actionButtonPanel.add(addFieldButton);
        actionButtonPanel.add(plotButton);
        controlPanel.add(actionButtonPanel, BorderLayout.SOUTH);

        add(controlPanel, BorderLayout.WEST);

        addNewEquationField("sin(x)");

        addFieldButton.addActionListener(e -> addNewEquationField(""));
        plotButton.addActionListener(e -> plotGraphs());

        pack();
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);
    }

    private void plotGraphs() {
        StringBuilder combinedInput = new StringBuilder();
        for (JTextField field : equationFields) {
            String text = field.getText().trim();
            if (!text.isEmpty()) {
                combinedInput.append(text).append(",");
            }
        }

        if (combinedInput.length() == 0) {
            System.out.println("All expression fields are empty");
            return;
        }

        List<List<Point2D>> allGraphs = EquationHandler.uploadDataPoints(combinedInput.toString());
        plotter.setMultiPoints(allGraphs);
    }

    private void addNewEquationField(String defaultText) {
        JPanel fieldRow = new JPanel(new BorderLayout(5, 0));
        fieldRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JTextField newField = new JTextField(defaultText, 12);
        JButton deleteButton = new JButton("X");
        deleteButton.setMargin(new Insets(0, 4, 0, 4));

        fieldRow.add(new JLabel("f(x) = "), BorderLayout.WEST);
        fieldRow.add(newField, BorderLayout.CENTER);
        fieldRow.add(deleteButton, BorderLayout.EAST);

        equationFields.add(newField);
        fieldsContainer.add(fieldRow);
        fieldsContainer.add(Box.createRigidArea(new Dimension(0, 5)));

        deleteButton.addActionListener(e -> {
            equationFields.remove(newField);
            fieldsContainer.remove(fieldRow);
            fieldsContainer.revalidate();
            fieldsContainer.repaint();
        });

        fieldsContainer.revalidate();
        fieldsContainer.repaint();
    }
}