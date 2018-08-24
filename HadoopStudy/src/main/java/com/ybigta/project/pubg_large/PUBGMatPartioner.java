package com.ybigta.project.pubg_large;

import com.ybigta.hw5.fine.DateNameWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PUBGMatPartioner extends Partitioner<PUBG_Mat_Key_CustomWritable, PUBG_Mat_Val_CustomWritable>{
	private String matrix;

	@Override
	public int getPartition(PUBG_Mat_Key_CustomWritable key, PUBG_Mat_Val_CustomWritable value, int numReduceTasks) {
		matrix = key.getMatrix().toString();
		if (matrix.equals("XTX")){ return 0;}
		else {return 1;}
	}
}
