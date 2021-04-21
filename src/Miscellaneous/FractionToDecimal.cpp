#include <string>
#include <map>

using namespace std;

class FractionToDecimal
{
    string fractionToDecimal(int A, int B)
    {
        map<int, int> lookup;
        long long x = A;
        long long y = B;
        string result;
        if ((x < 0 && y > 0) || (x > 0 && y < 0))
        {
            result = "-";
        }
        x = abs(x);
        y = abs(y);

        long long int base = x / y;
        long long int remaining = x % y;
        result += to_string(base);

        if (remaining == 0) {
            return result;
        } else {
            string decimalStr = ".";
            long long int res = remaining;
            while (lookup.find(res) == lookup.end() && res != 0)
            {
                decimalStr += to_string((10 * res) / y);
                lookup[res] = decimalStr.size();
                res = (10 * res) % y;
            }
            if (res != 0)
            {
                decimalStr.insert(lookup[res], "(");
                decimalStr += ")";
            }
            result += decimalStr;
            return result;
        }
    }

    string fractionToDecimal2(int numerator, int denominator)
    {
        if (numerator == 0)
            return "0";
        
        bool minus = ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) ? true : false;
        int64_t num = abs(int64_t(numerator));
        int64_t denom = abs(int64_t(denominator));
        int64_t div = num / denom;
        string ret;
        
        if (minus)
            ret.append("-");
        ret.append(to_string(div));
        
        int64_t remainder = num - div * denom;
        if (remainder == 0)
            return ret;

        ret.append(".");
        ssize_t dotPos = ret.length() - 1;
        int nrm = 0;
        int64_t d = denom;
        div = num;
        while (div % 10 == 0 && d % 10 == 0)
        {
            div /= 10;
            d /= 10;
        }
        while (div % 5 == 0 && d % 5 == 0)
        {
            div /= 5;
            d /= 5;
        }
        while (div % 2 == 0 && d % 2 == 0)
        {
            div /= 2;
            d /= 2;
        }
        while (d % 10 == 0)
        {
            d /= 10;
            nrm++;
        }
        while (d % 5 == 0)
        {
            d /= 5;
            nrm++;
        }
        while (d % 2 == 0)
        {
            d /= 2;
            nrm++;
        }

        size_t pos = ret.length();
        ret.resize(pos + nrm);
        int i = 0;
        while (remainder > 0 && i < nrm)
        {
            remainder *= 10;
            int8_t M = remainder / denom;
            remainder -= M * denom;
            ret[pos++] = M + '0';
            i++;
        }

        if (remainder == 0)
            return ret;

        //first pass calculate length of repeated seq

        int lng = 0;
        int64_t Rstart = remainder;

        do
        {
            remainder *= 10;
            remainder -= (remainder / denom) * denom;
            lng++;
        } while (Rstart != remainder);

        ret.resize(pos + lng + 2);
        ret[pos++] = '(';
        while (lng-- > 0)
        {
            remainder *= 10;
            int64_t M = remainder / denom;
            remainder -= M * denom;
            ret[pos++] = M + '0';
        }
        ret[pos] = ')';
        return ret;
    }
};

/*
package Miscellaneous;

import java.util.HashMap;
import java.util.Map;

//https://www.interviewbit.com/problems/fraction/

public class FractionToDecimal {
    
    public String fractionToDecimal(int A, int B) {
        long a = A, b = B;
        if (a % b == 0)
            return String.valueOf(a / b);
        Map<Long, Integer> map = new HashMap<>();
        StringBuilder res = new StringBuilder();
        if ((a > 0 && b < 0) || (a < 0 && b > 0))
            res.append("-");
        a = Math.abs(a);
        b = Math.abs(b);
        res.append(a / b + ".");
        a = (a % b) * 10;
        while (!map.containsKey(a)) {
            map.put(a, res.length());
            res.append(String.valueOf(a / b));
            a = (a % b) * 10;
            if (a == 0)
                return res.toString();
        }
        return res.insert(map.get(a), "(").append(")").toString();
    }
}
*/