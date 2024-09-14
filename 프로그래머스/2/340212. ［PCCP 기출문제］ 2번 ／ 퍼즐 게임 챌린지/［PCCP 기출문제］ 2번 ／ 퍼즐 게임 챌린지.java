class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int r = 1;
        int l = 0;
        int n = diffs.length;
        
        for(int now : diffs) {
            l = Math.max(now, l);
        }
        
        
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
}