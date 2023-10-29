package ulpgc.bigdata.matrix;

import ulpgc.bigdata.builders.DenseMatrixBuilder;
import ulpgc.bigdata.reader.Reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class DenseMatrix {
    public double[][] get(String filePath, boolean isSorted) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int size = new Reader().readDimension(reader).columnsDimension();
        double[][] matrix = new double[size][size];
        return new DenseMatrixBuilder(matrix).build(filePath, isSorted);
    }

    public void print(double[][] builder) {
        System.out.println("Dense Matrix Format");
        for (double[] row : builder) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
