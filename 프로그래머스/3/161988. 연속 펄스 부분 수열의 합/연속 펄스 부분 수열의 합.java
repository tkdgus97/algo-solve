import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        long[] dp1 = new long[sequence.length];
        long[] dp2 = new long[sequence.length];
        long answer = Math.max(sequence[0], -1*sequence[0]);
        for(int i = 0; i < n; i++) {
            dp1[i] = (i % 2 == 0 ? sequence[i] : -1*sequence[i]);
            dp2[i] = (i % 2 == 0 ? -1*sequence[i] : sequence[i]); 
            
            if(i != 0) {
                dp1[i] = Math.max(dp1[i], dp1[i - 1] + dp1[i]);
                dp2[i] = Math.max(dp2[i], dp2[i - 1] + dp2[i]);
            
                answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
            }
        }
//         long answer = Math.max(dp1[0], dp2[0]);
//         for(int i = 1; i < n; i++) {
//             dp1[i] = Math.max(dp1[i], dp1[i - 1] + dp1[i]);
//             dp2[i] = Math.max(dp2[i], dp2[i - 1] + dp2[i]);
            
//             answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
//         }
    
        
        return answer;
    }
}