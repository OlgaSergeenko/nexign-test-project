package org.example.comparator;


import org.example.model.Record;

import java.util.Comparator;

public class TariffIndexComparator implements Comparator<Record> {
    @Override
    public int compare(Record r1, Record r2) {
        if (Integer.parseInt(r1.getCallType()) > Integer.parseInt(r2.getCallType())) {
            return 1;
        } else if (Integer.parseInt(r1.getCallType()) < Integer.parseInt(r2.getCallType())) {
            return -1;
        } else {
            return 0;
        }
    }
}
