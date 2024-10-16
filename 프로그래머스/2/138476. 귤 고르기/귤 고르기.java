import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        Set<Integer> vSet = new HashSet<>();
        int[] vCnt = new int[10000001];
        for(int v : tangerine) {
            vSet.add(v);
            vCnt[v]++;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o2[1] - o1[1];
        });
        for(int v : vSet) {
            pq.add(new int[] {v, vCnt[v]});
        }
        
        while(k > 0) {
            int[] now = pq.poll();
            k -= now[1];
            if(k < 0) {
                answer++;
                break;
            } 
            answer++;
        }
        return answer;
    }
}