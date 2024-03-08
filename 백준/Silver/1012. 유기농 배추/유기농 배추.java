import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
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
			int M = Integer.parseInt(st.nextToken());

			int K = Integer.parseInt(st.nextToken());

			int[][] map = new int[N][M];

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				map[x][y] = 1;
			}

			boolean[][] visit = new boolean[N][M];
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					if (map[j][j2] == 1 && !visit[j][j2]) {
						visit[j][j2] = true;
						BFS(j, j2, visit, N, M, map);
						cnt++;
					}
				}
			}

			sb.append(cnt).append("\n");
		}

		System.out.println(sb);
	}

	private static void BFS(int x, int y, boolean[][] visit, int N, int M, int[][] map) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		visit[x][y] = true;

		Queue<int[]> Q = new ArrayDeque<int[]>();

		Q.add(new int[] { x, y });

		while (!Q.isEmpty()) {
			int[] now = Q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == 1 && !visit[nx][ny]) {
					visit[nx][ny] = true;
					Q.add(new int[] { nx, ny });
				}
			}
		}
	}
}
