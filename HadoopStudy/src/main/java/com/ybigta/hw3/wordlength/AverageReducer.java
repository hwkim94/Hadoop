package com.ybigta.hw3.wordlength;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
    private DoubleWritable result = new DoubleWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        double sum = 0;
        int cnt = 0;

        for (IntWritable val : values) {
            sum += val.get();
            cnt++;
        }
        result.set(sum/cnt);

        context.write(key, result);
    }
}
