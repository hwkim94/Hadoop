package com.ybigta.skeleton;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class SkeletonCustomWritable implements WritableComparable<SkeletonCustomWritable> {
	private Text text1; // Writable의 기본자료형으로는 string, int, boolean도 가능하지만, 주로 Text, intWritable과 같은 하둡 자료형을 많이 사용
    private Text text2;
    
    public SkeletonCustomWritable() {
    	set(new Text(), new Text());
    }

    public SkeletonCustomWritable(String firstText, String secondText) {
        set(new Text(firstText), new Text(secondText));
    }

    public SkeletonCustomWritable(Text firstText, Text secondText) {
        set(firstText, secondText);
    }

    public void set(Text text1, Text text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    public Text gettext1() {
        return text1;
    }
    public Text gettext2() {
        return text2;
    }

    //readFields와 write는 직렬화를 어떻게 시킬지를 정의
    @Override
    public void readFields(DataInput dataInput) throws IOException {
    	text1.readFields(dataInput);
    	text2.readFields(dataInput);
    }

	@Override
    public void write(DataOutput dataOutput) throws IOException {
        text1.write(dataOutput);
        text2.write(dataOutput);
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkeletonCustomWritable textPair = (SkeletonCustomWritable) o;

        return Objects.equals(text1, textPair.text1) && Objects.equals(text2, textPair.text2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text1, text2);
    }

    @Override
    public int compareTo(SkeletonCustomWritable o) {
        int cmp = text1.compareTo(o.text1);

        if (cmp != 0) { return cmp; }
        return text2.compareTo(o.text2);
    }

    @Override
    public String toString() {
    	// TO-DO implements//
        return "return here";
    }

}
