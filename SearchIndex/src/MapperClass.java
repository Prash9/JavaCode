


import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class MapperClass extends Mapper<LongWritable,Text,Text,Text>{

	private Text word = new Text();
	private Text filename = new Text();
	
	@Override
	public void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException{
		FileSplit currentSplit = (FileSplit)context.getInputSplit();
		String file_name = currentSplit.getPath().getName();
		filename = new Text(file_name);
		
		String line  = value.toString();
		StringTokenizer tokenizer  = new StringTokenizer(line);
		while(tokenizer.hasMoreTokens()) {
			word.set(tokenizer.nextToken());
			context.write(word , filename);
		}
		
		
	}
	
}
