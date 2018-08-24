package com.ybigta.hw5.fine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public  class FineMapper extends Mapper<LongWritable, Text, DateNameWritable, LongWritable> {
    private String inputValue;
    private String[] split;
    private DateNameWritable dateNameWritable = new DateNameWritable();
    private LongWritable outputValue = new LongWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        inputValue = value.toString();
        split = inputValue.split(" ");

        dateNameWritable.set(split[0], split[1]);
        outputValue.set(Long.parseLong(split[2]));

        context.write(dateNameWritable, outputValue);

    }
}