import java.util.*;
import java.io.*;
public class Main {
    private static int n,m;
    private static char[][] map;
    private static int exitX, exitY;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    static class Node {
        int rx;
        int ry;
        int bx;
        int by;
        int time;

        public Node(int rx, int ry, int bx, int by, int time) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new char[n][m];

        int redX = 0;
        int redY = 0;
        int blueX = 0;
        int blueY = 0;
        for(int r = 0; r < n; r++) {
            String s = br.readLine();
            for(int c = 0; c < m; c++) {
                map[r][c] = s.charAt(c);
                if(map[r][c] == 'R') {
                    redX = r;
                    redY = c;
                    map[r][c] = '.';
                } else if(map[r][c] == 'B') {
                    blueX = r;
                    blueY = c;
                    map[r][c] = '.';
                } else if(map[r][c] == 'O') {
                    exitX = r;
                    exitY = c;
                }
            }
        }

        int result = bfs(redX, redY, blueX, blueY);
        System.out.println(result);
    }
    private static int bfs(int srx, int sry, int sbx, int sby) {
        Queue<Node> q = new LinkedList<>();
        boolean[][][][] visit = new boolean[n][m][n][m];
        visit[srx][sry][sbx][sby] = true;

        q.add(new Node(srx, sry, sbx, sby, 0));

        while(!q.isEmpty()) {
            Node n = q.poll();

            if(n.time > 10) return -1;

            // System.out.println(n.time + " : " + n.rx + " " + n.ry + " " + n.bx + " " + n.by);
            
            for(int i = 0; i < 4; i++) {
                int rx = n.rx;
                int ry = n.ry;
                int bx = n.bx;
                int by = n.by;

                while(true) {
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];
                    if(rangeCheck(nx, ny) && map[nx][ny] != '#') {
                        rx = nx;
                        ry = ny;
                        if(rx == exitX && ry == exitY) break;
                        continue;
                    }
                    break;
                }

                while(true) {
                    int nx = bx + dx[i];
                    int ny = by + dy[i];
                    if(rangeCheck(nx, ny) && map[nx][ny] != '#') {
                        bx = nx;
                        by = ny;
                        if(bx == exitX && by == exitY) break;
                        continue;
                    }
                    break;
                }
                if(bx == exitX && by == exitY) continue;
                if(rx == exitX && ry == exitY) return n.time + 1;

                if(rx == bx && ry == by) {
                    if(i == 0) {
                        if(n.rx > n.bx) rx += 1;
                        else bx += 1;
                    } else if(i == 1) {
                        if(n.ry > n.by) by -= 1;
                        else ry -= 1;
                    } else if(i == 2) {
                        if(n.rx < n.bx) rx -= 1;
                        else bx -= 1;
                    } else if(i == 3) {
                        if(n.ry > n.by) ry += 1;
                        else by += 1;
                    } 
                }

                if(!visit[rx][ry][bx][by]) {
                    visit[rx][ry][bx][by] = true;
                    q.add(new Node(rx,ry,bx,by, n.time + 1));
                }
            }
        }

        return -1;
    }

    private static boolean rangeCheck(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}