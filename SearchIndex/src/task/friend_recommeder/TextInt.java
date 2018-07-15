package task.friend_recommeder;

import task.bigramcount.TextPair;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class TextInt implements WritableComparable<TextInt> {

	private Text first;
	private IntWritable second;
	
	public TextInt() {
		set(new Text(),new IntWritable());
	}

	
	public TextInt(String first, int second) {
		set(new Text(first),new IntWritable(second));
	}
	
	public TextInt(Text first,IntWritable second) {
		set(first,second);
	}
	
	public void set(Text first,IntWritable second) {
		this.first = first;
		this.second = second;
	}
	
	public Text getFirst() {
		return this.first;
	}

	public IntWritable getSecond() {
		return this.second;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.first.readFields(in);
		this.second.readFields(in);
	}


	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		this.first.write(out);
		this.second.write(out);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof TextInt) {
			TextInt ti  = (TextInt) o;
			return first.equals(ti.first) && second.equals(ti.second);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return first.hashCode()*163 + second.hashCode();
				
	}
	
	@Override
	public int compareTo(TextInt o) {
		// TODO Auto-generated method stub	
		return second.compareTo(o.second);
	}
	
	@Override
	public String toString(){
		return first.toString() +"\t"+ second.get();
	}
}
