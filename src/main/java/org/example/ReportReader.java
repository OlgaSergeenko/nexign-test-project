package org.example;

import org.example.model.Record;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportReader {

    private final Map<String, List<Record>> reports = new HashMap<>();

    public Map<String, List<Record>> readReport(String reportPath) {
        try {
            String content = Files.readString(Paths.get(reportPath));
            String[] lines = content.split("\\n");
            for (String rec : lines) {
                String[] line = rec.split(",");
                Record record = recordFromLine(line);
                if (reports.containsKey(record.getNumber())) {
                    List<Record> recordsInReport = reports.get(record.getNumber());
                    recordsInReport.add(record);
                } else {
                    List<Record> recordsInReport = new ArrayList<>();
                    recordsInReport.add(record);
                    reports.put(record.getNumber(), recordsInReport);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Невозможно прочитать файл");
        }
        return reports;
    }

    private Record recordFromLine(String[] line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Record record = new Record();
        record.setCallType(line[0].trim());
        record.setNumber(line[1].trim());
        record.setCallStartTime(LocalDateTime.parse(line[2].trim(), formatter));
        record.setCallEndTime(LocalDateTime.parse(line[3].trim(), formatter));
        record.setTariffIndex(line[4].trim());
        record.setDuration(Duration.between(LocalDateTime.parse(line[2].trim(), formatter),
                LocalDateTime.parse(line[3].trim(), formatter)));

        return record;
    }
}
