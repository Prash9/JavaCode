package task.friend_recommeder;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import task.bigramcount.TextPair;
import org.apache.hadoop.mapreduce.Reducer;


public class Reducer1 extends Reducer<TextPair,IntWritable,TextPair,IntWritable> {

	public void reduce(TextPair key,Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
		int sum = 0;
		int prod = 1;
		for(IntWritable i : values) {
			sum += i.get();
			prod *= i.get();
		}
		
		if(prod != 0) {
			context.write(key, new IntWritable(sum));
		}
	}
	
}
