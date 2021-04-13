#include <iostream>
using namespace std;

// https://www.interviewbit.com/problems/sorted-permutation-rank/
class SortedPermute{

    int fact(int val)
    {
        int ans = 1;
        if (val == 1 || val == 0)
            return 1;
        else
        {
            while (val > 0)
            {
                ans *= val;
                ans %= 1000003;
                val--;
            }
        }
    }

    int findRank(string str)
    {
        long long int sum = 0;
        for (int i = 0; i < str.length(); i++)
        {
            long long int count = 0;
            for (int j = i + 1; j < str.length(); j++)
            {
                if (str[j] < str[i])
                {
                    count++;
                }
            }
            sum += fact(str.length() - i - 1) * count / 1000003;
        }
    }
};


/*
package Array;

public class sortedPermutedRank {
 
    private static final int DIVISOR = 1000003;
    
    //optimised
    public static int findRank(String str) {
        if (str.length() == 1)
            return 1;
        int sortedPosition = getSortedPosition(str);
        int postRank = sortedPosition * factorial(str.length() - 1);
        return postRank + findRank(str.substring(1));
    }

    private static  int getSortedPosition(String str) {
        int count = 0;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) < str.charAt(0)) {
                count++;
            }
        }
        return count;
    }

    private static int factorial(int n) {
        int num = 1;
        for (int i = 1; i <= n; i++) {
            num *= i;
            num %= DIVISOR;
        }
        return num;
    }

    public static void main (String[] args){
        System.out.println(findRank("cba"));
    }
   
    //Navie way
    private int count = 0;
    public int getRank(char [] arr, int start, int end, String target){
        if(start == end){
            count++;
            if(String.valueOf(arr).equals(target)){
                return count;
            }
            return -1;
        }
        for(int i=start; i<end; i++){
            swap(arr, start, i);
            int ct = getRank(arr, start+1, end, target);
            if(ct != -1){
                return ct;
            }
            swap(arr, i, start);
        }
        return -1;
    }
    
    public void swap(char [] arr, int first, int second){
        char temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
*/