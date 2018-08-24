package com.ybigta.hw6.secondarySort.bad_version;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SumReducer extends Reducer<MemberStudentCodeWritable, IntWritable, MemberStudentCodeWritable, Text> {
    private int sum = 0;
    private MemberStudentCodeWritable outputKey = new MemberStudentCodeWritable();
    private Text outputValue = new Text();

    @Override
    protected void reduce(MemberStudentCodeWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //Grouping 작업을 group-key 기준으로 하도록 Compartor를 설정했으므로 composite-key 별로 연산을 해야함
        //이렇게 해버리면 같은 group-key에 해당하는 것들이 모두 같이 연산이 됨
        for(IntWritable value : values){
            sum += value.get();
        }

        outputValue.set(sum+"*");
        context.write(key, outputValue);
    }


}
