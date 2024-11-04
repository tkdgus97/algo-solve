import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n,m;
    private static boolean[] number = new boolean[10];
    private static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        Arrays.fill(number, true);
        if (m > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int v = Integer.parseInt(st.nextToken());
                number[v] = false;
            }
        }

        result = Math.abs(n - 100);
        dfs(0,0);
        System.out.println(result);
    }

    private static void dfs(int d, int v) {
        for (int i = 0; i < 10; i++) {
            if (number[i]) {
                int val = v * 10 + i;
                int diff = Math.abs(n - val) + String.valueOf(val).length();
                result = Math.min(result, diff);

                if (d < 6) {
                    dfs(d + 1, val);
                }
            }
        }
    }
}
