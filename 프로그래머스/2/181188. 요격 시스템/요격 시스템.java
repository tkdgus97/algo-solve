import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1,o2) -> {
            return o1[1] - o2[1];
        });
        
        int last = 0;
        for(int[] t: targets) {
            if(last <= t[0]) {
                last = t[1];
                answer++;
            }
        }
        return answer;
    }
}