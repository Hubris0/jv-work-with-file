package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buyCount = 0;
        int supplyCount = 0;
        int operationIndex = 0;
        int countIndex = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String operation = parts[operationIndex];
                int count = Integer.parseInt(parts[countIndex]);
                if (operation.equals("buy")) {
                    buyCount += count;
                } else if (operation.equals("supply")) {
                    supplyCount += count;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("File can't be read", e);
        }
        int result = supplyCount - buyCount;
        String resultString = "supply," + supplyCount + System.lineSeparator() +
                "buy," + buyCount + System.lineSeparator() +
                "result," + result;
        try (java.io.FileWriter writer = new java.io.FileWriter(toFileName)) {
            writer.write(resultString);
        } catch (IOException e) {
            throw new RuntimeException("File can't be written", e);
        }
    }
}
