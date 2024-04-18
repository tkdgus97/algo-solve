import java.util.*;
class Solution {
    private static int IMPOSSIBLE = -1;
    private static int INF = 987654321;
    private static int NOT_VALID = -1;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {

        List<Integer>[] map = new List[n+1];
        for(int i = 1 ; i <= n ; i++){
            map[i] = new LinkedList<>();
        }
        for(int[] edge : edge_list){
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }
        int[][] dp = new int[k+1][n+1];
        for(int i = 0 ; i <= k ; i++){
            Arrays.fill(dp[i],NOT_VALID);
        }
        int answer = dfs(0,gps_log[0],gps_log,k,map,dp);
        if(answer == INF){
            answer = IMPOSSIBLE;
        }
        return answer;
    }
    private static int dfs(int depth, int now,int[] log, int k , List<Integer>[] map,int[][] dp){
        if(depth == k-1){
            if(log[depth] == now){
                return 0;
            }else{
                return INF;
            }
        }
        if(dp[depth][now] != NOT_VALID){
            return dp[depth][now];
        }
        int result = INF;
        int same = 1;
        if(log[depth] == now){
            same = 0;
        }
        for(int i = 0 ; i < map[now].size() ; i++){
            int next = map[now].get(i);
            result = Math.min(result,dfs(depth+1,next,log,k,map,dp) + same);
        }
        dp[depth][now] = result;
        return result;
    }
}