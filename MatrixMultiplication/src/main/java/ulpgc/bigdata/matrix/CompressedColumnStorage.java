package ulpgc.bigdata.matrix;

import ulpgc.bigdata.Matrix;
import ulpgc.bigdata.MatrixFormat;
import ulpgc.bigdata.builders.CompressedColumnStorageBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompressedColumnStorage implements Matrix, MatrixFormat {
    @Override
    public CompressedColumnStorageBuilder get(String filePath, boolean isSorted) throws IOException {
        List<Integer> rows = new ArrayList<>();
        List<Integer> colsPtrs = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        return new CompressedColumnStorageBuilder(rows, colsPtrs, values).build(filePath, isSorted);
    }

    public void print(CompressedColumnStorageBuilder builder) {
        System.out.println("CCS Format");
        System.out.println("Values: " + builder.values());
        System.out.println("ColsPtrs: " + builder.colsPtrs());
        System.out.println("Rows : " + builder.rows());
        System.out.println();
    }
}
