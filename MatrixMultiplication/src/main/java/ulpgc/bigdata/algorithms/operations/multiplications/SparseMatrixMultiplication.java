package ulpgc.bigdata.algorithms.operations.multiplications;

import ulpgc.bigdata.builders.CompressedColumnStorageBuilder;
import ulpgc.bigdata.builders.CompressedRowStorageBuilder;
import ulpgc.bigdata.files.FileManager;
import ulpgc.bigdata.reader.Reader;
import ulpgc.bigdata.records.Dimension;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SparseMatrixMultiplication {

    public void multiply(CompressedRowStorageBuilder A, CompressedColumnStorageBuilder B, String filename, String matrixName) throws IOException {
        String filePath = "src/main/resources/";
        String multiplicationFilePath = "src/main/resources/products/";
        new FileManager().createFile(filename);
        BufferedReader reader = new BufferedReader(new FileReader(filePath + matrixName));
        Dimension dimension = new Reader().readDimension(reader);
        int size = dimension.columnsDimension();
        int nnz = dimension.nonZeroElements();
        new FileManager().write(multiplicationFilePath + filename, size + " " + size + " " + nnz + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int ii = A.rowsPtrs().get(i);
                int iEnd = A.rowsPtrs().get(i + 1);
                int jj = B.colsPtrs().get(j);
                int jEnd = B.colsPtrs().get(j + 1);
                double s = 0;
                while (ii < iEnd && jj < jEnd) {
                    int colA = A.columns().get(ii);
                    int colB = B.rows().get(jj);
                    if (colA == colB) {
                        s += A.values().get(ii) * B.values().get(jj);
                        ii++;
                        jj++;
                    } else if (colA < colB) {
                        ii++;
                    } else {
                        jj++;
                    }
                }
                if (s != 0) {
                    int row = i + 1;
                    int col = j + 1;
                    new FileManager().write(multiplicationFilePath + filename, row + " " + col + " " + s + "\n");
                }
            }
        }
    }
}
