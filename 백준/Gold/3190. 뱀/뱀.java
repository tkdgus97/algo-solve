import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static class Turn {
        int time;
        String direction;

        public Turn(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int N = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        Queue<Turn> turnQ = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turnQ.add(new Turn(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        int x = 0, y = 0, dir = 1;
        int time = 0;
        ArrayDeque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[] {0,0});
        while(true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                time++;
                break;
            }

            if (map[nx][ny] == -1) {
                map[nx][ny] = 0;
                snake.addFirst(new int[] {nx, ny});
            }
            else if (map[nx][ny] == 0) {
                boolean isContinue = true;
                for (int[] body : snake) {
                    if (nx == body[0] && ny == body[1]) {
                        isContinue = false;
                        break;
                    }
                }
                if (!isContinue) {
                    time++;
                    break;
                }
                snake.removeLast();
                snake.addFirst(new int[] {nx, ny});
            }
            x = nx;
            y = ny;
            time++;

            if (!turnQ.isEmpty()) {
                if (time == turnQ.peek().time) {
                    Turn turn = turnQ.poll();
                    if (turn.direction.equals("L")) {
                        if (dir == 0) dir = 3;
                        else if (dir == 1) dir = 0;
                        else if (dir == 2) dir = 1;
                        else if (dir == 3) dir = 2;
                    } else if (turn.direction.equals("D")) {
                        if (dir == 0) dir = 1;
                        else if (dir == 1) dir = 2;
                        else if (dir == 2) dir = 3;
                        else if (dir == 3) dir = 0;
                    }
                }
            }
        }
        System.out.println(time);
    }
}
