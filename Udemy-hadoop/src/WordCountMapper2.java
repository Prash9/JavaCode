
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper2 extends Mapper<Text,Text,IntWritable,Text>{

	public void map(Text key,Text value,Context context) throws IOException, InterruptedException {
		
		IntWritable count = new IntWritable();
		
		int int_count = Integer.parseInt(value.toString());
		count.set(int_count);
		context.write(count , key );
	}
	
	
}
