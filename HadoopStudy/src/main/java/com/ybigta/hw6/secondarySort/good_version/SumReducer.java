package com.ybigta.hw6.secondarySort.good_version;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SumReducer extends Reducer<MemberStudentCodeWritable, IntWritable, MemberStudentCodeWritable, Text> {
    private int sum;
    private int memberCode, studentCode;


    private MemberStudentCodeWritable outputKey = new MemberStudentCodeWritable();
    private Text outputValue = new Text();


    @Override
    protected void reduce(MemberStudentCodeWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        // test 결과, value값에 맞춰 알아서 key값이 바뀌는 것을 확인함
        // studentCode를 백업해두는 것은 studentCode가 바뀌었을 때,
        // if문에서 이전까지 쌓아왔던 sum을 해당 studentCOde로 저장해야하는데
        // key.getStudentCode()는 현재의 studentCode이므로 따로 백업을 해둬야 함

        sum=0;
        memberCode = key.getMemberCode().get();
        studentCode = key.getStudentCode().get();

        for (IntWritable value : values) {
            if (studentCode != key.getStudentCode().get()) {
                outputKey.set(key.getMemberCode(), new IntWritable(studentCode));
                outputValue.set(sum + "*");
                context.write(outputKey, outputValue);
                sum = 0;
            }

            studentCode = key.getStudentCode().get();
            sum += value.get();
        }

        outputKey.set(key.getMemberCode(), key.getStudentCode());
        outputValue.set(sum+"*");
        context.write(outputKey, outputValue);
    }
}
