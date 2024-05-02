

import java.util.ArrayDeque;
import java.util.LinkedList;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        ArrayDeque<int[]> deliveryQ = new ArrayDeque<>();
        ArrayDeque<int[]> pickupQ = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            deliveryQ.addFirst(new int[]{i, deliveries[i - 1]});
            pickupQ.addFirst(new int[]{i, pickups[i - 1]});
        }

        while (!deliveryQ.isEmpty() && deliveryQ.peek()[1] == 0) deliveryQ.poll();
        while (!pickupQ.isEmpty() && pickupQ.peek()[1] == 0) pickupQ.poll();
        
        while (!deliveryQ.isEmpty() || !pickupQ.isEmpty()) {
            if (deliveryQ.isEmpty())  answer += (pickupQ.peek()[0] * 2);
            else if (pickupQ.isEmpty()) answer += (deliveryQ.peek()[0] * 2);
            else {
                int t = deliveryQ.peek()[0] >= pickupQ.peek()[0] ?deliveryQ.peek()[0] : pickupQ.peek()[0];
                answer += (t * 2);
            }

            int nowCap = cap;
            while (!deliveryQ.isEmpty() && nowCap > 0) {
                int[] d = deliveryQ.poll();
                if (nowCap > d[1]) {
                    nowCap -= d[1];
                } else {
                    d[1] -= nowCap;
                    deliveryQ.addFirst(d);
                    nowCap = 0;
                }
            }
            while (!deliveryQ.isEmpty() && deliveryQ.peek()[1] == 0) deliveryQ.poll();
            nowCap = cap;
            while (!pickupQ.isEmpty() && nowCap > 0) {
                int[] d = pickupQ.poll();
                if (nowCap > d[1]) {
                    nowCap -= d[1];
                } else {
                    d[1] -= nowCap;
                    pickupQ.addFirst(d);
                    nowCap = 0;
                }
            }
            while (!pickupQ.isEmpty() && pickupQ.peek()[1] == 0) pickupQ.poll();
        }
        return answer;
    }

}