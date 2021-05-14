package tree;
/*
Representation of Segment trees 
1. Leaf Nodes are the elements of the input array. 
2. Each internal node represents some merging of the leaf nodes
http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
*/
public class SegmentTreeRange {

    static int segmentTree[];

    // Min Segment Tree
    public SegmentTreeRange(int[] input) {
        /* int x = (int) (Math.ceil(Math.log(input.length) / Math.log(2)));
         * int max_size = 2 * (int) Math.pow(2, x) - 1; 
         */
        int max_size = 2 * nextPowerOf2(input.length) - 1;
        segmentTree = new int[max_size];
        for (int i = 0; i < max_size; i++) {
            segmentTree[i] = Integer.MAX_VALUE;
        }
        buildSegmentTree(input, 0, input.length - 1, 0);
    }

    private void buildSegmentTree(int[] input, int low, int high, int pos) {
        if (low == high) {
            segmentTree[pos] = input[low];
            return;
        }
        int mid = (low + high) / 2;

        buildSegmentTree(input, low, mid, 2 * pos + 1);
        buildSegmentTree(input, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    /**
     * Updates segment tree for certain index by given delta Time Complexity:
     * 0(logN)
     */
    public void updateSegmentTree(int input[], int segmentTree[], int index, int delta) {
        input[index] += delta;
        updateSegmentTreeUtil(segmentTree, index, delta, 0, input.length - 1, 0);
    }

    private void updateSegmentTreeUtil(int[] segmentTree, int index, int delta, int low, int high, int pos) {
        if (index < low || index > high) {
            return;
        }

        if (low == high) {
            segmentTree[pos] += delta;
            return;
        }
        int mid = (low + high) / 2;
        updateSegmentTreeUtil(segmentTree, index, delta, low, mid, 2 * pos + 1);
        updateSegmentTreeUtil(segmentTree, index, delta, mid + 1, high, 2 * pos + 2);
        segmentTree[pos] = Math.min(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
    }

    /**
     * Queries given range for minimum value. Time Complexity: 0(logN)
     */
    public int rangeMinimumQuery(int[] segmentTree, int qlow, int qhigh, int len) {
        return rangeMinimumQueryUtil(segmentTree, qlow, qhigh, 0, len - 1, 0);
    }

    private int rangeMinimumQueryUtil(int[] segmentTree, int qlow, int qhigh, int low, int high, int pos) {
        //If segment of this node is a part of given range
        if (qlow <= low && high <= qhigh) { // complete overlap
            return segmentTree[pos];
        }
        if (qlow > high || qhigh < low) { // No overlap
            return Integer.MAX_VALUE;
        }
        int mid = (low + high) / 2;
        return Math.min(rangeMinimumQueryUtil(segmentTree, qlow, qhigh, low, mid, 2 * pos + 1),
                rangeMinimumQueryUtil(segmentTree, qlow, qhigh, mid + 1, high, 2 * pos + 2));
    }

    public static void main(String args[]) {
        int input[] = { 0, 3, 4, 2, 1, 6, -1 };
        SegmentTreeRange st = new SegmentTreeRange(input);
        System.out.println(st.rangeMinimumQuery(segmentTree, 1, 5, input.length));
        System.out.println(st.rangeMinimumQuery(segmentTree, 1, 6, input.length));
        st.updateSegmentTree(input, segmentTree, 2, 1);
        System.out.println(st.rangeMinimumQuery(segmentTree, 1, 3, input.length));
    }

    // http://www.geeksforgeeks.org/next-power-of-2/
    private int nextPowerOf2(int num) {
        if (num == 0) {
            return 1;
        }
        if (num > 0 && (num & (num - 1)) == 0) {
            return num;
        }
        while ((num & (num - 1)) > 0) {
            num = num & (num - 1);
        }
        return num << 1;
    }
}
