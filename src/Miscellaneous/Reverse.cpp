#include <iostream>
#include <vector>

using namespace std;

// https://www.interviewbit.com/problems/reverse-the-string/
class Reverse
{

    void reverseWords(string &A)
    {
        int n = A.length();
        vector<string> curr;
        string res, temp;
        for (int i = 0; i < n; i++)
        {
            if (A[i] == ' ' && A[i + 1] != ' ')
            {
                curr.push_back(temp);
                temp = "";
            }
            else if (A[i] == ' ' && A[i + 1] == ' ')
                continue;
            else
            {
                temp += A[i];
            }
        }
        curr.push_back(temp);
        for (int i = curr.size() - 1; i > 0; i--)
        {
            res += curr[i] + " ";
        }
        res += curr[0];
        A = res;
    }

    void reverseWords(string &a)
    {
        int n = a.size();
        int i = 0, in = 0;
        while (i < n)
        {
            int start;
            if (a[i] != ' ')
            {
                start = i;
                int end = start;
                i++;
                while (i < n && a[i] != ' ')
                {
                    end++;
                    i++;
                }
                while (start <= end)
                {
                    char temp = a[start];
                    a[start] = a[end];
                    a[end] = temp;
                    start++;
                    end--;
                }
            }
            else
                i++;
        }
        //cout << a << endl;
        int start = 0, end = n - 1;
        while (start < end)
        {
            char temp = a[start];
            a[start] = a[end];
            a[end] = temp;
            start++;
            end--;
        }
        i = 0;
        while (i < n)
        {
            while (i < n && a[i] == ' ')
                i++;
            if (i != n && in != 0)
                a[in++] = ' ';
            while (i < n && a[i] != ' ')
            {
                a[in] = a[i];
                in++;
                i++;
            }
        }
        a.erase(a.begin() + in, a.end());
    }
};
/*
package Miscellaneous;

public class Reverse {
    
    public String reverseWords2(String a) {
	    String[] words = a.split(" ");
	    StringBuilder stringBuilder = new StringBuilder();
	    for(int i = words.length - 1; i >= 0; i--) {
	        stringBuilder.append(words[i].trim());
	        
	        if(i != 0) {
	            stringBuilder.append(" ");
	        }
	    }
	    return stringBuilder.toString();
	}

    public String reverseWords(String A) {
	    char [] array = A.toCharArray();
	    int i;
	    int n = A.length();
	    
	    for (i = 0; i < n; i++) {
	        while (i < n && array[i] == ' ')
	            i++;
	        int start = i;
	        int end = -1;
	        while (i < n && array[i] != ' ')
	            i++;
	        end = i - 1;
	        if (end < start)
	            break;
	       
	        reverse(array, start, end);
	    }
	    
	    reverse(array, 0, n - 1);
	    
	    return removeExtraSpaces(array);
	}
	
	public String removeExtraSpaces(char [] array) {
	    int n = array.length;
	    int index = 0;
	    boolean cond = false;
	    
	    for (int i = 0; i < n;) {
	        
	        if (array[i] != ' ') {
	            array[index] = array[i];
	            index++;
	            cond = true;
	        } else {
	            if (cond)
	                array[index++] = ' ';
	            cond = false;
	        }
	        
	        i++;
	    }
	    
	    if (index - 1 >= 0 && index - 1 < n && array[index - 1] == ' ')
	        index--;
	    
	    return new String(array, 0, index);
	    
	}
	
	
	public void reverse(char [] array, int start, int end) {
	    char temp;
	    int i;
	    
	    for (i = 0; i < (end - start + 1) / 2; i++) {
	        temp = array[start + i];
	        array[start + i] = array[end - i];
	        array[end - i] = temp;
	    }
	    
	}
}
*/