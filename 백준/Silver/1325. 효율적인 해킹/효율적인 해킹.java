import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());

        List<Integer>[] adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());

            adj[e].add(s);
        }
        List<int[]> result = new ArrayList<>();
        int[] cnt = new int[n + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int r = search(i, n, adj);
            cnt[i] = r;
            max = Math.max(max, r);
        }

        for (int i = 1; i <= n; i++) {
            if (max == cnt[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static int search(int v, int n, List<Integer>[] adj) {
        boolean[] visit = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        visit[v] = true;
        q.add(v);

        int result = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Integer i : adj[now]) {
                if (!visit[i]) {
                    q.add(i);
                    visit[i] = true;
                    result++;
                }
            }
        }
        return result;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
