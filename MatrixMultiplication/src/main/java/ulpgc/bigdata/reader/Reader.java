package ulpgc.bigdata.reader;

import ulpgc.bigdata.records.COO;
import ulpgc.bigdata.records.Dimension;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<COO> read(BufferedReader reader) throws IOException {
        skipHeader(reader);
        return readMatrix(reader);
    }

    public Dimension readDimension(BufferedReader reader) throws IOException {
        String line;
        int rows = 0;
        int columns = 0;
        int nnz = 0;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("%")) { continue; }
            String[] parts = line.trim().split("\\s+");
            if (parts.length >= 2) {
                rows = Integer.parseInt(parts[0]);
                columns = Integer.parseInt(parts[1]);
                nnz = Integer.parseInt(parts[2]);
            }
            break;
        }
        reader.close();
        return new Dimension(rows, columns, nnz);
    }

    private void skipHeader(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("%")) {
                continue;
            }
            break;
        }
    }

    private List<COO> readMatrix(BufferedReader reader) throws IOException {
        String line;
        List<COO> cooList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length >= 3) {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                double value = Double.parseDouble(parts[2]);
                cooList.add(new COO(row, col, value));
            }
        }
        reader.close();
        return cooList;
    }
}
