import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean[][] top;
    private static int[][] map;
    private static int[][] move = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
            {-1, -1},
            {-1, 1},
            {1, -1},
            {1, 1}
    };
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());

        map = new int[n][m];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = stoi(st.nextToken());
            }
        }

        top = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] != 0 && !top[r][c]) {
                    bfs(r, c, n, m);
                }
            }
        }
        System.out.println(result);
    }

    private static void bfs(int r, int c, int n, int m) {
        boolean[][] visit = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{r, c});
        visit[r][c] = true;
        ArrayList<int[]> topList = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] now = q.poll();

            topList.add(now);
            for (int i = 0; i < 8; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];

                if (isRange(nx, ny, n, m) && !visit[nx][ny]) {
                    if (map[nx][ny] > map[now[0]][now[1]]) return;

                    if (map[nx][ny] == map[now[0]][now[1]]) {
                        q.add(new int[]{nx, ny});
                        visit[nx][ny] = true;
                    }
                }
            }
        }

        for (int[] loc : topList) {
            top[loc[0]][loc[1]] = true;
        }
        result++;
    }

    private static boolean isRange(int nx, int ny, int n, int m) {
        return (nx >= 0 && ny >= 0) && (nx < n && ny < m);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
