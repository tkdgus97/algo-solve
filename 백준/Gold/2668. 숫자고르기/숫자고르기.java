import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static ArrayList<Integer> result = new ArrayList<>();
    private static int[] adj;
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());

        adj = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            adj[i] = stoi(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];
            visit[i] = true;
            dfs(i, i);
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (Integer integer : result) {
            System.out.println(integer);
        }
    }

    private static void dfs(int st, int now) {
        if (!visit[adj[now]]) {
            visit[adj[now]] = true;
            dfs(st, adj[now]);
        }
        if (st == adj[now]) result.add(st);
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
