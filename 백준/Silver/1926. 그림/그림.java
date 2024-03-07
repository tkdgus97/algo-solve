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

		int[][] arr = new int[N][M];
		boolean[][] visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int max = Integer.MIN_VALUE;
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1 && !visit[i][j]) {
					visit[i][j] = true;
					max = Math.max(max, BFS(i, j, visit, N, M, arr));
					count++;
				}
			}
		}
		
		System.out.println(count);
		System.out.println(count == 0 ? 0 : max);
	}

	private static int BFS(int x, int y, boolean[][] visit, int N, int M, int[][] map) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		int cnt = 1;

		Queue<int[]> Q = new ArrayDeque<int[]>();
		Q.add(new int[] { x, y });
		while (!Q.isEmpty()) {
			int[] now = Q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == 1 && !visit[nx][ny]) {
					visit[nx][ny] = true;
					cnt++;
					Q.add(new int[] { nx, ny });
				}
			}
		}

		return cnt;
	}
}
