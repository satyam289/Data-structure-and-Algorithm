#include <iostream>
#include <algorithm>

using namespace std;

class AddBinary {
   public:
        string add(string a, string b){
            string ans = "";
            int ansLen = 0, carry = 0, sum;
            int i = a.length() - 1, j = b.length() - 1;
            while (i >= 0 || j >= 0 || carry){
                sum = carry;
                if(i >= 0){
                    sum += (a[i] - '0');
                }
                if(j >= 0){
                    sum += (b[j] - '0');
                }
                ans.push_back((char)('0' + sum % 2));
                carry = sum / 2;
                i--;
                j--;
            }
            reverse(ans.begin(), ans.end());
            return ans;
        }
};


/* java Implementaion
package Math_Bit;

import java.util.Arrays;

// https://www.interviewbit.com/problems/add-binary-strings/
class addBinary {
    
    public String add(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int i = A.length() - 1;
        int j = B.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0)
                sum += A.charAt(i) - '0';
            if (j >= 0)
                sum += B.charAt(j) - '0';
            sb.append(sum % 2);  // or sum & 1;
            carry = sum / 2; // or sum >> 1
            i--;
            j--;
        }
        if (carry != 0)
            sb.append(carry);
        return sb.reverse().toString();
    }

    public String add2(String a, String b) {
        StringBuilder result = new StringBuilder();
        if (a.length() > b.length()) {
            char[] zeros = new char[a.length() - b.length()];
            Arrays.fill(zeros, '0');
            b = new String(zeros) + b;
        } else if (b.length() > a.length()) {
            char[] zeros = new char[b.length() - a.length()];
            Arrays.fill(zeros, '0');
            a = new String(zeros) + a;
        }

        boolean carry = false, A1 = false, B1 = false;

        for (int i = a.length() - 1; i >= 0; i--) {
            if (a.charAt(i) == '1')
                A1 = true;
            if (b.charAt(i) == '1')
                B1 = true;
            boolean sum = A1 ^ B1 ^ carry;
            if (sum) {
                result.append('1');
            } else {
                result.append('0');
            }
            int c = 0;
            if (A1) c++;
            if (B1) c++;
            if (carry) c++;

            if (c >= 2) {
                carry = true;
            } else {
                carry = false;
            }
        }
        if (carry) {
            result.append("1");
        }
        result.reverse();
        return result.toString();
    }
}
*/