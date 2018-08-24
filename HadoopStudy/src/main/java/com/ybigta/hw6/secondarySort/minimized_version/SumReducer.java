package com.ybigta.hw6.secondarySort.minimized_version;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SumReducer extends Reducer<MemberStudentCodeWritable, IntWritable, MemberStudentCodeWritable, Text> {
    private int sum;
    private MemberStudentCodeWritable outputKey = new MemberStudentCodeWritable();
    private Text outputValue = new Text();

    @Override
    protected void reduce(MemberStudentCodeWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        sum=0;

        // GroupingComparator를 지정하지 않았으므로 reduce의 parame으로 composite-key가 들어온다.
        // 현재 우리가 하려는 것은 composite-key끼리 연산을 해주는 것이므로, 그냥 별다른 코딩없이 처리해주면 된다.
        for(IntWritable value : values){
            sum += value.get();
        }

        outputValue.set(sum+"*");
        context.write(key, outputValue);
    }
}
