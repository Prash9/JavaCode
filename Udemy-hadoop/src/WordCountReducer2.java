
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer2 extends Reducer<IntWritable,Text,IntWritable,Text>{

	public void reduce(IntWritable key ,Iterable<Text> value_list,Context context) throws IOException, InterruptedException {
		
		for(Text value :value_list) {
			context.write(key, value);
		}
		
		
	}
	
}
