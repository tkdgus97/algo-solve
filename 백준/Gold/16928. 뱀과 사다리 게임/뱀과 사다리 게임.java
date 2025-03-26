import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());
        int[] info = new int[101];

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            info[s] = e;
        }

        int result = bfs(info);
        System.out.println(result);
    }

    private static int bfs(int[] info) {
        int[] dis = new int[101];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[1] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {1, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            if (now[0] == 100) return now[1];

            for (int i = 1; i <= 6; i++) {
                int nxt = now[0] + i;
                if (nxt <= 100 && dis[nxt] > now[1] + 1) {
                    if (info[nxt] != 0) {
                        dis[nxt] = now[1] + 1;
                        nxt = info[nxt];
                    }
                    dis[nxt] = now[1] + 1;
                    q.add(new int[] {nxt, now[1] + 1});
                }
            }
        }
        return 0;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}