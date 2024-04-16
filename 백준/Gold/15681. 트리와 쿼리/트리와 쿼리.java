import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static List<Integer>[] trees;
    private static int[] childCnt;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();


        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        childCnt = new int[n + 1];
        trees = new ArrayList[n + 1];
        visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
            childCnt[i] = 1;
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            trees[a].add(b);
            trees[b].add(a);
        }

        func(r);

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            sb.append(childCnt[Integer.parseInt(st.nextToken())]).append("\n");
        }
        System.out.println(sb);
    }

    private static void func(int n) {
        visit[n] = true;
        for (Integer i : trees[n]) {
            if (!visit[i]) {
                func(i);
                childCnt[n] += childCnt[i];
            }
        }
    }


}
