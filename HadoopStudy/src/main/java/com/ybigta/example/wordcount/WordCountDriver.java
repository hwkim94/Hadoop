package com.ybigta.example.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountDriver extends Configured implements Tool { //JOB 환경 설정 및 클러스터로의 전송을 담당

    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new WordCountDriver(), args); //??
        System.exit(result);
    }

    @Override
    public int run(String[] args) throws Exception {
        String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs(); //명령어 parsing

        if (otherArgs.length != 2) {
            System.err.println("Usage: WordCountDriver <input_path> <output_path>");
            System.exit(2);
        }

        Job job = Job.getInstance(getConf(), "word count"); //getConf() : 실행시점의 하둡 전역의 설정 가져오기
                                                                         //JOB 생성
        job.setJarByClass(WordCountDriver.class); // 해당 class 파일이 속한 jar 파일을 job을 jar파일로 설정
        job.setMapperClass(TokenizerMapper.class); // 어떤 클래스가 각각 어떤 역할을 하는지 설정
        job.setCombinerClass(IntSumReducer.class); // Reducer의 역할을 미리 해두는 것, 신중하게 만드어야 함
        job.setReducerClass(IntSumReducer.class);

        job.setInputFormatClass(TextInputFormat.class); // 입출력할 데이터 타입을 설정
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setOutputKeyClass(Text.class); // reducer의 output key와 value를 설정
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0])); //입출력 디렉토리 설정
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        job.waitForCompletion(true);

        return 0;
    }
}