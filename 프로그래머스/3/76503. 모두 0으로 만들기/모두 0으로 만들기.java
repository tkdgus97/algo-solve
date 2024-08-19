import java.lang.reflect.Array;
import java.util.*;
class Solution {
    private static long[] sum;
    private static long result = 0;
    private static int n;
    private static List<Integer>[] edge;
    private static boolean[] visit;
    public long solution(int[] a, int[][] edges) {
        n = a.length;
        edge = new ArrayList[n];
        sum = new long[n];
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            edge[i] = new ArrayList<>();
            tmp += a[i];
            sum[i] = a[i];
        }

        if (tmp != 0) return -1;
        for (int[] e : edges) {
            edge[e[0]].add(e[1]);
            edge[e[1]].add(e[0]);
        }

        visit = new boolean[n];
        
        rec(0);

        return result;
    }

    private static long rec(int now) {
        visit[now] = true;

        for (int v : edge[now]) {
            if (!visit[v]) {
                long k = rec(v);
                sum[now] += k;
                result += Math.abs(k);
            }
        }

        return sum[now];
    }

}
