import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int n, m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result == -1 ? result : result + 1);
    }

    private static int bfs() {
        boolean[][][] visit = new boolean[n][m][2];
        Queue<int[]> q = new LinkedList<>();
        visit[0][0][1] = true;

        q.add(new int[]{0, 0, 0, 1});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[0] == (n - 1) && now[1] == (m - 1)) return now[2];
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (check(nx, ny) && !visit[nx][ny][now[3]]) {
                    if (map[nx][ny] == 1) {
                        if (now[3] > 0) {
                            visit[nx][ny][now[3] - 1] = true;
                            q.add(new int[]{nx, ny, now[2] + 1, now[3] - 1});
                        }
                    } else {
                        visit[nx][ny][now[3]] = true;
                        q.add(new int[]{nx, ny, now[2] + 1, now[3]});
                    }
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