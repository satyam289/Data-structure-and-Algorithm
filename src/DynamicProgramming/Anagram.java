package DynamicProgramming;

import java.util.Arrays;

public class Anagram {

	public static void main(String[] args) {
       String s="cat";
       getanagram(s);
       
	}
	
	private static void getanagram(String  s) {
		getanagram(s.toCharArray(), 0 , s.length()-1);
	}
	
	private static void getanagram(char[] cs, int start, int end) {
		
		if(start == end) {
		    	System.out.println(Arrays.toString(cs));
		}
		for(int i=start; i<=end; i++) {
		    swap(cs,start, i);
			getanagram(cs,start+1,end);
			swap(cs,i, start);
		}
	}
   
	private static void swap(char[] cs, int first, int second) {
		char temp = cs[first];
		cs[first]=cs[second];
		cs[second]=temp;
	}

}
