
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.*;

public class WordCountPartitioner extends Partitioner<IntWritable,Text>{
	
	@Override
	public int getPartition(IntWritable key, Text value, int numOfReducer) {
		if (key.get()<=10) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
