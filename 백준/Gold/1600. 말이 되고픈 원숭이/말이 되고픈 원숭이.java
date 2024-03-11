import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

//문제 풀이용
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = BFS(map, N, M, K);

        System.out.println(result);
    }

    private static int BFS(int[][] map, int N, int M, int K) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][] horseX = {{-2, -2}, {-1, 1}, {2, 2}, {1, -1}};
        int[][] horseY = {{-1, 1}, {2, 2}, {1, -1}, {-2, -2}};

        boolean[][][] visit = new boolean[M][N][K + 1];
        Queue<int[]> Q = new ArrayDeque<>();

        visit[0][0][K] = true;
        Q.add(new int[]{0, 0, 0, K});
        int result = -1;
        while (!Q.isEmpty()) {
            int[] now = Q.poll();

            if (now[0] == M - 1 && now[1] == N - 1) {
                return now[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx == 0 && ny == 0) continue;

                if ((nx >= 0 && nx < M) && (ny >= 0 && ny < N) && map[nx][ny] == 0 && !visit[nx][ny][now[3]]) {
//                    dis[nx][ny] = now[2] + 1;
                    visit[nx][ny][now[3]] = true;
                    Q.add(new int[]{nx, ny, now[2] + 1, now[3]});
                }
            }

            if (now[3] > 0) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        int hx = now[0] + horseX[i][j];
                        int hy = now[1] + horseY[i][j];
                        if (hx == 0 && hy == 0) continue;

                        if ((hx >= 0 && hx < M) && (hy >= 0 && hy < N) && map[hx][hy] == 0 && !visit[hx][hy][now[3] - 1]) {
//                            dis[hx][hy] = now[2] + 1;
                            visit[hx][hy][now[3] - 1] = true;
                            Q.add(new int[]{hx, hy, now[2] + 1, now[3] - 1});
                        }
                    }
                }
            }
        }

        return result;
    }

}
