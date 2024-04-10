import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = -1;
        if (map[H - 1][W - 1] != 1) {
            result = BFS(W, H, K);
        }
        System.out.println(result);

    }

    private static int BFS(int W, int H, int K) {
        int[][] md = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] mh = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {1, -2}, {2, -1}};

        Queue<Monkey> Q = new ArrayDeque<>();

        boolean[][][] visit = new boolean[H][W][K + 1];
        visit[0][0][K] = true;

        Q.add(new Monkey(0, 0, K, 0));
        while (!Q.isEmpty()) {
            Monkey monkey = Q.poll();

            if (monkey.x == H - 1 && monkey.y == W - 1) {
                return monkey.time;
            }

            for (int i = 0; i < 4; i++) {
                int nx = monkey.x + md[i][0];
                int ny = monkey.y + md[i][1];

                if ((nx >= 0 && nx < H) && (ny >= 0 && ny < W) && !visit[nx][ny][monkey.horCnt] && map[nx][ny] == 0) {
                    visit[nx][ny][monkey.horCnt] = true;
                    Q.add(new Monkey(nx, ny, monkey.horCnt, monkey.time + 1));
                }
            }

            if (monkey.horCnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = monkey.x + mh[i][0];
                    int ny = monkey.y + mh[i][1];
                    if ((nx >= 0 && nx < H) && (ny >= 0 && ny < W) && !visit[nx][ny][monkey.horCnt - 1] && map[nx][ny] == 0) {
                        visit[nx][ny][monkey.horCnt - 1] = true;
                        Q.add(new Monkey(nx, ny, monkey.horCnt - 1, monkey.time + 1));
                    }
                }
            }
        }

        return -1;
    }

    private static class Monkey {
        int x, y, horCnt, time;

        public Monkey(int x, int y, int horCnt, int time) {
            this.x = x;
            this.y = y;
            this.horCnt = horCnt;
            this.time = time;
        }
    }
}
