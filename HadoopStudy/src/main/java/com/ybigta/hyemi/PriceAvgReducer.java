package com.ybigta.hyemi;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

import java.io.IOException;

public class PriceAvgReducer extends Reducer<Text, IntWritable, Text, DoubleWritable> {
	private DoubleWritable result = new DoubleWritable();

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        int sum = 0;
        int count = 0;
        for (IntWritable val : values) {
            sum += val.get();
            count += 1;
        }
        
        double avg = sum/(double)count;
        result.set(avg);
        context.write(key, result);
    }
}
