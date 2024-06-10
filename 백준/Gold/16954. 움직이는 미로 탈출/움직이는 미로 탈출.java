import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1,0};
    private static int[] dy = {0, 1, 0, -1, -1, 1, 1, -1,0};

    private static List<int[]> walls = new ArrayList<>();
    private static char[][] map = new char[8][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            for (int j = 0; j < 8; j++) {
                map[i][j] = t.charAt(j);
                if (map[i][j] == '#') {
                    walls.add(new int[]{i, j});
                }
            }
        }

        int result = bfs();

        System.out.println(result);
    }

    private static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{7, 0});

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int[] now = q.poll();

                if (map[now[0]][now[1]] == '#') continue;

                if (now[0] == 0 && now[1] == 7) return 1;

                for (int j = 0; j < 9; j++) {
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];

                    if ((nx >= 0 && nx < 8) && (ny >= 0 && ny < 8) && map[nx][ny] != '#') {
                        q.add(new int[]{nx, ny});
                    }

                }
            }
            move();
        }
        return 0;
    }

    private static void move() {
        List<int[]> tmp = new ArrayList<>();

        for (int[] wall : walls) {
            int nx = wall[0] + 1;
            map[wall[0]][wall[1]] = '.';

            if (nx < 8) {
                tmp.add(new int[]{nx, wall[1]});
            }
        }

        for (int[] wall : tmp) {
            map[wall[0]][wall[1]] = '#';
        }

        walls = tmp;
    }
}
