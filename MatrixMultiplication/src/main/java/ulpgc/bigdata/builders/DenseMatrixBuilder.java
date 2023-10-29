package ulpgc.bigdata.builders;

import ulpgc.bigdata.matrix.CoordinateFormat;

import java.io.IOException;
import java.util.List;

public record DenseMatrixBuilder(double[][] matrix) {

    public double[][] build(String filePath, boolean isSorted) throws IOException {
        CoordinateFormatBuilder cooBuilder = new CoordinateFormat().get(filePath, isSorted);
        List<Integer> cooRows = cooBuilder.rows();
        List<Integer> cooColumns = cooBuilder.columns();
        List<Double> cooValues = cooBuilder.values();
        for (int i = 0; i < cooRows.size(); i++) {
            matrix[cooRows.get(i)][cooColumns.get(i)] = cooValues.get(i);
        }
        return matrix;
    }
}
