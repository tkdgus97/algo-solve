import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[][] dp = new long[n + 1][m + 1];
        //가로 선
        int[][] horizontal = new int[n][m + 1];
        //세로 선
        int[][] vertical = new int[n + 1][m];

        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            // init
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (b == d) {
                horizontal[Math.min(a, c)][b] = 1;
            } else {
                vertical[a][Math.min(b, d)] = 1;
            }
        }

        for (int i = 1; i < n + 1; i++) {
            if (horizontal[i - 1][0] == 1) {
                break;
            }
            dp[i][0] = 1L;

        }
        for (int i = 1; i < m + 1; i++) {
            if (vertical[0][i - 1] == 1) {
                break;
            }
            dp[0][i] = 1;
        } // for

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                if (horizontal[i - 1][j] == 1) {
                    dp[i][j] -= dp[i - 1][j];
                }
                if (vertical[i][j - 1] == 1) {
                    dp[i][j] -= dp[i][j - 1];
                }
            }
        }
        System.out.println(dp[n][m]);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
