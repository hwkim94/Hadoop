package com.ybigta.project.pubg_large;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;

public class PUBGMatrixReducer extends Reducer<PUBG_Mat_Key_CustomWritable, PUBG_Mat_Val_CustomWritable, PUBG_Mat_Key_CustomWritable, Text> {

    private Text K;
    private Text outputvalue = new Text();
    private String type;
    private HashMap<Integer, Float> left = new HashMap<Integer, Float>();
    private HashMap<Integer, Float> right = new HashMap<Integer, Float>();
    private long count = 0;
    private float result = 0;

    @Override
    protected void reduce(PUBG_Mat_Key_CustomWritable key, Iterable<PUBG_Mat_Val_CustomWritable> values, Context context) throws IOException, InterruptedException {
        K = key.getMatrix();

        for (PUBG_Mat_Val_CustomWritable value : values){
            type = value.getType().toString().trim();
            if(type.equals("XT")){
                left.put(Integer.parseInt(value.getCol().toString().trim()), Float.parseFloat(value.getValue().toString().trim()));
            }else {
                right.put(Integer.parseInt(value.getRow().toString().trim()), Float.parseFloat(value.getValue().toString().trim()));
            }

            count++;
        }

        for(int i=0;i<left.size();i++){
            result += left.get(i) * right.get(i);
        }

        outputvalue.set(","+String.valueOf(result));
        context.write(key, outputvalue);
    }
}
