package ulpgc.bigdata;

import ulpgc.bigdata.builders.CompressedColumnStorageBuilder;

import java.io.IOException;

public interface MatrixFormat {
    Matrix get(String filePath, boolean isSorted) throws IOException;
}
