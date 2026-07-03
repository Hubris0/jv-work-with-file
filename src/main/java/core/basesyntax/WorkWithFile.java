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
        String delimeter = ",";
        int operationIndex = 0;
        int countIndex = 1;
        String operationBuy = "buy";
        String operationSupply = "supply";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(delimeter);
                String operation = parts[operationIndex];
                int count = Integer.parseInt(parts[countIndex]);
                if (operation.equals(operationBuy)) {
                    buyCount += count;
                } else if (operation.equals(operationSupply)) {
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
