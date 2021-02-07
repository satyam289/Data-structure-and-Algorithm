
#include <iostream>
using namespace std;

/**Given the coordinate of ballon, find minimum number of gun used to brust all
 * Contraints: 1) Every fire gun height reduced by 1
 *             2) You can fire inifinte times from single gun
 *             3) You should always move in forward direction along x coordinate.
 ***/

class BallonBurstGun {

static bool cmp(vector<int>& a, vector<int>& b){
       return a[0] < b[0];
}

void getGunCount(vector<vector<int>>& points){

     sort(points.begin(), points.end(), cmp);  
     int n = points.size();
     if(!n)
        return ;
    
    for(int i=0 ; i < points.size(); i++){
     for(int j=0 ; j<points[i].size(); j++){
         cout << points[i][j] << " ";
     }
      cout << endl;
    }
     int currBallonHeight = points[0][1]; 
     int count = 1;  
     for (int i=1; i<n; i++) {
          if(currBallonHeight < points[i][1]) {
            count++;
          }
          currBallonHeight = points[i][1];  //if height of ballon is less than gun point, it will go till its position
     }
     cout << "The minimum number gun required " << count;
   }

  int main() {
       vector<vector<int>> v = {{10, 16}, {2,8}, {1,13}, {7,12}};
       getGunCount(v);
   }
}