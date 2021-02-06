
#include <bits/stdc++.h>
using namespace std;

class BallonBurstGun {

   static bool cmp(vector<int>& a, vector<int>& b){
       return a[1] < b[1];
   }

   void getGunCount(vector<vector<int>>& points){

     sort(points.begin(), points.end(), cmp);  
     int n = points.size();
     if(!n)
        return 0;

     int currBallonHeight = points[0][1]; 
     int count = 1;  
     for (int i=1; i<n; i++) {
          if(currBallonHeight > points[i][1]) {
            count++;
            currBallonHeight = points[i][0];
          }
     }
     cout << "The minimum number gun required" << count;
   }

   int main() {
       vector<vector<int>> v = {{10, 16}, {2,8}, {1,6}, {7,12}};
       BallonBurstGun bbg;
       bbg.getGunCount(v);
   }
}
