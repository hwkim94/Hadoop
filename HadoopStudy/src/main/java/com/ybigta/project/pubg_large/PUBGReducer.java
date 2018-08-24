package com.ybigta.project.pubg_large;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PUBGReducer extends Reducer<IntWritable, PUBG_Info_CustomWritable, Text, Text> {

    private Text outputkey = new Text();
    private Text outputvalue = new Text();

    private int num_data = 0;

    @Override
    protected void reduce(IntWritable key, Iterable<PUBG_Info_CustomWritable> values, Context context) throws IOException, InterruptedException {

        for (PUBG_Info_CustomWritable value : values){
            outputkey.set("X");
            context.write(outputkey, outputvalue);
            outputvalue.set("," + num_data + ",0," + value.getPlayer_dist_ride().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + num_data + ",1," + value.getPlayer_dist_walk().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + num_data + ",2," + value.getPlayer_dmg().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + num_data + ",3," + value.getPlayer_kills().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + num_data + ",4," + 1);
            context.write(outputkey, outputvalue);

            outputkey.set("XT");
            outputvalue.set("," + "0," + num_data + "," + value.getPlayer_dist_ride().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + "1," + num_data + "," + value.getPlayer_dist_walk().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + "2," + num_data + "," + value.getPlayer_dmg().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + "3," + num_data + "," + value.getPlayer_kills().toString());
            context.write(outputkey, outputvalue);
            outputvalue.set("," + "4," + num_data + "," +1);
            context.write(outputkey, outputvalue);

            outputkey.set("y");
            outputvalue.set("," + num_data + ",0," + value.getPlayer_survive_time().toString());
            context.write(outputkey, outputvalue);

            num_data ++;
        }

    }

}
