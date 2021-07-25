//https://www.interviewbit.com/problems/remove-duplicates-from-sorted-array-ii/
class RemoveDuplicate2{

      public:
       // upto 2 duplicate allowed
       int removeDuplicate(int A[] , int n){
           int count = 0;
           for(int i=0; i<n; i++){
               if(i<n-2 && (A[i] == A[i+1]) && (A[i] == A[i+2])){
                   continue;
               }else{
                   A[count]= A[i];
                   count++;
               }
           }
           for(int i= count; i<n; i++){
               A[i] = -1;
           }
           return count;
       }  
};

/**** Java Implementation ****
package Array;
import java.util.ArrayList;

public class RemoveDuplicate {
     
     //No dunplicate allowed
    public int removeDuplicate(ArrayList<Integer> a){
        int i = 1;
        int j = 1;
        while(i< a.size()){
            if(!a.get(i).equals(a.get(i-1)){
                if(i != j){
                    a.set(j, a.get(i));
                }
                j++;
            }
            i++;
        }
        a.subList(j, a.size()).clear();
        return a.size();
    }
     
     //one Duplicate allowed
     public int removeDuplicates(ArrayList<Integer> a) {
        int n = a.size();
        if (n <=2 ) return n ;
        int len = 2 ;
        for (int i = 2; i < n; i++) {
            if (!a.get(i).equals(a.get(len - 2)) || !a.get(i).equals(a.get(len - 1))){
                a.set(len, a.get(i));
                len++;
            }
        }
        return len;
    }

    //one Duplicate allowed
    public int removeDuplicates(ArrayList<Integer> a) {
        int n = a.size();
        int i = 1;
        int j = 1;
        boolean twice = true;
        while(i < n){
            if(a.get(i).equals(a.get(i-1))){
                if(twice){
                    if(i != j){
                        a.set(j, a.get(i));
                    }
                    j++;
                }
                twice = false;   
            }else{
                if(i != j){
                    a.set(j, a.get(i));
                }
                j++;
                twice = true;
            }
            i++;
        }
        a.subList(j, a.size()).clear();
        return a.size();
    }
}
*/