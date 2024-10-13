import java.util.*;
class Solution {
    private static boolean[] visit;
    private static List<Integer>[] adj;
    private static int result;
    public int solution(int n, int[][] lighthouse) {
        adj = new ArrayList[n + 1];
        visit = new boolean[n +1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] l : lighthouse) {
            adj[l[0]].add(l[1]);
            adj[l[1]].add(l[0]);            
        }
        
        dfs(1);
        
        return result;
    }
    
    private static int dfs(int cur) {
        visit[cur] = true;
        
        int child = 0;
        for(int next : adj[cur]) {
            if(visit[next]) continue;
            child += dfs(next);
        }
        
        if(child == 0) return 1;
        
        result++;
        return 0;
    }
}