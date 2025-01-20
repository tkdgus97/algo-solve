import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;
    private static char[][] arr;
    private static boolean[][] visit;
    private static boolean[][] isEnd;

    private static int cnt = 0;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        arr = new char[n][m];
        visit = new boolean[n][m];
        isEnd = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    rec(i, j);
                }
            }
        }

        System.out.println(cnt);
    }

    private static void rec(int x, int y) {
        if (!visit[x][y]) {
            visit[x][y] = true;
            if (arr[x][y] == 'U') {
                rec(x + dx[0], y + dy[0]);
            } else if (arr[x][y] == 'R') {
                rec(x + dx[1], y + dy[1]);
            } else if (arr[x][y] == 'D') {
                rec(x + dx[2], y + dy[2]);
            } else if (arr[x][y] == 'L') {
                rec(x + dx[3], y + dy[3]);
            }
        } else {
            if (!isEnd[x][y]) cnt++;
        }
        isEnd[x][y] = true;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}