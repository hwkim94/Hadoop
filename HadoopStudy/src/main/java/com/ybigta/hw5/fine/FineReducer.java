package com.ybigta.hw5.fine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Date;

public class FineReducer extends Reducer<DateNameWritable, LongWritable, DateNameWritable, LongWritable> {
    private Long sum = 0L;
    private LongWritable result = new LongWritable();

    @Override
    protected void reduce(DateNameWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        for(LongWritable value : values){
            sum += value.get();
        }
        result.set(sum);

        context.write(key, result);
    }
}
