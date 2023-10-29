import ulpgc.bigdata.algorithms.operations.multiplications.SparseMatrixMultiplication;
import ulpgc.bigdata.builders.CompressedColumnStorageBuilder;
import ulpgc.bigdata.builders.CompressedRowStorageBuilder;
import ulpgc.bigdata.matrix.CompressedColumnStorage;
import ulpgc.bigdata.matrix.CompressedRowStorage;

import java.io.IOException;
import java.util.List;

public class SparseMatrixMultiplicationBenchmark {
    public static final String filePath = "src/main/resources/";

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> matrices = List.of("685_bus.mtx", "1138_bus.mtx", "bcsstk13.mtx", "bcsstk28.mtx", "ex15.mtx", "dw4096.mtx");

        for (String matrix : matrices) {
            CompressedRowStorageBuilder crsBuilder = new CompressedRowStorage().get(filePath + matrix, true);
            CompressedColumnStorageBuilder ccsBuilder = new CompressedColumnStorage().get(filePath + matrix, false);
            SparseMatrixMultiplication multiply = new SparseMatrixMultiplication();
            double start = System.currentTimeMillis();
            multiply.multiply(crsBuilder, ccsBuilder, "product_" + matrix, matrix);
            double end = System.currentTimeMillis();
            System.out.println("Sparse Matrix Multiplication: " + matrix);
            System.out.println((end - start) + " ms");
        }
    }
}
