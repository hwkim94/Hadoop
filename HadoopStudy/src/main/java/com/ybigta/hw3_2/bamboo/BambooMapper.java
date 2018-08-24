package com.ybigta.hw3_2.bamboo;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.StringTokenizer;

public class BambooMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text word = new Text();
    private Text info = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //json 형식으로 글 하나씩 input으로 들어오므로 tokenizing해줄 필요가 없음

        JSONParser parser = new JSONParser();
        JSONObject jsonObject ;
        JSONObject likes;
        JSONObject summary;
        long likesCount;
        String message;
        String time;
        String date;

        try {
            jsonObject = (JSONObject) parser.parse(value.toString());

            //날짜, 시간
            time = (String) jsonObject.get("created_time");
            date = time.split("T")[0];

            //좋아요
            likes = (JSONObject) jsonObject.get("likes");
            summary = (JSONObject) likes.get("summary");
            likesCount = (long) summary.get("total_count");

            //내용
            message = (String) jsonObject.get("message");

            word.set(date);
            info.set(time + "::" + likesCount + "::" + message);

            context.write(word, info);

        } catch (ParseException e) { e.printStackTrace(); }
    }
}
