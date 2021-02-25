package DynamicProgramming;

import java.util.Arrays;

public class WordBreak {

	public static void main(String[] args) {
		findAnagram("cat".toCharArray(),0,2);
	}

	
	public static void findAnagram(char [] ch, int start, int end ) {
		if(start == end)
	 		 System.out.println(Arrays.toString(ch));
		
		if(start < end) {
			for(int i=start; i<=end; i++) {
			 swap(ch,start,i);	
			 findAnagram(ch, start+1, end);
	         swap(ch, i, start);
			}   
	 	 }
 }
	public static void swap(char [] ch , int first, int second ) {
		char temp = ch[first];
		ch[first]=ch[second];
		ch[second]=temp;
	}
}
