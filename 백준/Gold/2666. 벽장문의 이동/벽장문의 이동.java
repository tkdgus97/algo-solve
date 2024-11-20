import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n, o;
    private static int[] opens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int d1 = stoi(st.nextToken());
        int d2 = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
         o = stoi(st.nextToken());
        opens = new int[o];

        for (int i = 0; i < o; i++) {
            st = new StringTokenizer(br.readLine());
            opens[i] = stoi(st.nextToken());
        }

        int result = dfs(0, d1, d2);
        System.out.println(result);
    }

    private static int dfs(int d, int d1, int d2) {
        if (d == o) return 0;

        int t1 = Math.abs(d1 - opens[d]);
        int t2 = Math.abs(d2 - opens[d]);

        return Math.min(t1 + dfs(d + 1, d2, opens[d]), t2 + dfs(d + 1, d1, opens[d]));
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
