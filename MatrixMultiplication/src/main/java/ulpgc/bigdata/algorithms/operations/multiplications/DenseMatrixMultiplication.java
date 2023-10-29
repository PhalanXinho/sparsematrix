package ulpgc.bigdata.algorithms.operations.multiplications;

import ulpgc.bigdata.matrix.DenseMatrix;

public class DenseMatrixMultiplication {
    public double[][] multiply(double[][] a, double[][] b) {
        assert a.length == b.length;
        int n = a.length;
        double[][] c = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}
