package com.ybigta.hw6.secondarySort.test;

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

        // reducer에 partition이 어떻게 들어오는지 확인하기 위한 패키지
        // 돌려본 결과, GroupingComparator를 지정한 경우 reduce 함수는 group-key마다 각각 적용이 된다.
        // 또한, key값에 아무런 연산도 취해주지 않았지만 value에 해당하는 값에 맞게 알아서 바뀌는 걸 알 수 있다.
        // 출력값 : 13기 12학번 0
        //          13기 13학번 1
        //          13기 14학번 2
        //          13기 15학번 3
        //          13기 15학번 4
        //          13기 15학번 5
        //          14기 10학번 0
        //          14기 10학번 1
        //          14기 11학번 2
        //          15기 9학번 0

        cnt=0;
        memberCode = key.getMemberCode().get();
        studentCode = key.getStudentCode().get();

        for (IntWritable value : values) {
            outputKey.set(key.getMemberCode(), key.getStudentCode());
            outputValue.set(cnt+"*");
            context.write(outputKey, outputValue);

            cnt++;
        }
    }
}
