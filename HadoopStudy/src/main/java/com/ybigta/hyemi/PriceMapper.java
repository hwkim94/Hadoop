package com.ybigta.hyemi;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import java.io.IOException;

public class PriceMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text word = new Text();
    private IntWritable result = new IntWritable();
    int price, percent, point, origin;

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] splited = value.toString().split(" ");
        String name = splited[0];
        price = 0;

        switch(splited.length) {
            case 2:
                price = Integer.parseInt(splited[1]);
                break;

            case 3:
                origin = Integer.parseInt(splited[1]);

                if (splited[2].contains("%")) {
                    percent = Integer.parseInt(splited[2].substring(0, splited[2].indexOf("%")));
                    price = (int)(origin*((100-percent)/100.0));
                }else if(splited[2].contains("포")){
                    point = Integer.parseInt(splited[2].substring(0, splited[2].indexOf("포")));
                    price = origin - point;
                }
        }

        result.set(price);
        word.set(name);
        context.write(word, result);
    }

}
