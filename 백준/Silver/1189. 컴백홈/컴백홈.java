import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int result = 0;

    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        DFS(R - 1, 0, 1, map, R, C, K, new boolean[R][C]);
        System.out.println(result);
    }

    private static void DFS(int x, int y, int cnt, char[][] map, int R, int C, int K, boolean[][] visit) {
        if (x == 0 && y == C - 1) {
            if (cnt == K) {
                result++;
            }
            return;
        }

        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if ((nx >= 0 && nx < R) && (ny >= 0 && ny < C) && !visit[nx][ny] && map[nx][ny] == '.') {
                DFS(nx, ny, cnt + 1, map, R,C,K, visit);
            }
        }
        visit[x][y] = false;
    }
}
