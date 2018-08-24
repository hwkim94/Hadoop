package com.ybigta.project.pubg_small;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Random;

public  class PUBGMapper extends Mapper<LongWritable, Text, IntWritable, PUBG_Info_CustomWritable > {

    private IntWritable outputkey = new IntWritable(1);
    private PUBG_Info_CustomWritable outputvalue = new PUBG_Info_CustomWritable();
    private String input;
    private String[] input_array;

    private Random random = new Random();
    int flag;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 첫줄일 경우 제외시킴
        if (key.get() == 0 && value.toString().contains("date")){ return; }

        // 100분의 1개의 데이터만 랜덤하게 추출
        flag = random.nextInt(10)+1;
        if (flag != 5){return;}

        // input값 분리
        input = value.toString();
        input_array = input.split(",");

        // player ID가 없을 경우 버림, 근데 상관은 없을 듯
        //if (input_array[11].trim().equals("")){ return; }

        // match mode tpp만 사용
        if (!input_array[3].trim().equals("tpp")){ return; }

        // output
        outputvalue.set(input_array[7].trim(), input_array[8].trim(), input_array[9].trim(), input_array[10].trim(), input_array[12].trim());
        context.write(outputkey, outputvalue);
    }
}