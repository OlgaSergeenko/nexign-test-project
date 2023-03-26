package org.example;

import org.example.model.Record;

import java.util.List;
import java.util.stream.Collectors;

public class CostCalculator {

    public List<Record> calculateCost(String tariff, List<Record> records) {
        records = records.stream().filter(record -> record.getCallType().equals("01")).collect(Collectors.toList());
        double cost;
        double pricePerMin;
        int limit;
        double pricePerMinOverLimit;
        long totalSeconds = 0;
        long minutesOverLimit;
        long minOver = 0;
        switch (tariff) {
            case "11":
                limit = 100;
                pricePerMin = 0.5;
                pricePerMinOverLimit = 1.5;
                for (Record rec : records) {
                    totalSeconds = +(rec.getDuration().getSeconds());
                    double minutes = totalSeconds / 60.0;
                    double min = Math.ceil(minutes);
                    if (min > limit) {
                        minutesOverLimit = (long) (Math.ceil(totalSeconds / 60.0) - limit - minOver);
                        minOver = +minutesOverLimit;
                        cost = minutesOverLimit * pricePerMinOverLimit;
                        rec.setCost(cost);
                    } else {
                        cost = min * pricePerMin;
                        rec.setCost(cost);
                    }
                }
                break;

            case "03":
                pricePerMin = 1.5;
                for (Record rec : records) {
                    totalSeconds = +(rec.getDuration().getSeconds());
                    double min = Math.ceil(totalSeconds / 60.0);
                    rec.setCost(min * pricePerMin);
                }
                break;

            case "06":
                limit = 300;
                pricePerMinOverLimit = 1.0;
                for (Record rec : records) {
                    totalSeconds = +(rec.getDuration().getSeconds());
                    double min = Math.ceil(totalSeconds / 60.0);
                    if (min < limit) {
                        rec.setCost(0.0);
                    } else {
                        minutesOverLimit = (long) (Math.ceil(totalSeconds / 60.0) - limit - minOver);
                        minOver = +minutesOverLimit;
                        cost = minutesOverLimit * pricePerMinOverLimit;
                        rec.setCost(cost);
                    }
                }
                break;
        }
        return records;
    }

    public double calculateTotal(List<Record> records) {
        double sum = 0;
        if (records.get(0).getTariffIndex().equals("06")) {
            sum = 100;
            for (Record rec : records) {
                sum += rec.getCost();
            }
        } else {
            for (Record rec : records) {
                sum += rec.getCost();
            }
        }
        return sum;
    }
}
