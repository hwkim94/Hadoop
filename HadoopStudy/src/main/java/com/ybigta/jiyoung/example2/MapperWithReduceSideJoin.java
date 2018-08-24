package com.ybigta.jiyoung.example2;

import java.io.IOException;

import com.ybigta.jiyoung.example1.AirlinePerformanceParser;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.ybigta.jiyoung.example1.AirlinePerformanceParser;

public class MapperWithReduceSideJoin extends Mapper<LongWritable, Text, TaggedKey, Text> {

    TaggedKey outputKey = new TaggedKey();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        AirlinePerformanceParser parser = new AirlinePerformanceParser(value);

        outputKey.setCarrierCode(parser.getUniqueCarrier());
        outputKey.setTag(1);
        context.write(outputKey, value);
    }
}
