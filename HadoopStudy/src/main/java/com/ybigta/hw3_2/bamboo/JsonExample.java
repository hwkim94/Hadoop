package com.ybigta.hw3_2.bamboo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonExample {

    private static final String TEST_JSON_STRING = "{\"message\": \"연대숲 #55128번째 외침:\\n\\n2017. 7. 28 오전 1:47:49\\n\\n너와의 만남은 아름답고 달콤한 짧은 단편소설이었어.\\n이렇게 내 책장에 넣어둘게.\\n\\n내가 종종 꺼내 읽으며 추억할때 너도 같은 페이지를 읽고 있었으면 좋겠다.\", \"created_time\": \"2017-07-30T04:59:36+0000\", \"likes\": {\"data\": [{\"id\": \"207174549715135\", \"name\": \"이홍경\"}], \"paging\": {\"cursors\": {\"before\": \"MjA3MTc0NTQ5NzE1MTM1\", \"after\": \"MjA3MTc0NTQ5NzE1MTM1\"}, \"next\": \"https://graph.facebook.com/v2.10/180446095498086_694904747385549/likes?access_token=EAACEdEose0cBAIpadvZC2bVIklIt2Uffe8IjKXGSuh6QNgmhQnmw0NkmMRHkNeeQhAukhdGRZCWRpfzaAZBtraZCbX3QgtJkqmpEn8vSBeEwUN9bhjNVwbHyCvVqTCxEZArkk6O333ZCsPyZAVFnwlrdtYrwX25zoB0WpWYugdmF470BeHJ8tpqzJQ5P8M7WGgZD&summary=true&limit=1&after=MjA3MTc0NTQ5NzE1MTM1\"}, \"summary\": {\"total_count\": 12, \"can_like\": true, \"has_liked\": false}}, \"id\": \"180446095498086_694904747385549\"}";

    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(TEST_JSON_STRING);

        String time = (String) jsonObject.get("created_time");
        JSONObject likes = (JSONObject) jsonObject.get("likes");
        JSONObject summary = (JSONObject) likes.get("summary");
        long likesCount = (long) summary.get("total_count");
        String message = (String) jsonObject.get("message");

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(jsonObject);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(time);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(likes);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(summary);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(likesCount);
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println(message);
        System.out.println("------------------------------------------------------------------------------------------------");
    }
}
