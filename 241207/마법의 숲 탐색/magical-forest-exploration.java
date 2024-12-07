import java.util.*;
import java.io.*;

public class Main {
    private static int n,m,k;
    private static int[][] map;
    private static int[][] elements;
    private static int[] dx = {1,0,0,-1,0};
    private static int[] dy = {0,-1,1,0,0};
    private static int[] exitdx = {-1,0,1,0};
    private static int[] exitdy = {0,1,0,-1};
    private static int[] result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());

        elements = new int[k][2];
        map = new int[n + 3][m];
        result = new int[k];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int ci = stoi(st.nextToken()) - 1;
            int e = stoi(st.nextToken());
            elements[i][0] = ci;
            elements[i][1] = e;
        }

        for(int t = 0; t < k; t++) {
            int[] m = move(t);
            if(m[2] == 0) {
                init();
                continue;
            }

            int maxR = bfs(m[0], m[1]);

            result[t] = maxR - 2;
            // System.out.println(result[t]);

            // for(int i = 0; i < n + 3; i++) {
            //     System.out.println(Arrays.toString(map[i]));
            // }
        }
        int answer = 0;
        for(int i = 0; i < k; i++) {
            answer += result[i];
        }
        System.out.println(answer);
    }
    private static void init() {
        for(int i = 0; i < n + 3; i++) {
            Arrays.fill(map[i], 0);
        }
    }

    private static int[] move(int num) {
        int r = 1;
        int c = elements[num][0];
        while(true) {
            if(down(r,c)) {
                r++;
                continue;
            }
            if(left(r,c)) {
                r++;
                c--;
                elements[num][1] = (elements[num][1] + 3) % 4;
                continue;
            }
            if(right(r,c)) {
                r++;
                c++;
                elements[num][1] = (elements[num][1] + 1) % 4;
                continue;
            }

            for(int i = 0; i < 5; i++) {
                map[r + dx[i]][c + dy[i]] = num + 1;
            }
            map[r + exitdx[elements[num][1]]][c + exitdy[elements[num][1]]] = -(num + 1);
            break;
        }

        if(r < 4) return new int[] {r,c,0};

        return new int[] {r,c,1};
    }

    private static boolean down(int r, int c) {
        int[] cx = {1,2,1};
        int[] cy = {-1,0,1};

        for(int i = 0; i < 3; i++) {
            int nx = r + cx[i];
            int ny = c + cy[i];

            if(rangeCheck(nx, ny) && map[nx][ny] == 0) continue;
            return false;
        }

        return true;
    }

    private static boolean left(int r, int c) {
        int[] cx = {-1,0,1,1,2};
        int[] cy = {-1,-2,-1,-2,-1};

        for(int i = 0; i < 5; i++) {
            int nx = r + cx[i];
            int ny = c + cy[i];

            if(rangeCheck(nx, ny) && map[nx][ny] == 0) continue;
            return false;
        }

        return true;
    }

    private static boolean right(int r, int c) {
        int[] cx = {-1,0,1,1,2};
        int[] cy = {1,2,1,2,1};

        for(int i = 0; i < 5; i++) {
            int nx = r + cx[i];
            int ny = c + cy[i];

            if(rangeCheck(nx, ny) && map[nx][ny] == 0) continue;
            return false;
        }

        return true;
    }

    private static int bfs(int r, int c) {
        boolean[][] visit = new boolean[n + 3][m];
        visit[r][c] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r,c,map[r][c]});

        int maxR = r;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(rangeCheck(nx, ny) && !visit[nx][ny] && map[nx][ny] != 0) {
                    if(map[nx][ny] == now[2] || (map[nx][ny] > 0 && now[2] < 0) || (map[nx][ny] < 0 && now[2] < 0) || map[nx][ny] == -(now[2])) {
                        visit[nx][ny] = true;
                        q.add(new int[] {nx, ny, map[nx][ny]});
                        maxR = Math.max(maxR, nx);
                    }
                }
            }
        }

        return maxR;
    }


    private static boolean rangeCheck(int nx, int ny) {
        return nx >= 0 && nx < n + 3 && ny >= 0 && ny < m;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}