import java.util.*;
class Solution {
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    
    private static int n,m;
    private static int sx, sy, ex, ey;
    
    private static class Node {
        int x;
        int y;
        int t;
        
        int r;
        
        Node(int x, int y, int t, int r) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.r = r;
        }
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length();
        
        init(maps);
        
        
        return bfs(maps);
    }
    
    private static int bfs(String[] maps) {
        boolean[][][] visit = new boolean[n][m][2];
    
        Queue<Node> q = new LinkedList<>();
        visit[sx][sy][0] = true;
        q.add(new Node(sx,sy,0,0));
        
        while(!q.isEmpty()) {
            Node now = q.poll();
            
            if(now.x == ex && now.y == ey && now.r == 1) return now.t;
            
            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(check(nx, ny) && !visit[nx][ny][now.r] && maps[nx].charAt(ny) != 'X') {
                    if(maps[nx].charAt(ny) == 'L' && now.r == 0) {
                        q.add(new Node(nx, ny, now.t + 1, 1));
                        visit[nx][ny][1] = true;
                    }
                    q.add(new Node(nx, ny, now.t + 1, now.r));
                    visit[nx][ny][now.r] = true;
                }
            }
        }
        
        return -1;
    }
    
    private static void init(String[] maps) {
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(maps[r].charAt(c) == 'S') {
                    sx = r;
                    sy = c;
                }
                if(maps[r].charAt(c) == 'E') {
                    ex = r;
                    ey = c;
                }
            }
        }
    }
    
    private static boolean check(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && nx < n && ny < m);
    }
}