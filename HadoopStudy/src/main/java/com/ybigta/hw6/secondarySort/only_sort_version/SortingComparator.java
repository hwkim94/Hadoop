package com.ybigta.hw6.secondarySort.only_sort_version;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SortingComparator extends WritableComparator {
    public SortingComparator() { super(MemberStudentCodeWritable.class, true); }

    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        MemberStudentCodeWritable m1 = (MemberStudentCodeWritable)w1;
        MemberStudentCodeWritable m2 = (MemberStudentCodeWritable)w2;

        return m1.compareTo(m2);
    }
}