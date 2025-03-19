import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] p;
    private static List<Integer>[] tree;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        tree = new List[n + 1];
        p = new int[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }

        p[1] = 1;
        dfs(1);
        for (int i = 2; i <= n; i++) {
            sb.append(p[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int v) {
        visit[v] = true;

        for (Integer node : tree[v]) {
            if (!visit[node]) {
                p[node] = v;
                dfs(node);
            }
        }
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}