package Engine;

import GUI.Plotter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class EquationHandler {

    private Plotter plotter;

    public EquationHandler(Plotter plotter) {
        this.plotter = plotter;
        uploadDataPoints();
    }

    public void uploadDataPoints(){

        double negativePI = -(Math.PI);
        double positivePI = Math.PI;
        double increment = (Math.PI/40);

        List<Point2D.Double> dataPoints = new ArrayList<>();

        while(negativePI <= positivePI){

            dataPoints.add(new Point2D.Double(negativePI, Math.sin(negativePI)));
            negativePI += increment;
        }

        plotter.setPoints(dataPoints); // sends dataPoint to Plot
    }
}
