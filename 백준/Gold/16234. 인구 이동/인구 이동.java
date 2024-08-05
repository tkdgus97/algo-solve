import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] map, group;
    private static int[] groupCnt;
    private static int n, l, r;

    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        group = new int[n][n];
        groupCnt = new int[(n * n) + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int day = 0;

        while (true) {
            boolean[][] visit = new boolean[n][n];
            group = new int[n][n];
            groupCnt = new int[(n * n) + 1];

            int idx = 1;
            int move = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visit[i][j]) {
                        groupCnt[idx] = bfs(i,j,idx,visit);
                        move++;
                        idx++;
                    }
                }
            }
            if (move == n*n) {
                break;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = groupCnt[group[i][j]];
                }
            }
            day++;
        }

        System.out.println(day);
    }

    private static int bfs(int i, int j, int groupNum, boolean[][] visit) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {i,j});
        visit[i][j] = true;
        group[i][j] = groupNum;

        int sum = map[i][j];
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];

                if ((nx >= 0 && nx < n) && (ny >= 0 && ny < n) && !visit[nx][ny]) {
                    int diff = Math.abs(map[now[0]][now[1]] - map[nx][ny]);
                    if (diff >= l && diff <= r) {
                        group[nx][ny] = groupNum;
                        sum += map[nx][ny];
                        cnt++;
                        visit[nx][ny] = true;
                        q.add(new int[] {nx, ny});
                    }
                }
            }
        }

        return (sum / cnt);
    }


}
