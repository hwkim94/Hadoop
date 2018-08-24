package com.ybigta.jiyoung.example2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class TaggedGroupKeyPartitioner extends Partitioner<TaggedKey, Text> {

    @Override
    public int getPartition(TaggedKey key, Text val, int numPartitions) {

        int hash = key.getCarrierCode().hashCode();
        int partition = hash % numPartitions;
        return partition;
    }
}