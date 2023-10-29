package ulpgc.bigdata.builders;

import ulpgc.bigdata.Matrix;
import ulpgc.bigdata.MatrixBuilder;
import ulpgc.bigdata.reader.Reader;
import ulpgc.bigdata.records.COO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public record CoordinateFormatBuilder(List<Integer> rows, List<Integer> columns, List<Double> values) implements Matrix, MatrixBuilder {

    @Override
    public CoordinateFormatBuilder build(String filePath, boolean isSorted) throws IOException {
        CoordinateFormatBuilder builder = new CoordinateFormatBuilder(rows, columns, values);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<COO> cooList = new Reader().read(reader);
        if (isSorted) {
            sort(cooList);
        }
        for (COO coo : cooList) {
            builder.set(coo.rows(), coo.columns(), coo.values());
        }
        return builder;
    }

    @Override
    public void buildPtrs(String filePath) throws IOException {
        // Do nothing
    }

    void set(int i, int j, double value) {
        rows.add(i);
        columns.add(j);
        values.add(value);
    }

    private void sort(List<COO> cooList) {
        cooList.sort(Comparator.comparing(COO::rows).thenComparing(COO::columns));
    }
}
