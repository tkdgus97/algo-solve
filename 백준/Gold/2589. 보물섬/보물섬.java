import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static char[][] map;
    private static int n, m;
    private static int move[][] = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            map[i] = st.nextToken().toCharArray();
        }

        int[][] dis = new int[n][m];
        int result = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] == 'L') {
                    result = Math.max(result, bfs(r, c, dis));
                }
            }
        }
        System.out.println(result);
    }

    private static int bfs(int r, int c, int[][] dis) {

        boolean[][] visit = new boolean[n][m];
        visit[r][c] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, 0});
        int result = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + move[i][0];
                int ny = now[1] + move[i][1];

                if (rangeCheck(nx, ny) && !visit[nx][ny] && map[nx][ny] == 'L') {
                    visit[nx][ny] = true;
                    q.add(new int[] {nx,ny, now[2] + 1});
//                    dis[nx][ny] = now[2] + 1;
                    result = Math.max(result, now[2] + 1);
                }
            }
        }
        return result;
    }

    private static boolean rangeCheck(int r, int c) {
        return (r >= 0 && c >= 0) && (r < n && c < m);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
