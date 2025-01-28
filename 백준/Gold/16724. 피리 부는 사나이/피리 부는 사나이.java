import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int n, m;
    private static int[][] map;
    private static boolean[][] visit;
    private static boolean[][] end;

    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];
        end = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < m; j++) {
                map[i][j] = toI(s.charAt(j));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    dfs(i, j);
                }
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int r, int c) {
        visit[r][c] = true;
        int nx = r + dx[map[r][c]];
        int ny = c + dy[map[r][c]];

        if (!visit[nx][ny]) {
            dfs(nx, ny);
        }

        if (!end[nx][ny]) {
            cnt++;
        }
        end[r][c] = true;
    }

    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

    private static int toI(char c) {
        if (c == 'U') {
            return 0;
        } else if (c == 'R') {
            return 1;
        } else if (c == 'D') {
            return 2;
        } else if (c == 'L') {
            return 3;
        }
        return -1;
    }

}