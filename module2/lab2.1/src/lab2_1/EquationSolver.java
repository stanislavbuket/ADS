package lab2_1;

public class EquationSolver {
    
    public static double y(double x) {
        return Math.pow(x, 4) + 6.0 * x * x - 12.0 * x - 8.0;
    }

    public static double dy(double x) {
        return 4.0 * Math.pow(x, 3) + 12.0 * x - 12.0;
    }

    public static double ddy(double x) {
        return 12.0 * x * x + 12.0;
    }

    public static double bisection(double a, double b, double eps, int maxIterations) {
        if (y(a) * y(b) > 0) {
            throw new IllegalArgumentException("Function signs at interval boundaries must be different");
        }
        double c = a;
        int iter = 0;
        while ((b - a) / 2.0 > eps && iter < maxIterations) {
            c = (a + b) / 2.0;
            if (Math.abs(y(c)) < 1e-15) {
                return c;
            }
            if (y(a) * y(c) < 0) {
                b = c;
            } else {
                a = c;
            }
            iter++;
        }
        return (a + b) / 2.0;
    }

    public static double newton(double a, double b, double eps, int maxIterations) {
        double x0;
        if (y(b) > 0) {
            x0 = b;
        } else if (y(a) > 0) {
            x0 = a;
        } else {
            x0 = b;
        }

        double x = x0;
        int iter = 0;
        while (iter < maxIterations) {
            double derivative = dy(x);
            if (Math.abs(derivative) < 1e-12) {
                break;
            }
            double nextX = x - y(x) / derivative;
            if (Math.abs(nextX - x) < eps) {
                return nextX;
            }
            x = nextX;
            iter++;
        }
        return x;
    }

    public static double chord(double a, double b, double eps, int maxIterations) {
        if (y(a) * y(b) > 0) {
            throw new IllegalArgumentException("Function signs at interval boundaries must be different");
        }
        double c;
        double x;
        if (y(b) > 0) {
            c = b;
            x = a;
        } else {
            c = a;
            x = b;
        }

        int iter = 0;
        while (iter < maxIterations) {
            double nextX = x - y(x) * (c - x) / (y(c) - y(x));
            if (Math.abs(nextX - x) < eps) {
                return nextX;
            }
            x = nextX;
            iter++;
        }
        return x;
    }

    public static void findAndPrintRoots(double start, double end, double step) {
        System.out.printf("Scanning interval [%.2f, %.2f] with step %.3f:\n", start, end, step);
        boolean found = false;
        for (double x = start; x < end; x += step) {
            double xNext = Math.min(x + step, end);
            double y1 = y(x);
            double y2 = y(xNext);
            if (y1 * y2 <= 0) {
                found = true;
                System.out.printf("  Root detected in subinterval [%.4f, %.4f]\n", x, xNext);
            }
        }
        if (!found) {
            System.out.println("  No roots detected in this interval.");
        }
    }
}
