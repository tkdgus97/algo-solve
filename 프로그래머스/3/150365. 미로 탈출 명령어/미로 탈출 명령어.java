import java.util.*;
class Solution {
    
    private static int[] dx = {1,0,0,-1};
    private static int[] dy = {0,-1,1,0};
    private static char[] mv = {'d', 'l', 'r','u'};
    private static int mn,mm,mk;
    private static int tx, ty;
    private static String result;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        mn = n;
        mm = m;
        mk = k;
        tx = r;
        ty = c;
        
        int len = calcDis(x,y);
        if((k - len) % 2 == 1) return "impossible";
        
        dfs(x,y,0,"");
        String answer = result == null ? "impossible" : result;
        return answer;
    }
    private static int calcDis(int x1, int y1) {
        return (int)Math.abs(x1 - tx) + (int)Math.abs(y1-ty);
    }
    
    private static void dfs(int x, int y, int t, String r) {
        if(result != null) return;
        if((t + calcDis(x,y)) > mk) return;
        if(t == mk && x == tx && y == ty) {
            result = r;
            return;
        }
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(check(nx, ny)) {
                dfs(nx, ny, t + 1, r + mv[i]);
            }
        }
    }
    
    private static boolean check(int nx, int ny) {
        return nx >= 1 && nx <= mn && ny >= 1 && ny <= mm;
    }
}