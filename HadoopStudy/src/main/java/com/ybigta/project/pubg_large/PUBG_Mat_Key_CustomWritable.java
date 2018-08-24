package com.ybigta.project.pubg_large;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class PUBG_Mat_Key_CustomWritable implements WritableComparable<PUBG_Mat_Key_CustomWritable> {
    private Text matrix;
	private IntWritable row;
    private IntWritable col;

    public PUBG_Mat_Key_CustomWritable() {
    	set(new Text(), new IntWritable(), new IntWritable());
    }
    public PUBG_Mat_Key_CustomWritable(String matrix, int row, int col) {
        set(new Text(matrix), new IntWritable(row), new IntWritable(col));
    }
    public PUBG_Mat_Key_CustomWritable(Text matrix, IntWritable row, IntWritable col) {
        set(matrix, row, col);
    }

    public void set(Text matrix, IntWritable row, IntWritable col) {
        this.matrix = matrix;
        this.row = row;
        this.col = col;
    }
    public void set(String matrix, int row, int col){
        this.matrix = new Text(matrix);
        this.row = new IntWritable(row);
        this.col = new IntWritable(col);
    }
    public void set(String matrix, String row, String col){
        this.matrix = new Text(matrix);
        this.row = new IntWritable(Integer.parseInt(row));
        this.col = new IntWritable(Integer.parseInt(col));
    }

    public Text getMatrix() {
        return matrix;
    }
    public IntWritable getRow() {
        return row;
    }
    public IntWritable getCol() {
        return col;
    }



    @Override
    public void readFields(DataInput dataInput) throws IOException {
        matrix.readFields(dataInput);
    	row.readFields(dataInput);
    	col.readFields(dataInput);
    }

	@Override
    public void write(DataOutput dataOutput) throws IOException {
        matrix.write(dataOutput);
        row.write(dataOutput);
        col.write(dataOutput);
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PUBG_Mat_Key_CustomWritable textPair = (PUBG_Mat_Key_CustomWritable) o;
        return Objects.equals(row, textPair.row) && Objects.equals(col, textPair.col) && Objects.equals(matrix, textPair.matrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrix, row, col);
    }

    @Override
    public int compareTo(PUBG_Mat_Key_CustomWritable o) {
        int cmp;

        cmp = matrix.compareTo(o.matrix);
        if (cmp != 0) { return cmp; }

        cmp = row.compareTo(o.row);
        if (cmp != 0) { return cmp; }

        return col.compareTo(o.col);
    }

    @Override
    public String toString() {
        return matrix + "," + row + "," + col;
    }
}
