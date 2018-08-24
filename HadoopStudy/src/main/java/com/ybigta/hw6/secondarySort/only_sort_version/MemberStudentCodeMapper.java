package com.ybigta.hw6.secondarySort.only_sort_version;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public  class MemberStudentCodeMapper extends Mapper<LongWritable, Text, MemberStudentCodeWritable, Text> {
    private String[] split;
    private String memberCode, studentCode, name;
    private MemberStudentCodeWritable outputKey = new MemberStudentCodeWritable();
    private Text outputValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(value.toString().contains("class")){ return; }

        split = value.toString().split(",");
        memberCode = split[0].trim();
        studentCode = split[1].trim();
        name = split[2].trim();

        outputKey.set(Integer.parseInt(memberCode.substring(0, memberCode.length()-1)), Integer.parseInt(studentCode));
        outputValue.set(name);
        context.write(outputKey, outputValue);

    }
}