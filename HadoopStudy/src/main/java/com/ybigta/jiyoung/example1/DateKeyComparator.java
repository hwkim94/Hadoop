package com.ybigta.jiyoung.example1;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class DateKeyComparator extends WritableComparator {
    protected DateKeyComparator() { super(DateKey.class, true); }

    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        DateKey k1 = (DateKey) w1;
        DateKey k2 = (DateKey) w2;

        int cmp = k1.getYear().compareTo(k2.getYear());
        if (cmp != 0) {
            return cmp;
        }

        return k1.getMonth() == k2.getMonth() ? 0 : (k1.getMonth() < k2.getMonth() ? -1 : 1);
    }
}
