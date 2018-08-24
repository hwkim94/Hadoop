package com.ybigta.jiyoung.example2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerWithReduceSideJoin extends Reducer<TaggedKey, Text, Text, Text> {
    private Text outputKey = new Text();
    private Text outputValue = new Text();

    public void reduce(TaggedKey key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iterator = values.iterator();
        Text carrierName = new Text(iterator.next());

        while (iterator.hasNext()) {
        Text record = iterator.next();

        outputKey.set(key.getCarrierCode());
        outputValue = new Text(carrierName.toString() + "\t" + record.toString());
        context.write(outputKey, outputValue);
        }
    }
}
