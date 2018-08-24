package com.ybigta.hw6.secondarySort.test2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SumReducer extends Reducer<MemberStudentCodeWritable, IntWritable, MemberStudentCodeWritable, Text> {
    private int cnt=0;
    private int memberCode, studentCode;


    private MemberStudentCodeWritable outputKey = new MemberStudentCodeWritable();
    private Text outputValue = new Text();


    @Override
    protected void reduce(MemberStudentCodeWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        // reducer에 partition이 어떻게 들어오는지 확인하기 위한 패키지2
        // 도대체 key에 아무런 조치도 취해주지 않았는데, 알아서 바뀌는 것이 이상하여
        // 혹시 새로운 outputKey를 set하지 않아도 같은 결과가 나오는지 확인하기 위하여 시도
        // 결과 : test1과 같은 결과

        cnt=0;
        for (IntWritable value : values) {
            outputValue.set(cnt+"*");
            context.write(key, outputValue);

            cnt++;
        }
    }
}
