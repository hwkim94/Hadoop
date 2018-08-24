package com.ybigta.hw5.fine;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class DateNameWritable implements WritableComparable<DateNameWritable> {
	private Text date;
    private Text name;
    
    public DateNameWritable() {
    	set(new Text(), new Text());
    }

    public DateNameWritable(String date, String name) {
        set(new Text(date), new Text(name));
    }

    public DateNameWritable(Text date, Text name) {
        set(date, name);
    }

    public void set(Text date, Text name) {
        this.date = date;
        this.name = name;
    }

    public void set(String date, String name){
        this.date = new Text(date);
        this.name = new Text(name);
    }

    public Text getDate() { return date; }
    public Text getName() { return name; }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
    	date.readFields(dataInput);
    	name.readFields(dataInput);
    }

	@Override
    public void write(DataOutput dataOutput) throws IOException {
        date.write(dataOutput);
        name.write(dataOutput);
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateNameWritable textPair = (DateNameWritable) o;
        return Objects.equals(date, textPair.date) && Objects.equals(name, textPair.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, name);
    }

    @Override
    public int compareTo(DateNameWritable o) {
        int cmp = date.compareTo(o.date);

        if (cmp != 0) { return cmp; }
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "날짜: " + date.toString() + ", 이름: "+name.toString();
    }

    public  String[] toStringArray() {
        return new String[]{date.toString(), name.toString()};
    }
}
