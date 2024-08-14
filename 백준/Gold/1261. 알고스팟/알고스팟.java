import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] map;
    private static int n, m;

    private static int[][] dis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dis[i], Integer.MAX_VALUE);
            String s = st.nextToken();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        boolean[][] visit = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 0});
        visit[0][0] = true;
        dis[0][0] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[0] == (m - 1) && now[1] == (n - 1)) {
                dis[now[0]][now[1]] = Math.min(now[2], dis[now[0]][now[1]]);
            }
            ;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if ((nx >= 0 && nx < m) && (ny >= 0 && ny < n)) {
                    int v = now[2];
                    if (map[nx][ny] == 1) v++;
                    if (dis[nx][ny] > now[2]) {
                        dis[nx][ny] = now[2];
                        q.add(new int[]{nx, ny, v});
                    }
                }
            }
        }
        return dis[m - 1][n - 1];
    }


}
