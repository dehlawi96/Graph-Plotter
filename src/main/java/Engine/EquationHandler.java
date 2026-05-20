package Engine;

public class EquationHandler {

    public static void main(String[] args) {

        // Lit code
        double Least = -(Math.PI);
        double Target = 0;
        double Most = Math.PI;

        double leastTem = Least; // To operate over value without changing the og
        double increment = (Math.PI/10);

        while (leastTem <= Target) {

            System.out.printf("%f %10f\n", leastTem, Math.sin(leastTem));
            leastTem += increment;

        }

    }
}
