import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < t; tc++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<int[]>[] adj = new List[n + 1];

            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }

            int[] times = new int[n + 1];
            Arrays.fill(times, Integer.MAX_VALUE);
            PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
                return o1[1] - o2[1];
            });

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int after = Integer.parseInt(st.nextToken());
                adj[e].add(new int[] {s, after});
            }
            int result = Integer.MIN_VALUE, cnt = 1;
            q.add(new int[] {c,0});
            times[c] = 0;
            while(!q.isEmpty()) {
                int[] now = q.poll();
                int num = now[0];
                int time = now[1];
                for (int[] next : adj[num]) {
                    if (times[next[0]] > (time + next[1])) {
                        if (times[next[0]] == Integer.MAX_VALUE) {
                            cnt++;
                        }
                        times[next[0]] = time + next[1];
                        q.add(new int[] {next[0], time + next[1]});
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                if (times[i] != Integer.MAX_VALUE) {
                    result = Math.max(result, times[i]);
                }
            }

            sb.append(cnt).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
