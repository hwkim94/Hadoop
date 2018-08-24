package com.ybigta.project.pubg_large;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
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
    private static final String OUTPUT_PATH1 = "/user/hyunwoo/project/temp_large/temp1";
    private static final String OUTPUT_PATH2 = "/user/hyunwoo/project/temp_large/temp2";

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

        ///////////////////////////////////////////
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
        job1.setOutputValueClass(Text.class);

        // 프로그램의 입출력 데이터 타입
        job1.setInputFormatClass(TextInputFormat.class);
        job1.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job1, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job1, new Path(OUTPUT_PATH1));

        job1.waitForCompletion(true);

        ///////////////////////////////////////////
        //Job 2
        Job job2 = Job.getInstance(getConf(), "Job2 PUBG");

        job2.setJarByClass(PUBGDriver.class);
        job2.setMapperClass(PUBGMatrixMapper.class);
        job2.setReducerClass(PUBGMatrixReducer.class);
        job2.setPartitionerClass(PUBGMatPartioner.class);
        job2.setNumReduceTasks(2);

        //mapper의 output key, value
        job2.setMapOutputKeyClass(PUBG_Mat_Key_CustomWritable.class);
        job2.setMapOutputValueClass(PUBG_Mat_Val_CustomWritable.class);

        //reducer의 output key, value
        job2.setOutputKeyClass(PUBG_Mat_Key_CustomWritable.class);
        job2.setOutputValueClass(Text.class);

        // 프로그램의 입출력 데이터 타입
        job2.setInputFormatClass(TextInputFormat.class);
        job2.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job2, new Path(OUTPUT_PATH1));
        FileOutputFormat.setOutputPath(job2, new Path(OUTPUT_PATH2));

        job2.waitForCompletion(true);

        ///////////////////////////////////////////
        //Job 3
        Job job3 = Job.getInstance(getConf(), "Job3 PUBG");

        job3.setJarByClass(PUBGDriver.class);
        job3.setMapperClass(PUBGMatrixMapper2.class);
        job3.setReducerClass(PUBGMatrixReducer2.class);

        //mapper의 output key, value
        job3.setMapOutputKeyClass(IntWritable.class);
        job3.setMapOutputValueClass(PUBG_Mat_Val_CustomWritable.class);

        //reducer의 output key, value
        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(PUBG_LC_CustomWritable.class);

        // 프로그램의 입출력 데이터 타입
        job3.setInputFormatClass(TextInputFormat.class);
        job3.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job3, new Path(OUTPUT_PATH2));
        FileOutputFormat.setOutputPath(job3, new Path(otherArgs[1]));

        job3.waitForCompletion(true);

        return 0;
    }
}
