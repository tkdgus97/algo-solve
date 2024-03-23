

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        int sX = -1, sY = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    sX = i; sY = j;
                }
            }
        }

        map[sX][sY] = 0;

        System.out.println(BFS(map, sX, sY, N, M, sb));
    }

    private static String BFS(int[][] map, int startX, int startY, int N, int M, StringBuilder sb) {
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        Queue<int[]> Q = new ArrayDeque<>();

        boolean[][] visit = new boolean[N][M];

        visit[startX][startY] = true;
        Q.add(new int[] {startX, startY});
        while (!Q.isEmpty()) {
            int[] now = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == 1 && !visit[nx][ny]){
                    map[nx][ny] = map[now[0]][now[1]] + 1;
                    visit[nx][ny] = true;
                    Q.add(new int[] {nx, ny});
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j] && map[i][j] == 1) {
                    sb.append(-1).append(" ");
                    map[i][j] = -1;
                } else {
                    sb.append(map[i][j]).append(" ");

                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
