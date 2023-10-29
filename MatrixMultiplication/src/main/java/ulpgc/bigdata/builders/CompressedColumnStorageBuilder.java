package ulpgc.bigdata.builders;

import ulpgc.bigdata.Matrix;
import ulpgc.bigdata.MatrixBuilder;
import ulpgc.bigdata.matrix.CoordinateFormat;
import ulpgc.bigdata.reader.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public record CompressedColumnStorageBuilder(List<Integer> rows, List<Integer> colsPtrs, List<Double> values) implements Matrix, MatrixBuilder {

    @Override
    public CompressedColumnStorageBuilder build(String filePath, boolean isSorted) throws IOException {
        CompressedColumnStorageBuilder builder = new CompressedColumnStorageBuilder(rows, colsPtrs, values);
        CoordinateFormatBuilder cooBuilder = new CoordinateFormat().get(filePath, isSorted);
        List<Integer> cooRows = cooBuilder.rows();
        List<Double> cooValues = cooBuilder.values();
        builder.set(cooRows, cooValues, filePath);
        return builder;
    }

    @Override
    public void buildPtrs(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int size = new Reader().readDimension(reader).columnsDimension();
        CoordinateFormat coordinateFormat = new CoordinateFormat();
        CoordinateFormatBuilder builder = coordinateFormat.get(filePath, false);
        List<Integer> cooCols = builder.columns();
        if (cooCols.get(0) != 0) {
            for (int i = 0; i < cooCols.get(0); i++)
                colsPtrs.add(0);
        }
        for (int i = 0; i < cooCols.size(); i++) {
            if (i == 0)
                colsPtrs.add(0);
            else if (cooCols.get(i) - cooCols.get(i - 1) > 1) {
                for (int j = 0; j < cooCols.get(i) - cooCols.get(i - 1); j++)
                    colsPtrs.add(i);
            } else if (!cooCols.get(i).equals(cooCols.get(i - 1)))
                colsPtrs.add(i);
        }
        if (cooCols.get(cooCols.size() - 1) != size - 1) {
            for (int i = cooCols.get(cooCols.size() - 1); i < size - 1; i++)
                colsPtrs.add(cooCols.size());
        }
        colsPtrs.add(cooCols.size());
    }

    private void set(List<Integer> cooRows, List<Double> cooValues, String filePath) throws IOException {
        buildPtrs(filePath);
        rows.addAll(cooRows);
        values.addAll(cooValues);
    }
}
