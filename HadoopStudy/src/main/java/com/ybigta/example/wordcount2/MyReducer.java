package com.ybigta.example.wordcount2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable outputvalue = new IntWritable();

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;

        for(IntWritable value : values){
            sum += value.get();
        }
        outputvalue.set(sum);
        context.write(key, outputvalue);
    }
}
