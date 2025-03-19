import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] count;
    private static boolean[] visit;
    private static  List<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        count = new int[n + 1];
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 1; j <= n; j++) {
                if (s.charAt(j - 1)=='Y') {
                    adj[i].add(j);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];
            visit[i] = true;
            rec(i,i, 0);
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = Math.max(result, count[i]);
        }

        System.out.println(result);
    }

    private static void rec(int o, int v, int l) {
        if (l == 2) {
            return;
        }

        for (int nxt : adj[v]) {
            if (!visit[nxt]) {
                visit[nxt] = true;
                count[o]++;
            }
            rec(o, nxt, l + 1);
        }
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}