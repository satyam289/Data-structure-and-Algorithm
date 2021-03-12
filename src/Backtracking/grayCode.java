package Backtracking;

import java.util.ArrayList;
import java.util.Vector;

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

    //https://www.geeksforgeeks.org/backtracking-approach-generate-n-bit-gray-codes/
   
    static int num;

    static ArrayList<Integer> grayCodeBackTracking(int n){
        ArrayList<Integer> res = new ArrayList<>();
        num = 0;
        grayCodeUtil(res, n);
        return res;
    }

    private static void grayCodeUtil(ArrayList<Integer> res, int n){
        if(n == 0){
          res.add(num);
          return;
        }
        grayCodeUtil(res, n-1);
        num = num ^ (1 << (n-1));
        grayCodeUtil(res, n-1);
    }

    public static void main(String [] args){
        ArrayList<String> ans = solution(4);
        for(String str : ans){
            System.out.print(str +" ");
        }

        ArrayList<Integer> ans2 = grayCodeBackTracking(3);
        for(int val : ans2){
            System.out.print(val +" ");
        }
    }
}
