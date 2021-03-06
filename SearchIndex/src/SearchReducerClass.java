

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;

import java.io.IOException;


public class SearchReducerClass extends Reducer<Text,Text,Text,Text> {

	@Override
	public void reduce(Text key,Iterable<Text> values,Context context) throws IOException, InterruptedException {
		
		StringBuilder sb = new StringBuilder();
		
		for(Text value : values) {
			
			sb.append(value.toString());
			if (values.iterator().hasNext()) {
				sb.append("|");
			}
			
		}
		
		context.write(key , new Text(sb.toString()));
		
	}
	
	
	
}
