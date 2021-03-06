package task.bigramcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BigramCountReducer extends Reducer<TextPair,IntWritable,Text,IntWritable>{

	@Override
	public void reduce(TextPair key,Iterable<IntWritable> value ,Context context) throws IOException,InterruptedException{
		int count = 0;
		for(IntWritable i :value) {
			count  += i.get();
			
		}
		context.write(new Text(key.toString()), new IntWritable(count));
		
		
	}
	
	
}
