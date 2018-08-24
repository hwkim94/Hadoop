package com.ybigta.hw6.secondarySort.test;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupingComparator extends WritableComparator {
    public GroupingComparator() { super(MemberStudentCodeWritable.class, true); }

    @SuppressWarnings("rawtypes")
    @Override
    public int compare(WritableComparable w1, WritableComparable w2) {
        MemberStudentCodeWritable m1 = (MemberStudentCodeWritable)w1;
        MemberStudentCodeWritable m2 = (MemberStudentCodeWritable)w2;

        return m1.getMemberCode().compareTo(m2.getMemberCode());
    }
}