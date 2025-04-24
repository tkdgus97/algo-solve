import java.util.*;
class Solution {
    private static boolean[] possible;
    private static int[] combi;
    private static boolean[] visit;
    private static int N;
    private static int result = 0;
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        possible = new boolean[n + 1];
        combi = new int[5];
        visit = new boolean[n + 1];
        Arrays.fill(possible, true);
        for(int i = 0; i < ans.length; i++) {
            if(ans[i] == 0) {
                for(int j = 0; j < 5; j++) {
                    possible[q[i][j]] = false;
                }
            }
        }
        rec(0, 1, q,ans);
        return result;
    }
    
    private static void rec(int l,int nxt, int[][] q, int[] ans) {
        if(l == 5) {
            for(int i = 0; i < ans.length; i++) {
                int[] arr = q[i];
                int cnt = ans[i];
                int equl = 0;
                for(int j = 0; j < 5; j++) {
                    if(visit[arr[j]]) {
                        equl++;
                    }
                }
                if(equl != cnt) {
                    return;
                }
            }
            result++;
            return;
        }
        
        for(int i = nxt; i <= N; i++) {
            if(!possible[i]) continue;
            if(!visit[i]) {
                visit[i] = true;
                combi[l] = i;
                rec(l + 1, i+1, q, ans);
                visit[i] = false;
            }
        }
    }
}