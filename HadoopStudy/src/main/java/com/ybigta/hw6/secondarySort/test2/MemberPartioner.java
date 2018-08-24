package com.ybigta.hw6.secondarySort.test2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MemberPartioner extends Partitioner<MemberStudentCodeWritable, IntWritable>{
	private int criteria;

	@Override
	public int getPartition(MemberStudentCodeWritable key, IntWritable value, int numReduceTasks) {
		criteria = key.getMemberCode().hashCode();
		return criteria % numReduceTasks;
	}
}
