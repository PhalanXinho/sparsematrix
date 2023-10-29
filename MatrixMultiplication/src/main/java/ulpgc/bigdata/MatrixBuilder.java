package ulpgc.bigdata;

import java.io.IOException;

public interface MatrixBuilder {
    Matrix build(String filePath, boolean isSorted) throws IOException;
    void buildPtrs(String filePath) throws IOException;
}
