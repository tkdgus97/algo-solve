import java.util.*;
class Solution {
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static boolean[][] visit;
    private static List<Integer> lands = new ArrayList<>();
    private static int n,m;
    private static String[] map;
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        
        visit = new boolean[n][m];
        map = maps;
        
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++){
                if(maps[r].charAt(c) != 'X' && !visit[r][c]) {
                    lands.add(dfs(r,c,0));
                }
            }
        }
        
        if(lands.size() == 0) return new int[] {-1};
        
        int[] answer = new int[lands.size()];
        int idx = 0;
        for(int i : lands) {
            answer[idx++] = i;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    private static int dfs(int r, int c, int sum) {
        visit[r][c] = true;
        sum += (map[r].charAt(c) - '0');
        boolean isGo = false;
        for(int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            
            if(check(nx, ny) && !visit[nx][ny] && map[nx].charAt(ny) != 'X'){
                sum = dfs(nx, ny, sum);
            }
        }
        return sum;
    }
    
    private static boolean check(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && nx < n && ny < m);
    }
}