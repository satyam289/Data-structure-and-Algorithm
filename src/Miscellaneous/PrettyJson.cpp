#include <iostream>
#include <vector>
#include <string>

using namespace std;
class Solution
{
public:
    vector<string> Solution::prettyJSON1(string json)
    {
        vector<string> ans;
        if (json.length() == 0)
            return ans;
        // remove all spaces.
        //json.erase(remove(json.begin(), json.end(), ' ');, json.end());

        string indent = "";
        string current = "";
        int i = 0, len = json.length();
        while (i < len)
        {
            current.push_back(json[i]);
            switch (json[i])
            {
            case ',':
                ans.push_back(current);
                current = indent;
                i++;
                break;
            case ':':
                i++;
                if (json[i] == '{' || json[i] == '[')
                {
                    ans.push_back(current);
                    current = indent;
                }
                break;
            case '{':
            case '[':
                i++;
                ans.push_back(current);
                if (i < len && (json[i] != '}' || json[i] != ']'))
                {
                    indent.push_back('\t');
                }
                current = indent;
                break;
            case '}':
            case ']':
                i++;
                if (i < len && json[i] == ',')
                    break;
                ans.push_back(current);
                if (i < len && (json[i] == '}' || json[i] == ']'))
                {
                    if (!indent.empty())
                        indent.pop_back();
                }
                current = indent;
                break;
            default:
                i++;
                if (i < len && (json[i] == '}' || json[i] == ']'))
                {
                    ans.push_back(current);
                    if (!indent.empty())
                        indent.pop_back();
                    current = indent;
                }
            }
        }
        return ans;
    }

    vector<string> Solution::prettyJSON2(string A)
    {
        vector<string> ans;
        string s = "\t";
        int i = 0, cnt = 0, n = A.length();
        string cur = "";
        while (i < n)
        {
            if (A[i] == '{' || A[i] == '[')
            {
                string str = "";
                if (!cur.empty())
                {
                    for (int i = 0; i < cnt; i++)
                        str += s;
                    str += cur;
                    ans.push_back(str);
                    cur.clear();
                    str.clear();
                }
                // string str="";
                for (int i = 0; i < cnt; i++)
                    str += s;
                ans.push_back(str + A[i]);
                i++;
                cnt++;
            }
            else if (A[i] == ',')
            {
                cur += A[i];
                string str = "";
                for (int i = 0; i < cnt; i++)
                    str += s;
                str += cur;
                ans.push_back(str);
                cur.clear();
                i++;
            }
            else if (A[i] == '}' || A[i] == ']')
            {

                string str = "";
                if (!cur.empty())
                {
                    for (int i = 0; i < cnt; i++)
                        str += s;
                    str += cur;
                    ans.push_back(str);
                    cur.clear();
                    str.clear();
                }
                cnt--;
                for (int i = 0; i < cnt; i++)
                    str += s;
                str += A[i];
                if (i + 1 < n && A[i + 1] == ',')
                {
                    str += A[i + 1];
                    i++;
                }
                ans.push_back(str);
                i++;
            }
            else
            {
                cur += A[i];
                i++;
            }
        }
        return ans;
    }
};

/*
package Miscellaneous;

import java.util.ArrayList;

// https://www.interviewbit.com/problems/pretty-json/
public class PrettyJson {

    public static ArrayList<String> beautifyJson(String str) {

        ArrayList<String> result = new ArrayList<>();
        int tabCounter = 0;
        StringBuffer current = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
            case '{':
            case '[':
                if (current.length() > 1) {
                    result.add(current.toString());
                    current = new StringBuffer();
                }
                for (int j = 0; j < tabCounter; j++) {
                    current.append("\t");
                }
                current.append(ch);
                result.add(current.toString());
                current = new StringBuffer();
                tabCounter++;
                break;
            case '}':
            case ']':
                if (current.length() > 1) {
                    result.add(current.toString());
                    current = new StringBuffer();
                }
                tabCounter--;
                for (int j = 0; j < tabCounter; j++) {
                    current.append("\t");
                }
                current.append(ch);
                result.add(current.toString());
                current = new StringBuffer();
                break;
            case ',':
                if (current.length() > 1) {
                    current.append(ch);
                    result.add(current.toString());
                    current = new StringBuffer();
                }
                break;
            default:
                if (current.length() < 1) {
                    for (int j = 0; j < tabCounter; j++) {
                        current.append("\t");
                    }
                }
                current.append(ch);
            }
        }
        return result;
    }

    public static void main (String [] args){
        String raw = "{A:B,C:{D:E,F:{G:H,I:J}}}";
        for(String s: beautifyJson(raw)){
            System.out.println(s);
        }
    }
}
*/