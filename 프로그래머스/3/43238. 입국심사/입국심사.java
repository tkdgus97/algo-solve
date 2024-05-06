import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);

        long lt = 1;
        long rt = (long) times[times.length - 1] * n;

        while (lt < rt) {
            long mid = (lt + rt) / 2;
            long personCnt = 0;
            for (int time : times) {
                personCnt += (mid / time);
            }

            if (personCnt >= n) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }

        return rt;
    }
}