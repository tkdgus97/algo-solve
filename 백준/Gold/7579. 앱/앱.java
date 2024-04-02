import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] mem = new int[N];
		int[] costs = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int cTotal = 0;
		for (int i = 0; i < N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
			cTotal += costs[i];
		}
		
		int[][] dp = new int[N][cTotal + 1];
        int result = Integer.MAX_VALUE;
		for (int i = 0; i <= cTotal ; i++) {
			if (i >= costs[0]) {
				dp[0][i] = mem[0];
			}
			if (dp[0][i] >= M) {
				result = Math.min(result, i);
			}
		}
	
		for (int i = 1; i < N; i++) {
			int cost = costs[i];
			int m = mem[i];
			
			for (int j = 0; j <= cTotal; j++) {
				if(j >= cost) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + m);					
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				if (dp[i][j] >= M) {
					result = Math.min(result, j);
				}
			}
		}
		
		System.out.println(result);
	}

}