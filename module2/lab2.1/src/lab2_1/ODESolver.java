package lab2_1;

import java.util.ArrayList;
import java.util.List;

public class ODESolver {
    
    public static double f(double x, double y) {
        return Math.sin(x) - y;
    }

    public static double analyticalSolution(double x, double x0, double y0) {
        double c = (y0 - 0.5 * (Math.sin(x0) - Math.cos(x0))) * Math.exp(x0);
        return 0.5 * (Math.sin(x) - Math.cos(x)) + c * Math.exp(-x);
    }

    public static class StepResult {
        public final double x;
        public final double yNumerical;
        public final double yAnalytical;
        public final double error;

        public StepResult(double x, double yNumerical, double yAnalytical) {
            this.x = x;
            this.yNumerical = yNumerical;
            this.yAnalytical = yAnalytical;
            this.error = Math.abs(yNumerical - yAnalytical);
        }
    }

    public static List<StepResult> solve(double x0, double xn, double y0, double h) {
        List<StepResult> results = new ArrayList<>();
        
        double x = x0;
        double y = y0;
        
        results.add(new StepResult(x, y, analyticalSolution(x, x0, y0)));
        
        int steps = (int) Math.round((xn - x0) / h);
        for (int i = 0; i < steps; i++) {
            double k1 = f(x, y);
            double k2 = f(x + h / 2.0, y + h * k1 / 2.0);
            double k3 = f(x + h, y - h * k1 + 2.0 * h * k2);
            
            y = y + h * (k1 + 4.0 * k2 + k3) / 6.0;
            x = x + h;
            
            double yAnalyt = analyticalSolution(x, x0, y0);
            results.add(new StepResult(x, y, yAnalyt));
        }
        
        return results;
    }
}
