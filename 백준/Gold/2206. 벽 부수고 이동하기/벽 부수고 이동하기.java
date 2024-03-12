
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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(s.charAt(j));

            }
        }

        int result = BFS(map, N, M);

        System.out.println(result);
    }

    private static int BFS(int[][] map, int N, int M) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        boolean[][][] visit = new boolean[N][M][2];
        Queue<int[]> Q = new ArrayDeque<>();

        visit[0][0][0] = true;
        Q.add(new int[]{0, 0, 1, 1});
        int result = -1;
        while (!Q.isEmpty()) {
            int[] now = Q.poll();

            if (now[0] == N - 1 && now[1] == M - 1) {
                return now[2];
            }

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M)) {
                    //벽 부수기 가능
                    if (now[3] == 1) {
                        if (map[nx][ny] == 0 && !visit[nx][ny][0]) {
                            visit[nx][ny][0] = true;
                            Q.add(new int[]{ nx, ny, now[2] + 1, now[3]});
                        } else if (map[nx][ny] == 1 && !visit[nx][ny][1]){
                            visit[nx][ny][1] = true;
                            Q.add(new int[]{ nx, ny, now[2] + 1, 0});
                        }
                    } else {
                        if (map[nx][ny] == 0 && !visit[nx][ny][1]) {
                            visit[nx][ny][1] = true;
                            Q.add(new int[]{ nx, ny, now[2] + 1, now[3]});
                        }
                    }
                }
            }

        }

        return result;
    }

}
