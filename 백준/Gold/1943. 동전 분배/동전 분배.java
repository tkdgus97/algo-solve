import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			int[][] coins = new int[N][2];
			boolean[] dp = new boolean[100001];

			int total = 0;
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				coins[j][0] = price;
				coins[j][1] = v;

				total += (price * v);

				for (int k = 1; k <= v; k++) {
					dp[price * k] = true;
				}
			}

			if (total % 2 == 1) {
				sb.append(0).append("\n");
				continue;
			}

			total = total / 2;
			
			dp[0] = true;

			for (int j = 0; j < N; j++) {
				int price = coins[j][0];
				int v = coins[j][1];

				for (int k = total; k >= 0; k--) {
					if (k - price > 0 && dp[k - price]) {
						for (int l = 1; l <= v; l++) {
							if (k + price * l > total) {
								break;
							} else {
								dp[k + price * l] = true;
							}
						}
					}
				}
			}
			
			sb.append(dp[total] ? 1 : 0).append("\n");
		}

		System.out.println(sb);
	}
}
