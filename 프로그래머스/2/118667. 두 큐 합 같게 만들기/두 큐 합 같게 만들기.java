import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList();
        Queue<Integer> q2 = new LinkedList();
        
        long sum1 = 0, sum2 = 0;
        
        for(int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];
            
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }
        
        if((sum1 + sum2) % 2 == 1) return -1;
        
        long half = (sum1 + sum2) / 2;
        
        int cnt = 0;
        while(cnt < (queue1.length * 4) + 1) {
            if(sum1 == sum2) break;
            
            if(sum1 > sum2) {
                int pnum = q1.poll();
                q2.add(pnum);
                sum2 += pnum;
                sum1 -= pnum;
            } else {
                int pnum = q2.poll();
                q1.add(pnum);
                sum1 += pnum;
                sum2 -= pnum;
            }
            cnt++;
        }
        
        if(cnt >= (queue1.length * 4) + 1) return -1;
        return cnt;
    }


}