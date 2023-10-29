package ulpgc.bigdata.matrix;

import ulpgc.bigdata.Matrix;
import ulpgc.bigdata.MatrixFormat;
import ulpgc.bigdata.builders.CoordinateFormatBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoordinateFormat implements Matrix, MatrixFormat {
    @Override
    public CoordinateFormatBuilder get(String filePath, boolean isSorted) throws IOException {
        List<Integer> rows = new ArrayList<>();
        List<Integer> columns = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        return new CoordinateFormatBuilder(rows, columns, values).build(filePath, isSorted);
    }

    public void print(CoordinateFormatBuilder builder) {
        System.out.println("COO Format");
        System.out.println("Values: " + builder.values());
        System.out.println("Rows: " + builder.rows());
        System.out.println("Columns : " + builder.columns());
        System.out.println();
    }
}
