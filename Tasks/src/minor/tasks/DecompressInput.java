package minor.tasks;
import java.util.regex.Pattern;

import java.util.regex.Matcher;
public class DecompressInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(decompress("(18x9)(3x2)YQT(5X7)ABABA"));
		
	}
	
	public static long decompress(String main_string) {
		long main_count = 0;
		int  i =0;
		while(i<main_string.length()){
			if(main_string.charAt(i) == '(') {
				int[] data_array = new int[3];
				int main_string_length = main_string.length();
				data_array = evaluate_expression(main_string.substring(i,main_string_length));
				i = i + data_array[2]; 
	            main_count += substring_itr(data_array[0],data_array[1],main_string.substring(i+1,main_string_length));
	            i = i + data_array[0]; 
			}
			else {
				main_count += 1;
			}
			i = i+1;    	
		}
		return main_count;
	}
	
	public static int[] evaluate_expression(String sub_string) {
		int[] data_array = new int[3];
		
		Pattern pattern = Pattern.compile("^([(](\\d+)[xX](\\d+)[)])?");
		Matcher m = pattern.matcher(sub_string);
		while(m.find()) {
			data_array[0] = Integer.parseInt(m.group(2));
			data_array[1] = Integer.parseInt(m.group(3));
			data_array[2] = m.end()-1;
		}
		return data_array;
	}
	
	public static long substring_itr(int str_itr,int itr,String sub_string) {
		int string_length = 0;
		for (int index = 0;index<itr;index++) {
			string_length += decompress(sub_string.substring(0,str_itr));
		}
		return string_length;
	}
}
