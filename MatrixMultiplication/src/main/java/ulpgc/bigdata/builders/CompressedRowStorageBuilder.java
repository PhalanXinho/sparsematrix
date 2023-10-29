package ulpgc.bigdata.builders;

import ulpgc.bigdata.Matrix;
import ulpgc.bigdata.MatrixBuilder;
import ulpgc.bigdata.matrix.CoordinateFormat;
import ulpgc.bigdata.reader.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public record CompressedRowStorageBuilder(List<Integer> rowsPtrs, List<Integer> columns, List<Double> values) implements Matrix, MatrixBuilder {

    @Override
    public CompressedRowStorageBuilder build(String filePath, boolean isSorted) throws IOException {
        CompressedRowStorageBuilder builder = new CompressedRowStorageBuilder(rowsPtrs, columns, values);
        CoordinateFormatBuilder cooBuilder = new CoordinateFormat().get(filePath, isSorted);
        List<Integer> cooColumns = cooBuilder.columns();
        List<Double> cooValues = cooBuilder.values();
        builder.set(cooColumns, cooValues, filePath);
        return builder;
    }

    public void buildPtrs(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int size = new Reader().readDimension(reader).rowsDimension();
        CoordinateFormat coordinateFormat = new CoordinateFormat();
        CoordinateFormatBuilder builder = coordinateFormat.get(filePath, true);
        List<Integer> cooRows = builder.rows();
        if (cooRows.get(0) != 0) {
            for (int i = 0; i < cooRows.get(0); i++)
                rowsPtrs.add(0);
        }
        for (int i = 0; i < cooRows.size(); i++) {
            if (i == 0)
                rowsPtrs.add(0);
            else if (cooRows.get(i) - cooRows.get(i - 1) > 1) {
                for (int j = 0; j < cooRows.get(i) - cooRows.get(i - 1); j++)
                    rowsPtrs.add(i);
            } else if (!cooRows.get(i).equals(cooRows.get(i - 1)))
                rowsPtrs.add(i);
        }
        if (cooRows.get(cooRows.size() - 1) != size - 1) {
            for (int i = cooRows.get(cooRows.size() - 1); i < size - 1; i++)
                rowsPtrs.add(cooRows.size());
        }
        rowsPtrs.add(cooRows.size());
    }

    private void set(List<Integer> cooColumns, List<Double> cooValues, String filePath) throws IOException {
        buildPtrs(filePath);
        columns.addAll(cooColumns);
        values.addAll(cooValues);
    }
}
