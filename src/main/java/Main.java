import Engine.EquationHandler;
import GUI.Frame;
import GUI.Plotter;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Frame frame = new Frame();
            Plotter plotter = new Plotter();
            EquationHandler handler = new EquationHandler(plotter);
            frame.add(plotter);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
