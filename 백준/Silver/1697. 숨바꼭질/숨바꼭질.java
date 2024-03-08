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
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] distance = new int[200001];

		Arrays.fill(distance, Integer.MAX_VALUE);
		
		distance[N] = 0;
		
		int result = BFS(N, M, distance);
		
		System.out.println(result);
	}

	private static int BFS(int N, int M, int[] distance) {
		int[] move = {0,1,-1};
		if (N >= M) {
			return N - M;
		}
		Queue<Integer> Q = new ArrayDeque<>();
		Q.add(N);
		
		int result = Integer.MAX_VALUE;
		while(!Q.isEmpty()) {
			int now = Q.poll();
			
			if (now == M) {
				return distance[now];
			}
			for (int i = 0; i < 3; i++) {
				int next = 0;
				if (i == 0) {
					next = now * 2;
				} else {
					next = now + move[i];
				}
				if (next >= 0 && next < 200001 && distance[next] > distance[now] + 1) {
					distance[next] = distance[now] + 1;
					Q.add(next);
				}
			}
		}
		return result;
	}
}
