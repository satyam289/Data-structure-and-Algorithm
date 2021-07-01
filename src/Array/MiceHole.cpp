# include <algorithm>
#include <vector>

public:
    int mice(vector<int> &mice, vector<int> &Hole) {
        int N = mice.size();
        sort(mice.begin(), mice.end());
        sort(Hole.begin(), Hole.end());

        int ans = 0;
        for(int i = 0; i < N; ++i) {
            ans = max(ans, abs(mice[i] - Hole[i]));
        }
        return ans;
    }

/* 
package Array;

import java.util.Arrays;

//https://www.interviewbit.com/old/problems/assign-mice-to-holes/ public
class MiceHole {
    public int mice(int[] micePos, int[] holePos) {
        Arrays.sort(micePos);
        Arrays.sort(holePos);

        int mices = micePos.length;
        int holes = holePos.length;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i <= (holes - mices); i++) {
            int val = 0;
            for (int j = 0; j < mices; j++) {
                val = Math.max(val, Math.abs(micePos[j] - holePos[i + j]));
            }
            if (val < min) {
                min = val;
            }
        }
        return min;
    }
}
*/