import java.util.*;
class Solution {
    private static int[][] map;
    private static int n,m;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        map = new int[n + 2][m + 2];
        
        for(int i = 0; i < n + 2; i++) {
            Arrays.fill(map[i], -1);
        }
        
        for(int i = 1; i <= n; i++) {
            String s = storage[i - 1];
            for(int j = 1; j <= m; j++) {
                map[i][j] = (s.charAt(j - 1) - 'A');
            }
        }
        
        int total = n * m;
        for(String con : requests) {
            int target = con.charAt(0) - 'A';
            if(con.length() == 1) {
                total -= ji(target);
            } else if(con.length() == 2) {
                total -= crain(target);
            }
        }
        return total;
    }
    
    private static int crain(int v) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(map[i][j] == v) {
                    cnt++;
                    map[i][j] = -1;
                }
            }
        }
        
        return cnt;
    }
    
    private static int ji(int v) {
        int cnt = 0;
        boolean[][] isOut = new boolean[n + 2][m + 2];
        
        isOut[0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0,0});
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                
                if(check(nx, ny) && map[nx][ny] == -1 && !isOut[nx][ny]) {
                    isOut[nx][ny] = true;
                    q.add(new int[] {nx,ny});
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(map[i][j] == v) {
                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(isOut[nx][ny]) {
                            cnt++;
                            map[i][j] = -1;
                            break;
                        }
                    }
                }
            }
        }
        return cnt;
    }
    
    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx < n + 2 && ny >= 0 && ny < m + 2;
    }
    
}