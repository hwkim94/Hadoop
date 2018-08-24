package com.ybigta.example.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    // LongWritable : key값 하지만 무시
    // 앞의 두개는 input 뒤의 두개는 output
    // 앞의 Text는 한 문장
    // 뒤의 Text는 tokenized 된 단어
    // default로 shuffle과정이 되어 있음(같은 key값으로 묶음)

    private final static IntWritable ONE = new IntWritable(1); //어차피 단어는 1로 매핑되기 때문에 상수 1로 지정
    private Text word = new Text(); // 하둡의 기본 자료형

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString()); //단어단위로 쪼갬
        while (itr.hasMoreTokens()) {
            word.set(itr.nextToken());
            context.write(word, ONE);
        }
    }
}
