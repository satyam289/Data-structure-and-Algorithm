#include <vector>
#include <algorithm>
using namespace std;

// https://www.interviewbit.com/problems/hotel-bookings-possible/
class hotelBooking {

    public:
        bool hotel(vector<int> &arrive, vector<int> &depart, int K){
            if(K == 0)
                return false;

            int N = arrive.size();
            vector<pair<int, int>> vec;
            for(int i=0; i<N; i++){
                vec.push_back(make_pair(arrive[i], 1));
                vec.push_back(make_pair(depart[i], 0));
            }    
            sort(vec.begin(), vec.end());
            int currActive = 0;
            int maxAns = 0;
            for(int i=0; i<vec.size(); i++){
                if(vec[i].second == 1){
                    currActive++;
                    maxAns  = max(maxAns, currActive);
                } else {
                    currActive--;
                }
            }

            if(K >= maxAns)
                return true;
            return false;    
        }
};

/*  Java Implementaion
package Array;

import java.util.ArrayList;
import java.util.Collections;

public class hotelBooking {
    
    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        Collections.sort(arrive);
        Collections.sort(depart);
        int roomsRequired = 0, i = 0, j = 0;
        while (i < arrive.size() && j < arrive.size() && roomsRequired <= K) {
            if (arrive.get(i) < depart.get(j)) {
                i++;
                roomsRequired++;
            } else {
                j++;
                roomsRequired--;
            }
        }
        if (roomsRequired <= K) {
            return true;
        } else {
            return false;
        }
    }
}
*/