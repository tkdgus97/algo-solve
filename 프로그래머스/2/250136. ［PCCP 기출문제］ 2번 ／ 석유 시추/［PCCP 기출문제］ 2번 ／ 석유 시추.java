import java.util.*;
class Solution {
    private static boolean[][] visit;
    private static int n, m;
    private static int[] size;
    
    private static int[] dx= {-1,0,1,0};
    private static int[] dy= {0,1,0,-1};
    
    public int solution(int[][] land) {

        init(land);
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visit[i][j] && land[i][j] == 1) {
                    bfs(i,j, land);
                }
            }   
        }
        
        int answer = 0;
        
        for(int i = 0; i < m; i++) {
            answer = Math.max(answer, size[i]);
        }
        return answer;
    }
    
    private static void bfs(int x, int y, int[][] land) {
        visit[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[] {x,y});
        int cnt = 1;
        Set<Integer> area = new HashSet<>();
        area.add(y);
        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                
                if((nx >= 0 && nx < n && ny >= 0 && ny < m) && !visit[nx][ny] && land[nx][ny] == 1) {
                    cnt++;
                    q.add(new int[] {nx, ny});
                    visit[nx][ny] = true;
                    area.add(ny);
                }
            }   
        }
        
        for(int t : area) {
            size[t] += cnt;
        }
        
    }
    
    private static void init(int[][] land){
        n = land.length;
        m = land[0].length;
        size = new int[m + 1];
        visit = new boolean[n][m];
    }
}