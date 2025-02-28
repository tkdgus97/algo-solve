import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;
    private static char[][] map;
    private static int ex, ey;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        map = new char[n][m];
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                }
                if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
                if (map[i][j] == 'O') {
                    ex = i;
                    ey = j;
                }
            }
        }

        int result = bfs(rx, ry, bx, by);
        System.out.println(result);
    }

    private static int bfs(int rx, int ry, int bx, int by) {
        boolean[][][][] visit = new boolean[n][m][n][m];
        visit[rx][ry][bx][by] = true;
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(rx, ry, bx, by, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.time == 10) return -1;

            for (int i = 0; i < 4; i++) {
                int nrx = now.rx;
                int nry = now.ry;
                int nbx = now.bx;
                int nby = now.by;

                while (true) {
                    nrx += dx[i];
                    nry += dy[i];
                    if (nrx == ex && nry == ey) break;
                    if (map[nrx][nry] == '#') {
                        nrx -= dx[i];
                        nry -= dy[i];
                        break;
                    }
                }

                while (true) {
                    nbx += dx[i];
                    nby += dy[i];
                    if (nbx == ex && nby == ey) break;
                    if (map[nbx][nby] == '#') {
                        nbx -= dx[i];
                        nby -= dy[i];
                        break;
                    }
                }

                if ((nbx == ex && nby == ey)) continue;
                if ((nrx == ex && nry == ey)) return now.time + 1;

                if (nrx == nbx && nry == nby) {
                    if (i == 0) {
                        if (now.rx > now.bx) nrx += 1;
                        else nbx += 1;
                    } else if (i == 1) {
                        if (now.ry > now.by) nby -= 1;
                        else nry -= 1;
                    } else if (i == 2) {
                        if (now.rx > now.bx) nbx -= 1;
                        else nrx -= 1;
                    } else if (i == 3) {
                        if (now.ry > now.by) nry += 1;
                        else nby += 1;
                    }
                }

                if (!visit[nrx][nry][nbx][nby]) {
                    visit[nrx][nry][nbx][nby] = true;
                    q.add(new Node(nrx, nry, nbx, nby, now.time + 1));
                }
            }
        }
        return -1;
    }

    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}