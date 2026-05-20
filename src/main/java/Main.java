import GUI.Plotter;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Plotter plotter = new Plotter();
            plotter.setVisible(true);
        });
    }
}
