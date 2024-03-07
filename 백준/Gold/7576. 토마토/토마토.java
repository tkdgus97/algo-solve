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

		int[][] arr = new int[M][N];
		boolean[][] visit = new boolean[M][N];
		
		Queue<int[]> Q = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 1) {
					visit[i][j] = true;
					Q.add(new int[] {i,j,0});
				}
			}
		}
		
		int result = 0;
		BFS(Q, visit, N, M, arr);
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0) {
					result = -1;
					break;
				} else {
					result = Math.max(result, arr[i][j]);
				}
			}
			if (result == -1) {
				break;
			}
		}
		
		System.out.println(result == 1 ? 0 : result);
	}

	private static void BFS(Queue<int[]> Q, boolean[][] visit, int N, int M, int[][] map) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		
		while (!Q.isEmpty()) {
			int[] now = Q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if ((nx >= 0 && nx < M) && (ny >= 0 && ny < N) && map[nx][ny] == 0 && !visit[nx][ny]) {
					visit[nx][ny] = true;
					map[nx][ny] = now[2] + 1;
					Q.add(new int[] { nx, ny, now[2] + 1});
				}
			}
		}
	}
}
