package com.ybigta.hw6.secondarySort.minimized_version;

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

public class SecondarySortDriver extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
        int result = ToolRunner.run(new Configuration(), new SecondarySortDriver(), args);
        System.exit(result);
    }

    @Override
    public int run(String[] args) throws Exception {
        String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();

        if (otherArgs.length != 2) {
            System.err.println("Usage: AvgWordLengthDriver <input_path> <output_path>");
            System.exit(2);
        }

        Job job = Job.getInstance(getConf(), "SecondarySort");

        job.setJarByClass(SecondarySortDriver.class);
        job.setMapperClass(MemberStudentCodeMapper.class);
        job.setPartitionerClass(MemberPartioner.class);
        job.setSortComparatorClass(SortingComparator.class);
        job.setReducerClass(SumReducer.class);
        job.setNumReduceTasks(1);

        job.setMapOutputKeyClass(MemberStudentCodeWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(MemberStudentCodeWritable.class);
        job.setOutputValueClass(Text.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

        job.waitForCompletion(true);

        return 0;
    }
}
