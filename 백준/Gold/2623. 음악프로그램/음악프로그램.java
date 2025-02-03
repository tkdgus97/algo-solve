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

        List<Integer>[] adj = new List[n + 1];
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int c = stoi(st.nextToken());
            int s = stoi(st.nextToken());
            for(int j = 1; j < c; j++) {
                int e = stoi(st.nextToken());
                adj[s].add(e);
                d[e]++;
                s = e;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (d[i] == 0) {
                q.add(i);
            }
        }
        int finish = 0;
        while (!q.isEmpty()) {
            int now = q.poll();
            finish++;
            sb.append(now).append("\n");

            for(Integer next : adj[now]) {
                d[next]--;
                if (d[next] == 0) q.add(next);
            }
        }

        System.out.println(finish == n ? sb : 0);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }


}