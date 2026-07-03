package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String report = readFromFile(fromFileName);
        writeToFile(report, toFileName);
    }

    public String readFromFile(String fileName) {
        int supplyCount = 0;
        int buyCount = 0;
        String DELIMITER = ",";
        int OPERATION_INDEX = 0;
        int COUNT_INDEX = 1;
        String OPERATION_BUY = "buy";
        String OPERATION_SUPPLY = "supply";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(DELIMITER);
                String operation = parts[OPERATION_INDEX];
                int count = Integer.parseInt(parts[COUNT_INDEX]);
                if (operation.equals(OPERATION_BUY)) {
                    buyCount += count;
                } else if (operation.equals(OPERATION_SUPPLY)) {
                    supplyCount += count;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can't be read", e);
        }
        int result = supplyCount - buyCount;
        return "supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator()
                + "result," + result;
    }

    public void writeToFile(String report, String toFileName) {
        try (java.io.FileWriter writer = new java.io.FileWriter(toFileName)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File can't be written", e);
        }
    }
}
