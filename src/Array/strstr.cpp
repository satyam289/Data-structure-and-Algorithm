#include <iostream>
#include <string>

using namespace std;

//https://www.interviewbit.com/problems/implement-strstr/

class strstr{
public:
    //0(nm)
    int strStr(const string haystack, const string needle){

        int needle_size = needle.size();
        int haystack_size = haystack.size();

        if(!needle_size){
            return 0;
        }
        for(int i = 0; i < haystack_size; i++){
            if(haystack[i] == needle[0]){
                int j = 0;
                while(j < needle_size){
                    if(haystack[i+j] != needle[j])
                        break;
                    j++;
                }
                if(j == needle_size)
                    return i;
            }
        }
        return -1;
    }

    //0(m)
    int strstrOptimised(const string A, const string B){
        int start = 0, temp_start = 0, j = 0;
        while(start < A.size() && j < B.size()) {
            if(A[start] == B[j]) {
                start++;
                j++;
            } else {
                j=0;
                temp_start = start;
            }
            if(j == B.size())
               return temp_start;
        }
        return -1;
    }
};

/* simple Java Implementation 
     
     if(haystack.contains(needle)){
         return haystack.indexOf(needle);
     }
     return -1;

 * /