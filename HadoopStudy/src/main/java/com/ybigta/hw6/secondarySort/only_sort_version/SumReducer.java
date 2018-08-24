package com.ybigta.hw6.secondarySort.only_sort_version;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SumReducer extends Reducer<MemberStudentCodeWritable, Text, MemberStudentCodeWritable, Text> {


    @Override
    protected void reduce(MemberStudentCodeWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        // 단지 sorting만 하는 것이므로 굳이 GroupingComparator를 지정해줄 필요가 없다.

        for(Text value : values){
            context.write(key, value);
        }
    }
}
