package org.example.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Record {

    private String callType;
    private String number;
    private LocalDateTime callStartTime;
    private LocalDateTime callEndTime;
    private Duration duration;
    private String tariffIndex;
    private double cost;

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(LocalDateTime callStartTime) {
        this.callStartTime = callStartTime;
    }

    public LocalDateTime getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(LocalDateTime callEndTime) {
        this.callEndTime = callEndTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getTariffIndex() {
        return tariffIndex;
    }

    public void setTariffIndex(String tariffIndex) {
        this.tariffIndex = tariffIndex;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "|     " + getCallType() +
                "    | " + getCallStartTime().format(formatter) +
                " | " + getCallEndTime().format(formatter) +
                " | " + String.format("%02d:%02d:%02d", getDuration().getSeconds() / 3600,
                (getDuration().getSeconds() % 3600) / 60,
                (getDuration().getSeconds() % 60)) +
                " |  " + getCost() + " |";
    }
}
