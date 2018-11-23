package Array;

import java.text.DecimalFormat;

public class EnglishNumberToWords {

	  private static final String[] tensNames = {
	    "",
	    " ten",    //making array allign , no use
	    " twenty",
	    " thirty",
	    " forty",
	    " fifty",
	    " sixty",
	    " seventy",
	    " eighty",
	    " ninety"
	  };

	  private static final String[] numNames = {
	    "",
	    " one",
	    " two",
	    " three",
	    " four",
	    " five",
	    " six",
	    " seven",
	    " eight",
	    " nine",
	    " ten",
	    " eleven",
	    " twelve",
	    " thirteen",
	    " fourteen",
	    " fifteen",
	    " sixteen",
	    " seventeen",
	    " eighteen",
	    " nineteen"
	  };
	  
	  
	  public static String lessThanThousand(int number){
		 String sofar="";
		 if( (number%100) < 20){
			 sofar=numNames[number%100];
			 number=number/100;
		 }
		 else{
			 sofar=numNames[number%10];
			 number=number/10;
			 sofar=tensNames[number%10]+" "+sofar;
			 number=number/10;
		 }
		  if(number>0){
			  sofar=numNames[number]+" hundred "+sofar;
		  }
		 
	    return sofar;
	  
	  }
	  
	  public static String convertUpToBillion(long number){   // 0 to 999 999 999 999
		  
		  String result="";
		  String convert="";
		  if(number==0)
			  return "zero";
		  if(number<0 || number>999999999999L){
			  System.err.println("out of range");
			  return "";
		  }
		  String mask = "000000000000";    // 1000 million= 1 billion ; 1 million =10lakh or 1000 thousand (9 zeros in 1 billion)
		  DecimalFormat d=new DecimalFormat(mask);
		  convert=d.format(number);
		  System.err.println(convert);
		    int billionN=Integer.parseInt(convert.substring(0,3));
		    int millionN=Integer.parseInt(convert.substring(3,6));
		    int thousandN=Integer.parseInt(convert.substring(6,9));
		    int hundredN=Integer.parseInt(convert.substring(9,12));
		    switch(billionN){
		    case 0:result +="";break;
		    default: result=lessThanThousand(billionN)+" billion, ";
		    }
		    switch(millionN){
		    case 0:result +="";break;
		    default: result +=lessThanThousand(millionN)+" million, ";
		    }
		    switch(thousandN){
		    case 0:result +="";break;
		    default: result +=lessThanThousand(thousandN)+" thousand, ";
		    }
		    switch(hundredN){
		    case 0:result +="";break;
		    default: result +=lessThanThousand(hundredN)+" ";
		    }
		  
		  
		  return result;
	  }
	  public static void main(String args[]){
		 // System.out.println(lessThanThousand(999));
		 // System.out.println(Long.MAX_VALUE);
		  //System.out.println(convertUpToBillion(89333764414L));
		  System.out.println(convertUpToBillion(80006000001L));
		  //System.out.println(convertUpToBillion(0));
	  }
}