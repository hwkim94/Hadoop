package com.ybigta.hw3_2.bamboo;

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

public class BambooDriver extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        /*
        Configuration conf = new Configuration();
        conf.set("mapred.job.tracker", "local");
        conf.set("fs.default.name", "file:///");
        int result = ToolRunner.run(conf, new BambooDriver(), args);
        */

        int result = ToolRunner.run(new Configuration(), new BambooDriver(), args);
        System.exit(result);
    }

    @Override
    public int run(String[] args) throws Exception {
        String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();

        if (otherArgs.length != 2) {
            System.err.println("Usage: AvgWordLengthDriver <input_path> <output_path>");
            System.exit(2);
        }

        Job job = Job.getInstance(getConf(), "Job Name");

        job.setJarByClass(BambooDriver.class);
        job.setMapperClass(BambooMapper.class);
        job.setReducerClass(BambooReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        job.waitForCompletion(true);

        return 0;
    }
}
