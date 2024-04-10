import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int S = Integer.parseInt(st.nextToken());

        System.out.println(bfs(S));
    }

    private static int bfs(int s) {
        Queue<int[]> Q = new ArrayDeque<>();

        boolean[][] visit = new boolean[1001][1001];

        Q.offer(new int[] {0,1,0});
        visit[0][1] = true;
        while(!Q.isEmpty()) {
            int[] now = Q.poll();

            if (now[1] == s) {
                return now[2];
            }

            Q.add(new int[] {now[1], now[1], now[2] + 1});

            if (now[0] + now[1] <= s && !visit[now[0]][now[0] + now[1]] && now[0] != 0) {
                visit[now[0]][now[0] + now[1]] = true;
                Q.add(new int[] {now[0], now[0] + now[1], now[2] + 1});
            }

            if (now[1] > 0 && !visit[now[0]][now[1] - 1]) {
                visit[now[0]][now[1] - 1] = true;
                Q.add(new int[] {now[0], now[1] - 1, now[2] + 1});
            }
        }

        return 0;
    }
}
