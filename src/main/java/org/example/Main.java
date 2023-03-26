package org.example;

import org.example.model.Record;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final String reportPath = "cdr.txt";
        ReportReader reportReader = new ReportReader();
        ReportWriter reportWriter = new ReportWriter();
        CostCalculator costCalculator = new CostCalculator();
        Map<String, List<Record>> dataFromReport = reportReader.readReport(reportPath);
        for (String number : dataFromReport.keySet()) {
            costCalculator.calculateCost(dataFromReport.get(number).get(0).getTariffIndex(), dataFromReport.get(number));
        }
        reportWriter.writeReports(dataFromReport);
    }
}
