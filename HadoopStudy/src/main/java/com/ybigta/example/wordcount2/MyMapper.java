package com.ybigta.example.wordcount2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper <LongWritable, Text, Text, IntWritable> {
    private Text outputkey = new Text();
    private IntWritable outputvalue = new IntWritable(1);
    private String type;

    @Override
    public void setup(Context context){
        type = context.getConfiguration().get("type"); // hadoop jar myjar.jar MyDriver -D type=지각 input output
                                                        // hadoop jar myjar.jar MyDriver -D type=결석 input output
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");

        if (words[1].equals(type)) { //데이터가 바뀌면 계속 코드를 바꿔야하므로, 실행시 type 부분을 채워줌
            outputkey.set(words[0]);
            outputvalue.set(1);
            context.write(outputkey, outputvalue);
        }
    }

}
