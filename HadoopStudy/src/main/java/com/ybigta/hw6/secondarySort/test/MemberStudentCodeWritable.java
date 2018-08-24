package com.ybigta.hw6.secondarySort.test;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class MemberStudentCodeWritable implements WritableComparable<MemberStudentCodeWritable> {
	private IntWritable memberCode;
    private IntWritable studentCode;
    
    public MemberStudentCodeWritable() {
    	set(new IntWritable(), new IntWritable());
    }

    public MemberStudentCodeWritable(int memberCode, int studentCode) {
        set(new IntWritable(memberCode), new IntWritable(studentCode));
    }

    public MemberStudentCodeWritable(IntWritable memberCode, IntWritable studentCode) {
        set(memberCode, studentCode);
    }

    public void set(IntWritable memberCode, IntWritable studentCode) {
        this.memberCode = memberCode;
        this.studentCode = studentCode;
    }

    public void set(int memberCode, int studentCode){
        this.memberCode = new IntWritable(memberCode);
        this.studentCode = new IntWritable(studentCode);
    }

    public IntWritable getMemberCode() { return memberCode; }
    public IntWritable getStudentCode() { return studentCode; }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
    	memberCode.readFields(dataInput);
    	studentCode.readFields(dataInput);
    }

	@Override
    public void write(DataOutput dataOutput) throws IOException {
        memberCode.write(dataOutput);
        studentCode.write(dataOutput);
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberStudentCodeWritable textPair = (MemberStudentCodeWritable) o;
        return Objects.equals(memberCode, textPair.memberCode) && Objects.equals(studentCode, textPair.studentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberCode, studentCode);
    }

    @Override
    public int compareTo(MemberStudentCodeWritable o) {
        int cmp = memberCode.compareTo(o.memberCode);

        if (cmp != 0) { return cmp; }
        return studentCode.compareTo(o.studentCode);
    }

    @Override
    public String toString() {
        return "*" + memberCode.get() + "기 "+ studentCode.get() + "학번 :";
    }


}
