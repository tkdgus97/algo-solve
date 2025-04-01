import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int k = stoi(st.nextToken());

        int[] w = new int[n + 1];
        int[] v = new int[n + 1];
        int[] dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = stoi(st.nextToken());
            v[i] = stoi(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = k; j - w[i] >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }

        System.out.println(dp[k]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
