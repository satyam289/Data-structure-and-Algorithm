package DynamicProgramming;

import java.util.Arrays;

public class JobSeduling {

    private static class Job implements Comparable<Job> {
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            super();
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job job) {
            if (this.end - job.end == 0) {
                return this.start - job.start;
            }
            return this.end - job.end;
        }
    }

    public static void main(String[] args) {
        Job[] jobs = new Job[]{new Job(1, 3, 5), new Job(4, 5, 6)};
        getMaxValue(jobs);
    }

    private static void getMaxValue(Job[] jobs) {
        int[] valueArray = new int[jobs.length];
        Arrays.sort(jobs);
        for (int i = 0; i < jobs.length; i++) {
            valueArray[i] = jobs[i].profit;
        }

        for (int i = 0; i < jobs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (jobs[i].start >= jobs[j].end) {
                    if (valueArray[i] < valueArray[j] + jobs[i].profit) {
                        valueArray[i] = valueArray[j] + jobs[i].profit;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < jobs.length; i++) {
            if (valueArray[i] > max)
                max = valueArray[i];
        }
        System.out.println(max);
    }
}
