import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    private static int result = 0;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static boolean brFlag;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[][] map = new char[12][6];


        for (int i = 0; i < 12; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
        }

        boolean[][] visit;
        while (true) {
            visit = new boolean[12][6];
            brFlag = true;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visit[i][j]) {
                        visit[i][j] = true;
                        BFS(i, j, visit, map);
                    }
                }
            }

            for (int i = 0; i < 6; i++) {
                int dotCnt = 0;
                for (int j = 11; j >= 0; j--) {
                    if (map[j][i] == '.') {
                        dotCnt++;
                    } else if (map[j][i] != '.' && dotCnt != 0) {
                        map[j + dotCnt][i] = map[j][i];
                        map[j][i] = '.';
                    }
                }
            }

            if (brFlag) {
                break;
            }
            result++;
        }

        System.out.println(result);
    }

    private static void BFS(int startX, int startY, boolean[][] visit, char[][] map) {
        Queue<int[]> Q = new ArrayDeque<>();
        char color = map[startX][startY];
        Q.add(new int[]{startX, startY});

        List<int[]> chainList = new ArrayList<>();
        chainList.add(new int[]{startX, startY});
        while (!Q.isEmpty()) {
            int[] now = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if ((nx >= 0 && nx < 12) && (ny >= 0 && ny < 6) && !visit[nx][ny] && map[nx][ny] == color) {
                    visit[nx][ny] = true;
                    chainList.add(new int[]{nx, ny});
                    Q.add(new int[]{nx, ny});
                }
            }
        }

        if (chainList.size() >= 4) {
            brFlag = false;
            for (int[] p : chainList) {
                map[p[0]][p[1]] = '.';
            }
        }
    }

}
