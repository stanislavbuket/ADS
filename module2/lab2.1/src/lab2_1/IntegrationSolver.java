package lab2_1;

public class IntegrationSolver {
    
    public static double f(double x) {
        return 0.5 * Math.exp(Math.sqrt(1.0 + 2.0 * x));
    }

    public static double leftRectangles(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += f(a + i * h);
        }
        return sum * h;
    }

    public static double rightRectangles(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += f(a + i * h);
        }
        return sum * h;
    }

    public static double midpointRectangles(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += f(a + (i + 0.5) * h);
        }
        return sum * h;
    }

    public static double trapezoids(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);
        double sum = 0.5 * (f(a) + f(b));
        for (int i = 1; i < n; i++) {
            sum += f(a + i * h);
        }
        return sum * h;
    }

    public static double simpson(double a, double b, double h) {
        int n = (int) Math.round((b - a) / h);
        if (n % 2 != 0) {
            n++; // Simpson's rule requires an even number of intervals
            h = (b - a) / n;
        }
        double sum = f(a) + f(b);
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                sum += 2.0 * f(a + i * h);
            } else {
                sum += 4.0 * f(a + i * h);
            }
        }
        return sum * (h / 3.0);
    }
}
