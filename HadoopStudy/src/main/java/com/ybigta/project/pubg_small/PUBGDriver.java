package com.ybigta.project.pubg_small;

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

public class PUBGDriver extends Configured implements Tool {
    private static final String OUTPUT_PATH = "intermediate_output";

    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new PUBGDriver(), args);
        System.exit(result);
    }

    @Override
    public int run(String[] args) throws Exception {
        String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();

        if (otherArgs.length != 2) {
            System.err.println("Usage: AvgWordLengthDriver <input_path> <output_path>");
            System.exit(2);
        }

        //Job 1
        Job job1 = Job.getInstance(getConf(), "Job1 PUBG");

        job1.setJarByClass(PUBGDriver.class);
        job1.setMapperClass(PUBGMapper.class);
        job1.setReducerClass(PUBGReducer.class);

        //mapper의 output key, value
        job1.setMapOutputKeyClass(IntWritable.class);
        job1.setMapOutputValueClass(PUBG_Info_CustomWritable.class);

        //reducer의 output key, value
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(PUBG_LC_CustomWritable.class);

        // 프로그램의 입출력 데이터 타입
        job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job1, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job1, new Path(otherArgs[1]));

        job1.waitForCompletion(true);


        return 0;
    }
}
