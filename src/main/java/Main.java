import Engine.EquationHandler;
import GUI.Plotter;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Plotter plotter = new Plotter();
            EquationHandler handler = new EquationHandler(plotter);
            plotter.setVisible(true);
        });
    }
}
