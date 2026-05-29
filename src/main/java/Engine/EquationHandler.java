package Engine;

import org.mariuszgromada.math.mxparser.Function;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EquationHandler {

    private static final double START_X = -(Math.PI * 5);
    private static final double END_X = Math.PI * 5;
    private static final double STEP = Math.PI / 200;
    private static final int EXPECTED_POINTS = (int) ((END_X - START_X) / STEP) + 1;

    public static List<List<Point2D>> uploadDataPoints(String rawInput) {
        return Arrays.stream(rawInput.split(","))
                .parallel()
                .map(String::trim)
                .filter(e -> !e.isEmpty())
                .map(EquationHandler::evaluateSingle)
                .collect(Collectors.toList());
    }

    private static List<Point2D> evaluateSingle(String expressionBody) {
        Function f = new Function("f(x) = " + expressionBody);
        List<Point2D> points = new ArrayList<>(EXPECTED_POINTS);

        for (double x = START_X; x <= END_X; x += STEP) {
            double y = f.calculate(x);
            if (Double.isFinite(y)) {
                points.add(new Point2D.Double(x, y));
            }
        }
        return points;
    }
}