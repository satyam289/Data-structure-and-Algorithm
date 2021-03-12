package Backtracking;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/generate-n-bit-gray-codes/
public class grayCode {

    public static ArrayList<String> solution(int n){
       if(n == 1){
           ArrayList<String> base = new ArrayList<>();
           base.add("0");
           base.add("1");
           return base;
       }
       ArrayList<String> recbase = solution( n -1);
       ArrayList<String> result = new ArrayList<>();
       for(int i=0; i< recbase.size(); i++){
           result.add("0"+ recbase.get(i));
       }
       for(int j=recbase.size() -1; j  >=0; j--){
           result.add("1"+ recbase.get(j));
       }
       return result;
    }

    public static void main(String [] args){
        ArrayList<String> ans = solution(4);
        for(String str : ans){
            System.out.print(str +" ");
        }
    }
}
