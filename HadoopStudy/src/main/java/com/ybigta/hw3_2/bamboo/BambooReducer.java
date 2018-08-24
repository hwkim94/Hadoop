package com.ybigta.hw3_2.bamboo;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class BambooReducer extends Reducer<Text, Text, Text, Text> {
    private Text result = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        String winner = "";
        int num= 0;

        for (Text val : values) {
            String temp = val.toString();
            String[] temp2 = temp.split("::");

            if(Integer.parseInt(temp2[1]) > num){
                winner = temp2[0] + ". " + temp2[1] + ", " + temp2[2];
            }
        }

        result.set(winner);
        context.write(key, result);
    }
}
