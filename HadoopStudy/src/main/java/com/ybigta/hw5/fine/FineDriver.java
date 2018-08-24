package com.ybigta.hw5.fine;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class FineDriver extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new FineDriver(), args);
        System.exit(result);
    }

    @Override
    public int run(String[] args) throws Exception {
        String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();

        if (otherArgs.length != 2) {
            System.err.println("Usage: AvgWordLengthDriver <input_path> <output_path>");
            System.exit(2);
        }

        Job job = Job.getInstance(getConf(), "Fine Count");

        job.setJarByClass(FineDriver.class);
        job.setMapperClass(FineMapper.class);
        job.setCombinerClass(FineReducer.class);
        job.setPartitionerClass(MonthPartioner.class);
        job.setReducerClass(FineReducer.class);
        job.setNumReduceTasks(12);

        //mapper의 output key, value
        job.setMapOutputKeyClass(DateNameWritable.class);
        job.setMapOutputValueClass(LongWritable.class);

        //reducer의 output key, value
        job.setOutputKeyClass(DateNameWritable.class);
        job.setOutputValueClass(LongWritable.class);

        // 프로그램의 입출력 데이터 타입
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        job.waitForCompletion(true);

        return 0;
    }
}
