package com.ybigta.example.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //처음 두개는 input 순서쌍
        //Iterable<IntWritable> 은 shuffle을 통해서 key 값이 같은 것끼리 같은 reducer에 넣어주는데, 그 때 형태가 <key, [1,1]> 로 들어옴. 만약 세개라면 <key, [1,1,1]이 됨>

        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result); // result에는 단어의 개수
    }
}
