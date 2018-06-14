import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;



public class WordCountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

	public void map(LongWritable key,Text value, Context context) throws IOException,InterruptedException{
		String v=value.toString();
		
		for(String i:v.split(" ")) {
			if(i.length()>0) {
				context.write(new Text(i), new IntWritable(1));
			}
				
		}
	}

}
