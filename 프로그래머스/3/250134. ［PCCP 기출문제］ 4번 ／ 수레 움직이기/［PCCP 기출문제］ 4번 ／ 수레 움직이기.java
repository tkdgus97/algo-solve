class Solution {
    private static int n, m;
    private static int rx, ry, bx, by;
    private static int[][] map, blue, red;
    private static int result = Integer.MAX_VALUE;
    
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    
    public int solution(int[][] maze) {
        int answer = 0;
        
        init(maze);
        
        dfs(0, rx, ry, bx, by);
        
        answer = result;
        if(result == Integer.MAX_VALUE) answer = 0;
        
        return answer;
    }
    
    private static void dfs(int cnt, int x1, int y1, int x2, int y2) {
        if(map[x1][y1] == 3 && map[x2][y2] == 4) {
            result = Math.min(result, cnt);
            return;
        }
        //빨강 도착
        if(map[x1][y1] == 3) {
            for(int i = 0; i < 4; i++) {
                int bnx = x2 + dx[i];
                int bny = y2 + dy[i];
                
                if(checkBlue(bnx, bny)) {
                    if(map[bnx][bny] == 3) continue;
                    blue[bnx][bny] = 1;
                    dfs(cnt + 1, x1, y1, bnx, bny);
                    blue[bnx][bny] = 0;
                }
            }
        } else if(map[x2][y2] == 4) {
            for(int i = 0; i < 4; i++) {
                int rnx = x1 + dx[i];
                int rny = y1 + dy[i];
                
                if(checkRed(rnx,rny)) {
                    if(map[rnx][rny] == 4) continue;
                    red[rnx][rny] = 1;
                    dfs(cnt + 1, rnx, rny, x2, y2);
                    red[rnx][rny] = 0;
                }
            }
        } else {
            for(int i = 0; i < 4; i++) {
                int rnx = x1 + dx[i];
                int rny = y1 + dy[i];
                
                if(checkRed(rnx,rny)) {
                    for(int j = 0; j < 4; j++) {
                        int bnx = x2 + dx[j];
                        int bny = y2 + dy[j];
                        
                        if(checkBlue(bnx, bny)) {
                            if(rnx == bnx && rny == bny)continue;
                            if((rnx == x2 && rny == y2) && (bnx == x1 && bny == y1)) continue;
                            blue[bnx][bny] = 1;
                            red[rnx][rny] = 1;
                            dfs(cnt + 1, rnx, rny, bnx, bny);
                            red[rnx][rny] = 0;
                            blue[bnx][bny] = 0;
                        }
                    }
                }
            }
        }
    }
    
    private static boolean checkRed(int rnx, int rny) {
        return ((rnx >= 0 && rnx < n && rny >= 0 && rny < m) && red[rnx][rny] != 1 && map[rnx][rny] != 5);
    }
    
    private static boolean checkBlue(int bnx, int bny) {
        return ((bnx >= 0 && bnx < n && bny >= 0 && bny < m) && blue[bnx][bny] != 1 && map[bnx][bny] != 5);
    }
    
    private static void init(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        
        System.out.println(n + " " + m);
        
        map = new int[n][m];
        blue = new int[n][m];
        red = new int[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) {
                map[i][j] = maze[i][j];
                if(maze[i][j] == 1) {
                    rx = i;
                    ry = j;
                    red[i][j] = 1;
                }
                if(maze[i][j] == 2) {
                    bx = i;
                    by = j;
                    blue[i][j] = 1;
                }
            }
        }
    }
}