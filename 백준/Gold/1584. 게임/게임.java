import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int[][] map = new int[501][501];
    private static boolean[][] visit = new boolean[501][501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = stoi(st.nextToken());
            int y1 = stoi(st.nextToken());
            int x2 = stoi(st.nextToken());
            int y2 = stoi(st.nextToken());

            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2) ; j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
                    map[j][k] = 1;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = stoi(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = stoi(st.nextToken());
            int y1 = stoi(st.nextToken());
            int x2 = stoi(st.nextToken());
            int y2 = stoi(st.nextToken());

            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2) ; j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
                    map[j][k] = -1;
                }
            }
        }
        map[0][0] = 0;
        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        Queue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            return o1[2] - o2[2];
        });
        q.add(new int[] {0,0,0});
        visit[0][0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[0] == 500 && now[1] == 500) {
                return now[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (check(nx, ny) && !visit[nx][ny] && map[nx][ny] != -1) {
                    q.add(new int[] {nx, ny, now[2] + map[nx][ny]});
                    visit[nx][ny] = true;
                }
            }
        }
        return -1;
    }

    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx < 501 && ny >= 0 && ny < 501;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}