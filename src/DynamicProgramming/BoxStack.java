package DynamicProgramming;

import java.util.Arrays;

public class BoxStack {

    private static class Dimension implements Comparable<Dimension> {
        int len;
        int wid;
        int height;

        Dimension(int height) {
            this.height = height;
        }

        public Dimension(int len, int wid, int height) {
            super();
            this.len = len;
            this.wid = wid;
            this.height = height;
        }

        static Dimension createDimension(int height, int side1, int side2) {
            Dimension dim = new Dimension(height);
            if (side1 > side2) {
                dim.len = side1;
                dim.wid = side2;
            } else {
                dim.len = side2;
                dim.wid = side1;
            }
            return dim;
        }

        public int compareTo(Dimension dim) {
            if (this.len * this.wid >= len * wid)
                return 1;
            else
                return -1;
        }
    }

    public static void main(String[] args) {
        boxstacking(new Dimension[]{new Dimension(3, 2, 5), new Dimension(1, 2, 4)});
    }

    public static void boxstacking(Dimension[] dim) {
        Dimension[] alldim = new Dimension[3 * dim.length];
        generateDimension(alldim, dim);
        Arrays.sort(alldim);

        int[] t = new int[alldim.length];
        int[] r = new int[alldim.length];
        for (int i = 0; i < t.length; i++) {
            t[i] = alldim[i].height;
            r[i] = i;
        }

        for (int i = 1; i < alldim.length; i++) {
            for (int j = 0; j < i; j++) {
                if (alldim[i].len < alldim[j].len && alldim[i].wid < alldim[j].wid) {
                    if (t[j] + alldim[i].height > t[i]) {
                        t[i] = t[j] + alldim[i].height;
                        r[i] = j;
                    }
                }
            }
        }
        int max = 0;
        for (int value : t) {
            if (value > max) {
                max = value;
            }
        }
        System.out.println(max);
    }

    public static void generateDimension(Dimension[] alldim, Dimension[] dim) {
        int index = 0;
        for (Dimension dimension : dim) {

            alldim[index++] = Dimension.createDimension(dimension.len, dimension.wid, dimension.height);
            alldim[index++] = Dimension.createDimension(dimension.wid, dimension.height, dimension.len);
            alldim[index++] = Dimension.createDimension(dimension.height, dimension.len, dimension.wid);
        }
    }
}
