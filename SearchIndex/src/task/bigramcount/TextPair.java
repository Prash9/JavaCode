package task.bigramcount;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextPair implements WritableComparable<TextPair> {

	private Text first;
	private Text second;
	
//	constructors start ************************ 
	public TextPair(){
	
		set(new Text(),new Text());
		
	}
		
	public TextPair(Text first,Text second){
			set(first,second);
	}

	public TextPair(String first, String second){
		set(new Text(first),new Text(second));		
	}
	
//	constructors end ************************
	
//	overriding functions***********
	
	@Override
	public void readFields(DataInput in) throws IOException {
		this.first.readFields(in);
		this.second.readFields(in);
	}
	@Override
	public void write(DataOutput out) throws IOException {
		this.first.write(out);
		this.second.write(out);
	}  
	@Override
	public int compareTo(TextPair t) {
		int cmp = this.first.compareTo(t.first);
		if (cmp!=0) {
			return cmp;
		}
		
		return this.second.compareTo(t.second);
		
	}
	
	@Override
	public int hashCode() {  
		return this.first.hashCode()*163 + this.second.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		
		if(o instanceof TextPair) {
			TextPair tp = (TextPair)o;
			return this.first.equals(tp.first) && this.second.equals(tp.second);
			
		} 
		
		return false;
	}
	@Override
	public String toString(){
		return first +"\t"+ second;
	}
	
//	overriding functions end***********
	
	
//	getters and setters start ******************************
	public void set(Text t , Text t2) {
		this.first = t;
		this.second = t2;
	}
	
	public Text getFirst() {
		return this.first;
	}

	public Text getSecond() {
		return this.second;
	}

//	getters and setters end******************************
	
	
}
