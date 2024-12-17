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

        int t = stoi(st.nextToken());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = stoi(st.nextToken());
            int k = stoi(st.nextToken());

            int[] remain = new int[n + 1];
            int[] time = new int[n + 1];
            int[] times = new int[n + 1];

            List<Integer>[] adj = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                time[i] = stoi(st.nextToken());
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int s = stoi(st.nextToken());
                int e = stoi(st.nextToken());
                remain[e]++;
                adj[s].add(e);
            }
            st = new StringTokenizer(br.readLine());
            int w = stoi(st.nextToken());

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                return o1[1] - o2[1];
            });

            for (int i = 1; i <= n; i++) {
                if (remain[i] == 0) {
                    pq.add(new int[]{i, time[i]});
                }
            }

            int result = 0;

            while (!pq.isEmpty()) {
                int[] now = pq.poll();

                if (now[0] == w && remain[now[0]] == 0) {
                    result = now[1];
                }

                for (Integer next : adj[now[0]]) {
                    if (remain[next] > 0) {
                        remain[next]--;
                    }
                    if (remain[next] == 0) {
                        pq.add(new int[] {next, now[1] + time[next]});
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
