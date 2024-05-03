

class Solution {

    public int solution(int m, int n, int[][] cityMap) {
        int MOD = 20170805;
        int answer = 0;

        int[][][] dp = new int[m + 1][n + 1][2];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n;j++) {
                if (i == 1 && j == 1) {
                    dp[i][j][0] = dp[i][j][1] = 1;
                    continue;
                }
                if (cityMap[i - 1][j - 1] == 1) continue;
                if (cityMap[i - 1][j - 1] == 2) {
                    dp[i][j][0] = dp[i - 1][j][0];
                    dp[i][j][1] = dp[i][j-1][1];
                } else {
                    dp[i][j][0] = ((dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD);
                    dp[i][j][1] = ((dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD);
                }
            }
        }


        return dp[m][n][0] ;
    }
}