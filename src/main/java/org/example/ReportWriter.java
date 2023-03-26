package org.example;

import org.example.comparator.TariffIndexComparator;
import org.example.model.Record;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportWriter {

    private final TariffIndexComparator comparator = new TariffIndexComparator();
    private final CostCalculator costCalculator = new CostCalculator();

    public void writeReports(Map<String, List<Record>> dataFromReport) {
        for (String number : dataFromReport.keySet()) {
            List<Record> recordsByNumber = dataFromReport.get(number).stream().sorted(comparator).collect(Collectors.toList());
            createReportForNumber(number, recordsByNumber);
        }
    }

    private void createReportForNumber(String number, List<Record> records) {
        try (FileWriter fileWriter = new FileWriter("reports\\report" + number + ".txt")) {
            fileWriter.write("Tariff index: " + records.get(0).getTariffIndex() + "\n");
            fileWriter.write("----------------------------------------------------------------------------" + "\n");
            fileWriter.write("Report for phone number " + number + ":" + "\n");
            fileWriter.write("----------------------------------------------------------------------------" + "\n");
            fileWriter.write("| Call Type |   Start Time        |     End Time        | Duration | Cost  |" + "\n");
            fileWriter.write("----------------------------------------------------------------------------" + "\n");
            for (Record value : records) {
                fileWriter.write(value.toString() + "\n");
            }
            fileWriter.write("----------------------------------------------------------------------------" + "\n");
            fileWriter.write("|                                           Total Cost: |     "
                    + costCalculator.calculateTotal(records) + " rubles |"
                    + "\n");
            fileWriter.write("----------------------------------------------------------------------------" + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка сохранения данных. " + e.getMessage());
        }
    }
}
