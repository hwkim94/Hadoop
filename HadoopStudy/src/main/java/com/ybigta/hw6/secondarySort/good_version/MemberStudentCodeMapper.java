package com.ybigta.hw6.secondarySort.good_version;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public  class MemberStudentCodeMapper extends Mapper<LongWritable, Text, MemberStudentCodeWritable, IntWritable> {
    private String[] split;
    private String memberCode, studentCode;
    private MemberStudentCodeWritable outputKey = new MemberStudentCodeWritable();
    private IntWritable outputValueONE = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(value.toString().contains("class")){ return; }

        split = value.toString().split(",");
        memberCode = split[0].trim();
        studentCode = split[1].trim();

        outputKey.set(Integer.parseInt(memberCode.substring(0, memberCode.length()-1)), Integer.parseInt(studentCode));
        context.write(outputKey, outputValueONE);

    }
}