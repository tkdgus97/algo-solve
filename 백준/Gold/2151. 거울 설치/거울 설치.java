
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int x;
        int y;
        int dir;
        int t;

        Node(int x, int y, int d, int t) {
            this.x = x;
            this.y = y;
            this.dir = d;
            this.t = t;
        }
    }

    private static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][n];
        int sx = 0;
        int sy = 0;
        boolean[][][] visit = new boolean[n][n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            map[i] = s.toCharArray();
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '#') {
                    sx = i;
                    sy = j;
                }
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> {
            return o1.t - o2.t;
        });

        for (int i = 0; i < 4; i++) {
            q.add(new Node(sx, sy, i,0));
            visit[sx][sy][i] = true;
        }
        map[sx][sy] = '*';
        int result = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node now = q.poll();

            if (map[now.x][now.y] == '#') {
                result = now.t;
                break;
            }
            if (map[now.x][now.y] == '!') {
                if (now.dir <= 1) {
                    for (int i = 2; i < 4; i++) {
                        int newx = now.x + move[i][0];
                        int newy = now.y + move[i][1];
                        if (!check(newx, newy, n) || map[newx][newy] == '*' || visit[newx][newy][i]) continue;
                        q.add(new Node(newx, newy, i, now.t + 1));
                        visit[newx][newy][i] = true;
                    }
                } else {
                    for (int i = 0; i < 2; i++) {
                        int newx = now.x + move[i][0];
                        int newy = now.y + move[i][1];
                        if (!check(newx, newy, n) || map[newx][newy] == '*' || visit[newx][newy][i]) continue;
                        q.add(new Node(newx, newy, i, now.t + 1));
                        visit[newx][newy][i] = true;
                    }
                }
            }
            int nx = now.x + move[now.dir][0];
            int ny = now.y + move[now.dir][1];

            if (!check(nx, ny, n) || map[nx][ny] == '*' || visit[nx][ny][now.dir]) continue;
            q.add(new Node(nx, ny, now.dir, now.t));
            visit[nx][ny][now.dir] = true;
        }
        System.out.println(result);
    }

    private static boolean check(int nx, int ny, int n) {
        return (nx >= 0 && ny >= 0) && (nx < n && ny < n);
    }

}
