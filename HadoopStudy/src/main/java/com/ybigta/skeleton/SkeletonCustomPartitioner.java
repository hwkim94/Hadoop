package com.ybigta.skeleton;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class SkeletonCustomPartitioner extends Partitioner<Text, LongWritable>{
	// TO-DO implements//
	
	@Override
	public int getPartition(Text key, LongWritable value, int numReduceTasks) {
		// TO-DO implements//
		// return reducer number
		// 파티션의 개수는 리듀서의 개수와 같고, 파티셔너의 개수는 매퍼의 개수와 같음
		int temp = 0;
		return temp;
	}
}
