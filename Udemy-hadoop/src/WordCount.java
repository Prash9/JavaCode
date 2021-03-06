
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.jobcontrol.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class WordCount extends Configured implements Tool {

	public int run(String args[]) throws Exception {
		if(args.length!=2) {
			System.out.println(args[0]+""+args[1]+" "+args[2]+" "+args.length);
			System.out.println("enter 2 paths");
			System.exit(0);
		}
		
		JobControl jbcontrol = new JobControl("JobChain");
		
		Job job= Job.getInstance(getConf());
		job.setJobName("wordcount");
		job.setJarByClass(WordCount.class);
				
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);	
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		ControlledJob controlledJob1 = new ControlledJob(getConf());
		controlledJob1.setJob(job);
		jbcontrol.addJob(controlledJob1);
		
		
		Job job2 = Job.getInstance(getConf());
		job2.setJobName("wordcount2");
		job2.setJarByClass(WordCount.class);
	
		FileInputFormat.addInputPath(job2, new Path(args[1]+"/part*"));
		FileOutputFormat.setOutputPath(job2, new Path(args[1]+"/wc_final"));
		
		job2.setMapperClass(WordCountMapper2.class);
		job2.setReducerClass(WordCountReducer2.class);
		
		job2.setInputFormatClass(KeyValueTextInputFormat.class);
		job2.setPartitionerClass(WordCountPartitioner.class);
		job2.setNumReduceTasks(2);
		job2.setOutputKeyClass(IntWritable.class);
		job2.setOutputValueClass(Text.class);
		
		ControlledJob controlledJob2 = new ControlledJob(getConf());
		controlledJob2.setJob(job2);
		controlledJob2.addDependingJob(controlledJob1);
		jbcontrol.addJob(controlledJob2);
		
		Thread thread = new Thread(jbcontrol);
		thread.start();
		
		while(!jbcontrol.allFinished()) {
			
			System.out.println("Jobs in waiting state: " + jbcontrol.getWaitingJobList().size());  
		    System.out.println("Jobs in ready state: " + jbcontrol.getReadyJobsList().size());
		    System.out.println("Jobs in running state: " + jbcontrol.getRunningJobList().size());
		    System.out.println("Jobs in success state: " + jbcontrol.getSuccessfulJobList().size());
		    System.out.println("Jobs in failed state: " + jbcontrol.getFailedJobList().size());
			
			
			Thread.sleep(5000);
			
		}
		
		return job2.waitForCompletion(true)?0:1 ;
		
	}
	
	public static void main(String[] args) throws Exception{
		int exitCode  = ToolRunner.run(new WordCount(), args);
		System.exit(exitCode);
		
	}

}
