import ulpgc.bigdata.algorithms.operations.multiplications.DenseMatrixMultiplication;
import ulpgc.bigdata.matrix.DenseMatrix;

import java.io.IOException;
import java.util.List;

public class DenseMatrixMultiplicationBenchmark {
    public static final String filePath = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        List<String> matrices = List.of("685_bus.mtx", "1138_bus.mtx", "bcsstk13.mtx", "bcsstk28.mtx", "ex15.mtx", "dw4096.mtx");

        for (String matrix : matrices) {
            double[][] denseMatrixBuilder = new DenseMatrix().get(filePath + matrix, true);
            double start = System.currentTimeMillis();
            new DenseMatrixMultiplication().multiply(denseMatrixBuilder, denseMatrixBuilder);
            double end = System.currentTimeMillis();
            System.out.println("Dense Matrix Multiplication: " + matrix);
            System.out.println((end - start) + " ms");
        }
    }
}
