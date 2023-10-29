package ulpgc.bigdata.matrix;

import ulpgc.bigdata.Matrix;
import ulpgc.bigdata.MatrixFormat;
import ulpgc.bigdata.builders.CompressedRowStorageBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompressedRowStorage implements Matrix, MatrixFormat {
    @Override
    public CompressedRowStorageBuilder get(String filePath, boolean isSorted) throws IOException {
        List<Integer> rowsPtrs = new ArrayList<>();
        List<Integer> columns = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        return new CompressedRowStorageBuilder(rowsPtrs, columns, values).build(filePath, isSorted);
    }

    public void print(CompressedRowStorageBuilder builder) {
        System.out.println("CRS Format");
        System.out.println("Values: " + builder.values());
        System.out.println("RowsPtrs: " + builder.rowsPtrs());
        System.out.println("Columns : " + builder.columns());
        System.out.println();
    }
}
