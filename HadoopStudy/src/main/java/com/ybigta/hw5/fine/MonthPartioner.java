package com.ybigta.hw5.fine;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MonthPartioner extends Partitioner<DateNameWritable, LongWritable>{
	private int criteria;
	private String[] date;

	@Override
	public int getPartition(DateNameWritable key, LongWritable value, int numReduceTasks) {
		// return reducer number
		criteria = Integer.parseInt(key.getDate().toString().substring(5,7));

		return criteria - 1;
	}
}
