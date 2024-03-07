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

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}

		System.out.println(BFS(N, M, map) + 1);
	}

	private static int BFS(int N, int M, int[][] map) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		boolean[][] visit = new boolean[N][M];

		Queue<int[]> Q = new ArrayDeque<>();

		visit[0][0] = true;
		Q.add(new int[] { 0, 0, 0 });

		while (!Q.isEmpty()) {
			int[] now = Q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == 1 && !visit[nx][ny]) {
					if (nx == N - 1 && ny == M -1) {
						return now[2] + 1;
					}
					visit[nx][ny] = true;
					Q.add(new int[] { nx, ny, now[2] + 1 });
				}
			}
		}
		return 0;
	}
}
