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

		char[][] map = new char[N][M];

		Queue<int[]> fireQ = new ArrayDeque<>();
		Queue<int[]> jihunQ = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'J') {
					jihunQ.add(new int[] { i, j, 0 });
				}
				if (map[i][j] == 'F') {
					fireQ.add(new int[] { i, j });
				}
			}
		}

		int result = BFS(N, M, map, fireQ, jihunQ);
		System.out.println(result == -1 ? "IMPOSSIBLE" : result);
	}

	private static int BFS(int N, int M, char[][] map, Queue<int[]> fireQ, Queue<int[]> jihunQ) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		while (!jihunQ.isEmpty()) {

			int fireSize = fireQ.size();
			while (fireSize-- > 0) {
				int[] fireNow = fireQ.poll();
				for (int i = 0; i < 4; i++) {
					int nx = fireNow[0] + dx[i];
					int ny = fireNow[1] + dy[i];

					if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == '.') {
						map[nx][ny] = 'F';
						fireQ.add(new int[] { nx, ny });
					}
				}
			}

			int jSize = jihunQ.size();
			while (jSize-- > 0) {
				int[] jNow = jihunQ.poll();
				for (int i = 0; i < 4; i++) {
					int nx = jNow[0] + dx[i];
					int ny = jNow[1] + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
						return jNow[2] + 1;
					}

					if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == '.') {
						map[nx][ny] = 'J';
						jihunQ.add(new int[] { nx, ny, jNow[2] + 1 });
					}
				}
			}

		}

		return -1;
	}
}
