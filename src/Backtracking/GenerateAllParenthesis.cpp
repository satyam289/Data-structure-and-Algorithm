#include <iostream>
#include <vector>

using namespace std;

// https://www.interviewbit.com/problems/generate-all-parentheses-ii/
class GenerateParenthesis {
    
    void utilsgenparen(vector<string> sol, string sub,int open,int close,int n){
        if(sub.size() == 2*n){
            sol.push_back(sub);
            //cout << sub << '';
            return;
        }

        if(open < n){
            //sub.push_back('(');
            utilsgenparen(sol, sub, open +1, close, n);
            sub.pop_back();
        }
        if(open > close){
            sub.push_back(')');
            utilsgenparen(sol, sub, open, close+1, n);
            sub.pop_back();
        }
        return;
    }

    vector<string> generatePar(int A)
    {
        vector<string> result;
        string sub;
        utilsgenparen(result, sub, 0, 0, A);
        return result;
    }
};

/*
package Backtracking;

import java.util.ArrayList;
import java.util.Collections;

public class GenerateAllParenthesis {
    
    // Time Complexity : 2^n
    public ArrayList<String> generateParenthesis(int A) {
        char[] arr = new char[2 * A];
        ArrayList<String> result = new ArrayList<>();
        generateParenthesisRec(arr, A, 0, 0, 0, result);
        Collections.reverse(result);
        return result;
    }

    private void generateParenthesisRec(char[] arr, int A, int pos, int open, int close, ArrayList<String> result) {

        if (close == A) {
            result.add(new String(arr));
            return;
        }
        if (close < open) {
            arr[pos] = ')';
            generateParenthesisRec(arr, A, pos + 1, open, close + 1, result);
        }
        if (open < A) {
            arr[pos] = '(';
            generateParenthesisRec(arr, A, pos + 1, open + 1, close, result);
        }
    }

}
*/