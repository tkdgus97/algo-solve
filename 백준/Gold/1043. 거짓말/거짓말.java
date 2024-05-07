import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] party = new ArrayList[m];

        parents = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        int knowSize = Integer.parseInt(st.nextToken());
        int[] knowPerson = new int[knowSize];

        for (int i = 0; i < knowSize; i++) {
            knowPerson[i] = Integer.parseInt(st.nextToken());
        }

        //init
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            party[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            party[i].add(p);
            for (int j = 1; j < cnt; j++) {
                int nxtP = Integer.parseInt(st.nextToken());
                union(p, nxtP);
                party[i].add(nxtP);
            }
        }
        int result = 0;

        for (List<Integer> p : party) {
            boolean flag = true;
            int head = p.get(0);
            for (int knowP : knowPerson) {
                if (find(head) == find(knowP)){
                    flag = false;
                    break;
                }
            }
            if (flag) result++;
        }

        System.out.println(result);
    }

    private static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    private static void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) return;

        if (x > y) parents[x] = y;
        else parents[y] = x;
    }
}
