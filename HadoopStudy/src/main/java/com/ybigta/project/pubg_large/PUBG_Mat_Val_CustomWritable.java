package com.ybigta.project.pubg_large;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PUBG_Mat_Val_CustomWritable implements Writable {
    private Text type;
    private IntWritable row;
    private IntWritable col;
    private FloatWritable value;


    //constructor
    public PUBG_Mat_Val_CustomWritable(){
        set(new Text(), new IntWritable(), new IntWritable(), new FloatWritable());
    }

    public PUBG_Mat_Val_CustomWritable(String type, int row, int col, float value){
        set(new Text(type), new IntWritable(row), new IntWritable(col), new FloatWritable(value));
    }

    //setter
    public void set(Text type, IntWritable row, IntWritable col, FloatWritable value) {
        this.type = type;
        this.row = row;
        this.col = col;
        this.value = value;
    }
    public void set(String type, int row, int col, float value) {
        this.type = new Text(type);
        this.row = new IntWritable(row);
        this.col = new IntWritable(col);
        this.value = new FloatWritable(value);
    }
    public void set(String type, String row, String col, String value) {
        this.type = new Text(type);
        this.row = new IntWritable(Integer.parseInt(row));
        this.col = new IntWritable(Integer.parseInt(col));
        this.value = new FloatWritable(Float.parseFloat(value));
    }

    //getter
    public Text getType() {
        return type;
    }
    public IntWritable getRow() {
        return row;
    }
    public IntWritable getCol() {
        return col;
    }
    public FloatWritable getValue() {
        return value;
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        type.readFields(dataInput);
        row.readFields(dataInput);
        col.readFields(dataInput);
        value.readFields(dataInput);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        type.write(dataOutput);
        row.write(dataOutput);
        col.write(dataOutput);
        value.write(dataOutput);
    }

}
