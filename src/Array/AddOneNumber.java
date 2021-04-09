package Array;

import java.util.ArrayList;
import java.util.Collections;

// https://www.interviewbit.com/problems/add-one-to-number/

public class AddOneNumber {
    
    public ArrayList<Integer> plusOne(ArrayList<Integer> a){

        Collections.reverse(a);
        //removing zeros at the front
        int n = a.size();
        int i = 0;
        while(i < n && a.get(i) == 0){ 
            a.remove(i);
        }
        int carry = 1;
        int sum;
        for(i = a.size()-1; i>=0; i--){
            sum = a.get(i) + carry;
            a.set(i, sum % 10);
            carry = sum / 10;
            if(carry == 0){
                break;
            }
        }
        // if carry is still left
        if(carry != 0){
            a.add(0, carry);
            carry = carry/10;
        }
        Collections.reverse(a); // revert original
        return a;
    }

    // another ways
    public int[] plusOne(int[] A) {
        int num = 0;
        int n = A.length;
        for (int i=0; i<n; i++){
            num = num*10 + A[i];
        }
        num = num + 1;
        int len = String.valueOf(num).length();
        int [] result = new int[len];
        int k = len-1;
        while(num > 0){
            int rem = num % 10;
            result[k] = rem;
            num = num / 10;
            k--;
        }
        return result;
    }
}
