import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        while (true) {
            boolean[][] visit = new boolean[N][M];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !visit[i][j]) {
                        isSplit(N, M, map, visit, i, j);
                        cnt++;
                    }
                }
            }
            if (cnt == 0) {
                System.out.println(0);
                break;
            }

            if (cnt > 1) {
                System.out.println(time);
                break;
            }

            Queue<int[]> Q = new ArrayDeque<>();
            boolean[][] visitIce = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        Q.add(new int[]{i, j});
                        visitIce[i][j] = true;
                    }
                }
            }

            BFS(map, N,M,Q);

            time++;
        }


    }

    private static void BFS(int[][] map, int N, int M,  Queue<int[]> Q) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!Q.isEmpty()) {
            int[] now = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] != 0) {
                    map[nx][ny] -= 1;
                }
            }
        }
    }

    private static boolean isSplit(int N, int M, int[][] map, boolean[][] visit, int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<int[]> Q = new ArrayDeque<>();

        Q.add(new int[]{x, y});
        visit[x][y] = true;
        while (!Q.isEmpty()) {
            int[] now = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] != 0 && !visit[nx][ny]) {
                    Q.add(new int[]{nx, ny});
                    visit[nx][ny] = true;
                }
            }
        }

        return false;
    }
}
