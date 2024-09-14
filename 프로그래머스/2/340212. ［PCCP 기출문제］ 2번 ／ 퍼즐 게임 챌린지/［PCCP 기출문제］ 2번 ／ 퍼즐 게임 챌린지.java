class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = bnSearch(diffs ,times, limit);
        return answer;
    }

    
    private static int bnSearch(int[] diffs, int[] times, long limit) {
        int r = 1;
        int l = findMax(diffs);
        int n = diffs.length;
        while(r < l) {
            int level = (r + l) / 2;
            long totalTime = times[0];
            for(int i = 1; i < n; i++){
                if(totalTime > limit) break;
                
                if(diffs[i] <= level) totalTime += times[i];
                if(diffs[i] > level) {
                   totalTime += (((times[i] + times[i - 1]) * (diffs[i] - level)) + times[i]);
                }
            }
            
            if(totalTime > limit) {
                r = level + 1;
            } else {
                l = level;
            }
        }
        return r;
    }
     private static int findMax(int[] diffs) {
        int l = 0;
        
        for(int now : diffs) {
            l = Math.max(now, l);
        }
         return l;
    }
}