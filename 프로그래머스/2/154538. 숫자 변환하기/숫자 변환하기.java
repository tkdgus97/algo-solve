import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        boolean[] visit = new boolean[1000001];
        visit[x] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,0});
        int result = -1;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            if(now[0] == y) {
                result = now[1];
                break;
            }
            
            if(check(now[0]*3, y) && !visit[now[0]*3]) {
                visit[now[0]*3] = true;
                q.add(new int[] {now[0]*3, now[1] + 1});
            }
            if(check(now[0]*2, y) && !visit[now[0]*2]) {
                visit[now[0]*2] = true;
                q.add(new int[] {now[0]*2, now[1] + 1});
            }
            if(check(now[0] + n, y) && !visit[now[0] + n]) {
                visit[now[0] + n] = true;
                q.add(new int[] {now[0] + n, now[1] + 1});
            }
        }
        return result;
    }
    
    private static boolean check(int num, int y) {
        return num <= y && num <= 1000000;
    }
}