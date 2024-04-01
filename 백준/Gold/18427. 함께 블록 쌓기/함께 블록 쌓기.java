import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][H + 1];

        dp[0][0] = 1;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            dp[i][0] = 1;
            while(st.hasMoreTokens()){
                int nowCoin = Integer.parseInt(st.nextToken());

                for (int j = nowCoin; j <= H; j++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - nowCoin]) % 10007;
                }

            }
            for (int j = 1; j <= H; j++) {
                dp[i][j] = (dp[i][j] + dp[i - 1][j]) % 10007 ;
            }
        }
        System.out.println(dp[N][H]);
    }

}
