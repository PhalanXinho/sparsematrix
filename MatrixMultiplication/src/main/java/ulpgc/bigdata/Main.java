package ulpgc.bigdata;

import ulpgc.bigdata.algorithms.operations.multiplications.DenseMatrixMultiplication;
import ulpgc.bigdata.builders.CompressedColumnStorageBuilder;
import ulpgc.bigdata.builders.CompressedRowStorageBuilder;
import ulpgc.bigdata.builders.CoordinateFormatBuilder;
import ulpgc.bigdata.builders.DenseMatrixBuilder;
import ulpgc.bigdata.matrix.CompressedColumnStorage;
import ulpgc.bigdata.matrix.CompressedRowStorage;
import ulpgc.bigdata.matrix.CoordinateFormat;
import ulpgc.bigdata.algorithms.operations.multiplications.SparseMatrixMultiplication;
import ulpgc.bigdata.matrix.DenseMatrix;

import java.io.IOException;
import java.util.List;

public class Main {
    public static final String filePath = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        CoordinateFormat coordinateFormat = new CoordinateFormat();
        CoordinateFormatBuilder builder = coordinateFormat.get(filePath, true);
        coordinateFormat.print(builder);

        CompressedRowStorage compressedRowStorage = new CompressedRowStorage();
        CompressedRowStorageBuilder crsBuilder = compressedRowStorage.get(filePath, true);
        compressedRowStorage.print(crsBuilder);

        CompressedColumnStorage compressedColumnStorage = new CompressedColumnStorage();
        CompressedColumnStorageBuilder ccsBuilder = compressedColumnStorage.get(filePath, false);
        compressedColumnStorage.print(ccsBuilder);

        SparseMatrixMultiplication multiply = new SparseMatrixMultiplication();
        multiply.multiply(crsBuilder, ccsBuilder, "bcsstk13.mtx", "bcsstk13.mtx");
    }
}
