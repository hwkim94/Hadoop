package com.ybigta.hw3.wordlength;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class LetterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private String temp;
    private Text word = new Text();
    private IntWritable num;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());

        while (itr.hasMoreTokens()) {
            temp = itr.nextToken();
            word.set(temp.substring(0,1));
            num = new IntWritable(temp.length());

            context.write(word, num);
        }
    }


}
