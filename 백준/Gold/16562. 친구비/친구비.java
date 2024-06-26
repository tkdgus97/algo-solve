import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] price = new int[n + 1];
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            price[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b, price);
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (parents[i] == i) {
                sum += price[i];
            }
        }


        System.out.println(k - sum < 0 ? "Oh no" : sum);
    }

    private static int find(int n) {
        if (parents[n] == n) {
            return n;
        }
        return parents[n] = find(parents[n]);
    }

    private static void union(int a, int b, int[] price) {
        int x = find(a);
        int y = find(b);

        if (x != y) {
            if (price[x] > price[y]) {
                parents[x] = y;
            } else {
                parents[y] = x;
            }
        }

    }

}
