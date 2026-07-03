package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final String DELIMITER = ",";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_SUPPLY = "supply";

    public String getStatistic(String fromFileName, String toFileName) {
        String report = generateReport(readFromFile(fromFileName));
        writeToFile(report, toFileName);
        return report;
    }

    public int[] readFromFile(String fileName) {
        int supplyCount = 0;
        int buyCount = 0;
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
        return new int[]{supplyCount, buyCount};
    }

    public void writeToFile(String report, String toFileName) {
        try (java.io.FileWriter writer = new java.io.FileWriter(toFileName)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File can't be written", e);
        }
    }

    public String generateReport(int[] operationCounts) {
        int supplyCount = operationCounts[SUPPLY_INDEX];
        int buyCount = operationCounts[BUY_INDEX];
        int result = supplyCount - buyCount;
        return OPERATION_SUPPLY + DELIMITER + supplyCount + System.lineSeparator()
                + OPERATION_BUY + DELIMITER + buyCount + System.lineSeparator()
                + "result" + DELIMITER + result;
    }
}
