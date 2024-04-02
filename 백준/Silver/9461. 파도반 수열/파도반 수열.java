import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long[] arr = new long[101];
			
			arr[3] = arr[1] = arr[2] = 1;
			
			if (N <= 3) {
				sb.append(1).append("\n");
				continue;
			}
			
			for (int i1 = 4; i1 <= N; i1++) {
				arr[i1] = arr[i1 - 2] + arr[i1 - 3];
			}
			
			sb.append(arr[N]).append("\n");
		}

		System.out.println(sb);
	}

}