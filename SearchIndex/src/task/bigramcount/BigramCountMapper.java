package task.bigramcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BigramCountMapper extends Mapper<IntWritable,Text,TextPair,IntWritable> {

	private Text currentWord = null;
	private Text lastWord = null;
	
	@Override
	public void map(IntWritable key,Text value,Context context) throws IOException, InterruptedException {
		
		String line = value.toString();
		line = line.replace(".","");
		line = line.replace(",", "");
		
		for(String word : line.split(" ")) {
			if(lastWord == null) {
				lastWord.set(word);
				
			}
			else {
				currentWord.set(word);
				context.write(new TextPair(currentWord,lastWord), new IntWritable(1));
				lastWord.set(word);
			}	
			
		}
	
	}
	
	
}
