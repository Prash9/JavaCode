package task.friend_recommeder;

import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;

public class RecommenderDriver extends Configured implements Tool{

	public int run(String args[]) throws Exception {
		
		if(args.length!= 2) {
			System.out.println("enter 2 paths");
			System.exit(0);
		}
		
		Job job1 = Job.getInstance(getConf());
		FileInputFormat.addInputPath(job1, new Path(args[0]));
		
		return 0;
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
	}

}
