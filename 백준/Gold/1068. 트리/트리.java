import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static boolean[] visit;
    private static int cnt, remove;
    private static List<Integer>[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        trees = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            trees[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int start = 0;
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) {
                start = i;
                continue;
            }
            trees[p].add(i);
        }
        st = new StringTokenizer(br.readLine());
        visit = new boolean[N];

        remove = Integer.parseInt(st.nextToken());

        if (remove == start) {
            System.out.println(0);
        } else {
            dfs(start);
            System.out.println(cnt);
        }
    }

    private static void dfs(int p) {
        if (visit[p]) {
            return;
        }
        visit[p] = true;
        int nodes = 0;
        for (int i = 0; i < trees[p].size(); i++) {
            if (trees[p].get(i) != remove) {
                dfs(trees[p].get(i));
                nodes++;
            }
        }

        if (nodes == 0) {
            cnt++;
        }
    }

}