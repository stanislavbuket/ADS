package homework;

public class LUPSolver {

    public static class LUPResult {
        public final double[][] A;
        public final double[] b;
        public final double[][] L;
        public final double[][] U;
        public final double[][] P;
        public final double[] x;

        public LUPResult(double[][] A, double[] b, double[][] L, double[][] U, double[][] P, double[] x) {
            this.A = A;
            this.b = b;
            this.L = L;
            this.U = U;
            this.P = P;
            this.x = x;
        }
    }

    public static LUPResult decomposeAndSolve(double[][] A, double[] b) {
        int n = A.length;
        double[][] LU = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, LU[i], 0, n);
        }

        int[] pi = new int[n];
        for (int i = 0; i < n; i++) {
            pi[i] = i;
        }

        for (int k = 0; k < n; k++) {
            double maxVal = 0.0;
            int pivot = k;
            for (int i = k; i < n; i++) {
                double val = Math.abs(LU[i][k]);
                if (val > maxVal) {
                    maxVal = val;
                    pivot = i;
                }
            }

            if (maxVal < 1e-12) {
                throw new ArithmeticException("Матриця є виродженою (singular)");
            }

            if (pivot != k) {
                double[] tempRow = LU[k];
                LU[k] = LU[pivot];
                LU[pivot] = tempRow;

                int tempPi = pi[k];
                pi[k] = pi[pivot];
                pi[pivot] = tempPi;
            }

            for (int i = k + 1; i < n; i++) {
                LU[i][k] /= LU[k][k];
                for (int j = k + 1; j < n; j++) {
                    LU[i][j] -= LU[i][k] * LU[k][j];
                }
            }
        }

        double[][] L = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    L[i][j] = LU[i][j];
                } else if (i == j) {
                    L[i][j] = 1.0;
                } else {
                    L[i][j] = 0.0;
                }
            }
        }

        double[][] U = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i <= j) {
                    U[i][j] = LU[i][j];
                } else {
                    U[i][j] = 0.0;
                }
            }
        }

        double[][] P = new double[n][n];
        for (int i = 0; i < n; i++) {
            P[i][pi[i]] = 1.0;
        }

        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += LU[i][j] * y[j];
            }
            y[i] = b[pi[i]] - sum;
        }

        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += LU[i][j] * x[j];
            }
            x[i] = (y[i] - sum) / LU[i][i];
        }

        return new LUPResult(A, b, L, U, P, x);
    }

    public static void printMatrix(double[][] M) {
        for (double[] row : M) {
            for (double val : row) {
                System.out.printf("%10.4f ", val);
            }
            System.out.println();
        }
    }

    public static void printSystem(double[][] A, double[] b) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                double coeff = A[i][j];
                if (coeff == 0) continue;
                if (sb.length() > 0) {
                    if (coeff > 0) sb.append(" + ");
                    else sb.append(" - ");
                } else {
                    if (coeff < 0) sb.append("-");
                }
                sb.append(String.format("%.1fx%d", Math.abs(coeff), j + 1));
            }
            sb.append(String.format(" = %.1f", b[i]));
            System.out.println("  " + sb.toString());
        }
    }
}
