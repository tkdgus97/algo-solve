import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        int[] l = new int[n + 1];
        int[] j = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            l[i] = stoi(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            j[i] = stoi(st.nextToken());
        }

        int[][] dp = new int[n + 1][100];

        for (int i = 1; i <= n; i++) {
            for (int k = 0; k < 100; k++) {
                if (k >= l[i]) {
                    dp[i][k] = Math.max(dp[i - 1][k - l[i]] + j[i], dp[i- 1][k]);
                } else {
                    dp[i][k] = dp[i - 1][k];
                }
            }
        }

        System.out.println(dp[n][99]);
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}