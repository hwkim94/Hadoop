package com.ybigta.project.pubg_large;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public  class PUBGMatrixMapper extends Mapper<LongWritable, Text, PUBG_Mat_Key_CustomWritable, PUBG_Mat_Val_CustomWritable> {

    private PUBG_Mat_Key_CustomWritable outputkey = new PUBG_Mat_Key_CustomWritable();
    private PUBG_Mat_Val_CustomWritable outputvalue = new PUBG_Mat_Val_CustomWritable();
    private String input;
    private String[] input_array;

    private String Matrix;
    private int row;
    private int col;
    private float val;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        input = value.toString();
        input_array = input.split(",");

        if (input_array.length != 4){return;}

        try {
            Matrix = input_array[0].trim();
            row = Integer.parseInt(input_array[1].trim());
            col = Integer.parseInt(input_array[2].trim());
            val = Float.parseFloat(input_array[3].trim());
        }catch (Exception e){return;}

        if(Matrix.equals("XT")){
            for(int i=0;i<5;i++){
                outputkey.set("XTX",row,i);
                outputvalue.set(Matrix, row, col, val);
                context.write(outputkey, outputvalue);
            }

            outputkey.set("XTy",row, 0);
            outputvalue.set(Matrix, row, col, val);
            context.write(outputkey, outputvalue);

        } else if(Matrix.equals("X")){
            for(int i=0;i<5;i++){
                outputkey.set("XTX",i,col);
                outputvalue.set(Matrix, row, col, val);
                context.write(outputkey, outputvalue);
            }
        } else{
            for(int i=0;i<5;i++){
                outputkey.set("XTy",i,col);
                outputvalue.set(Matrix, row, col, val);
                context.write(outputkey, outputvalue);
            }
        }
    }
}