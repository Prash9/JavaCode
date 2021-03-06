import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class DriverClass extends Configured implements Tool{
						 	
	public int run(String args[]) throws Exception {
		if(args.length!=2) {
			System.out.println("enter 2 paths");
			System.exit(0);
		}
		
		
		
		Job job = Job.getInstance(getConf());
		job.setJobName("InvertedIndexSearch");
		job.setJarByClass(DriverClass.class);
		
		job.getConfiguration().set("mapreduce.output.textouptut.separator","|");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
//		MultipleInputs.addInputPath(job, new Path(args[0]), FileInputFormat.class, MapperClass.class);
		job.setMapperClass(MapperClass.class);
		job.setReducerClass(SearchReducerClass.class);
		job.setCombinerClass(SearchReducerClass.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		FileInputFormat.setInputDirRecursive(job,true);
	
		return job.waitForCompletion(true)?0:1 ;
		
	}
	public static void main(String[] args) throws Exception{
		int exitCode  = ToolRunner.run(new DriverClass(), args);
		System.exit(exitCode);
		
	}

}
