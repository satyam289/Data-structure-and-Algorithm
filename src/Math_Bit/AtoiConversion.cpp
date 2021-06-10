#include <vector>
#include <iostream>

using namespace std;
/**
 * https://www.interviewbit.com/problems/atoi/
 *   1) Discards all leading whitespaces
 *   2) Sign of the number
 *   3) Overflow
 *   4) Invalid input 
 */
class AtoiConversion {

    int atoi(const string &A)
    {
        long long x;
        int mul = 1;
        int i = 0;
        while (A[i] == ' ')
        {
            i++;
        }
        if (A[i] == '-')
        {
            mul = -1;
            i++;
        }
        if (A[i] == '+')
        {
            i++;
        }
        x = 0;
        while (i < A.size())
        {
            if (isdigit(A[i]))
                x = x * 10 + (A[i] - '0');
            else
                break;

            if((mul ==1 && x > 1LL* INT_MAX) || (mul == -1 && x>abs(1LL * INT_MIN)))
                break;

            i++;
        }

        x *= mul;
        x = max(x, 1LL * INT_MIN);
        x = min(x, 1LL * INT_MAX);
        return x;
    }
};

/*    
    public static int atoi(final String in){ 
        int start = 0;
        while( in.charAt(start) == ' '){
            start++;
        }
        int negative = 1;
        if(in.charAt(start) == '-'){
           negative = -1;
           start++;
        }
        if(in.charAt(start) == '+' ){
            start++;
        }
        long result = 0;
        for(int i = start ; i< in.length(); i++){
            int val = in.charAt(i) - '0';
            if(val >= 0 && val <= 9){
                 result = result*10 + val;
                 if(result > Integer.MAX_VALUE){
                     return Integer.MAX_VALUE;
                 }
            }else{
                break;
            }
        }
        result = negative * result;
        if(result < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        return (int)result;
    } 
    */
