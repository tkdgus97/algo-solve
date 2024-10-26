import java.util.*;
class Solution {
    private static int[] ldir = {3,0,1,2};
    private static int[] rdir = {1,2,3,0};
    private static int[][] move = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    private static int n, m;
    private static boolean[][][] visit;
    public int[] solution(String[] grid) {
        n = grid.length;
        m = grid[0].length();
        char[][] map = new char[n][m];
        List<Integer> result =  new ArrayList<>();
        visit = new boolean[n][m][4];
        for(int i = 0; i < n; i++) {
            map[i] = grid[i].toCharArray();
        }
        
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                for(int d = 0; d < 4; d++) {
                    if(!visit[r][c][d]) result.add(cycle(r,c,d,map));
                }
            }
        }
        
        Collections.sort(result);
        
        int[] answer = new int[result.size()];
        int idx = 0;
        for(int c : result) {
            answer[idx++] = c;
        }
        
        return answer;
    }
    
    private static int cycle(int r, int c, int dir, char[][] map) {
        int time = 0;
        while(!visit[r][c][dir]) {
            visit[r][c][dir] = true;
            if(map[r][c] == 'L') {
                dir = (dir + 3) % 4;
            } else if(map[r][c] == 'R') {
                dir = (dir + 1) % 4;
            }
            r = (r + move[dir][0] + n) % n;
            c = (c + move[dir][1] + m) % m;
            
            time++;
        }
        return time;
    }
}