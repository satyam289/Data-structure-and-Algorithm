#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_map>
#include <boost/functional/hash.hpp>

using namespace std;
  
// https://www.geeksforgeeks.org/count-maximum-points-on-same-line/  
// method to find maximum colinear point 

int maxPointOnSameLine(vector< pair<int, int> > points) 
{ 
    int N = points.size(); 
    if (N < 2) 
        return N; 
  
    int maxPoint = 0; 
    int curMax, overlapPoints, verticalPoints; 
  
    // here since we are using unordered_map which is based on hash function  
    // But by default we don't have hash function for pairs so we'll use hash function defined in Boost library 
    unordered_map<pair<int, int>, int, boost::hash<pair<int, int>>> slopeMap; 
  
    // looping for each point 
    for (int i = 0; i < N; i++) 
    { 
        curMax = overlapPoints = verticalPoints = 0; 
  
        // looping from i + 1 to ignore same pair again 
        for (int j = i + 1; j < N; j++) 
        { 
            // If both point are equal then just  increase overlapPoint count 
            if (points[i] == points[j]) 
                overlapPoints++; 
  
            // If x co-ordinate is same, then both point are vertical to each other 
            else if (points[i].first == points[j].first) 
                verticalPoints++; 
  
            else
            { 
                int yDif = points[j].second - points[i].second; 
                int xDif = points[j].first - points[i].first; 
                int g = __gcd(xDif, yDif); 
  
                // reducing the difference by their gcd 
                yDif /= g; 
                xDif /= g; 
  
                // increasing the frequency of current slope in map 
                slopeMap[make_pair(yDif, xDif)]++; 
                curMax = max(curMax, slopeMap[make_pair(yDif, xDif)]); 
            } 
            curMax = max(curMax, verticalPoints); 
        } 
  
        // updating global maximum by current point's maximum 
        maxPoint = max(maxPoint, curMax + overlapPoints + 1); 
  
        // printf("maximum colinear point which contains current point are : %d\n", curMax + overlapPoints + 1); 
        slopeMap.clear(); 
    } 
  
    return maxPoint; 
} 
 
int main() 
{ 
    const int N = 6; 
    int arr[N][2] = {{-1, 1}, {0, 0}, {1, 1}, {2, 2}, {3, 3}, {3, 4}}; 
    vector< pair<int, int> > points; 
    for (int i = 0; i < N; i++) {
        points.push_back(make_pair(arr[i][0], arr[i][1])); 
    }
    cout << maxPointOnSameLine(points) << endl; 
    return 0; 
};

/***  Java Implementation
    //https://www.interviewbit.com/problems/points-on-the-straight-line/
    public int maxPoints(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == 0) {
            return 0;
        }
        if (a.size() == 1 && b.size() == 1) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.size(); i++) {
            int x1 = a.get(i);
            int y1 = b.get(i);

            HashMap<Double, Integer> map = new HashMap<Double, Integer>();
            int samePoint = 1; // 1 is taken point itself
            int infValue = 0;

            for (int j = i + 1; j < a.size(); j++) {
                int x2 = a.get(j);
                int y2 = b.get(j);

                if ((x1 == x2) && (y1 == y2)) {
                    samePoint += 1;
                } else if (x2 - x1 == 0) {
                    infValue++;
                } else {
                    double slope = 0.0;
                    if (y1 != y2) {
                        slope = (double) (y2 - y1) / (double) (x2 - x1);
                    }
                    if (map.containsKey(slope)) {
                        map.put(slope, map.get(slope) + 1);
                    } else {
                        map.put(slope, 1);
                    }
                }
            }
            if ((infValue + samePoint) > max) {
                max = infValue + samePoint;
            }
            for (Integer value : map.values()) {
                max = Math.max(value + samePoint, max);
            }
        }
        return max;
    }

*/