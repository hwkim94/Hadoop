package com.ybigta.hw6.secondarySort.only_sort_version;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MemberPartioner extends Partitioner<MemberStudentCodeWritable, Text>{
	private int criteria;

	@Override
	public int getPartition(MemberStudentCodeWritable key, Text value, int numReduceTasks) {
		criteria = key.getMemberCode().hashCode();
		return criteria % numReduceTasks;
	}
}
