package Miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

//https://www.geeksforgeeks.org/the-skyline-problem-set-2/
public class SkyLine {

    class BuildingPoint implements Comparable<BuildingPoint> {
        int x;
        boolean isStart;
        int height;

        @Override
        public int compareTo(BuildingPoint o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
            }
        }
    }

    public List<int[]> getSkyLine(int[][] buildings) {

        BuildingPoint[] buildingPts = new BuildingPoint[3 * buildings.length];
        int index = 0;
        for (int[] building : buildings) {
            buildingPts[index] = new BuildingPoint();
            buildingPts[index].x = building[0];
            buildingPts[index].height = building[2];
            buildingPts[index].isStart = true;

            buildingPts[index + 1] = new BuildingPoint();
            buildingPts[index + 1].x = building[1];
            buildingPts[index + 1].height = building[2];
            buildingPts[index + 1].isStart = false;
            index += 2;
        }
        Arrays.sort(buildingPts);
        TreeMap<Integer, Integer> q = new TreeMap<>();
        q.put(0, 1);

        int preMaxHeight = 0;
        List<int[]> result = new ArrayList<>();
        for (BuildingPoint pts : buildingPts) {
            if (pts.isStart) {
                q.compute(pts.height, (key, value) -> {
                    if (value != null) {
                        return value + 1;
                    }
                    return 1;
                });
            } else {
                q.compute(pts.height, (key, value) -> {
                    if (value == 1) {
                        return null;
                    }
                    return value - 1;
                });
            }
            int currMaxHeight = q.lastKey();
            if (currMaxHeight != preMaxHeight) {
                result.add(new int[] { pts.x, currMaxHeight });
                preMaxHeight = currMaxHeight;
            }
        }
        return result;
    }
}
