import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    private static class Node {
        int f, w;

        public Node(int f, int w) {
            this.f = f;
            this.w = w;
        }
    }

    private static boolean[] visit;
    private static int start, end, result;
    private static List<Node>[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        trees = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            trees[t].add(new Node(f, w));
            trees[f].add(new Node(t, w));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            result = 0;
            visit = new boolean[n + 1];
            dfs(start, 0);
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int p, int wSum) {
        if (p == end) {
            result = wSum;
            return;
        }

        visit[p] = true;
        for (int i = 0; i < trees[p].size(); i++) {
            if (!visit[trees[p].get(i).f]) {
                dfs(trees[p].get(i).f, wSum + trees[p].get(i).w);
            }
        }
    }

}
