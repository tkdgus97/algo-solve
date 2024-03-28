import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];

		int startX = 0;
		int startY = 0;
		List<int[]> waters = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'S') {
					startX = i;
					startY = j;
				}
				if (map[i][j] == '*') {
					waters.add(new int[] { i, j });
				}
			}
		}
		int result = BFS(startX, startY, waters, N,M, map);
		System.out.println(result == -1 ? "KAKTUS" : result);
	}

	private static int BFS(int startX, int startY, List<int[]> waters, int N, int M, char[][] map) {
		Queue<int[]> waterQ = new ArrayDeque<int[]>();
		Queue<int[]> personQ = new ArrayDeque<int[]>();

		int[][] waterMap = new int[N][M];
		boolean[][] visit = new boolean[N][M];
		visit[startX][startY] = true;
		
		
		for (int[] water : waters) {
			waterQ.add(new int[] { water[0], water[1], 0 });
		}

		personQ.add(new int[] { startX, startY, 0 });

		for (int i = 0; i < N; i++) {
			Arrays.fill(waterMap[i], Integer.MAX_VALUE);
		}
		while (!waterQ.isEmpty()) {
			int[] now = waterQ.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == '.' && waterMap[nx][ny] == Integer.MAX_VALUE) {
					waterMap[nx][ny] = now[2] + 1;
					waterQ.add(new int[] { nx, ny, now[2] + 1});
				}
			}
		}
		
		while(!personQ.isEmpty()) {
			int[] now = personQ.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && (map[nx][ny] == '.' || map[nx][ny] == 'D') && waterMap[nx][ny] > now[2] + 1 && !visit[nx][ny]) {
					if (map[nx][ny] == 'D') {
						return now[2] + 1;
					}
					visit[nx][ny] = true;
					personQ.add(new int[] { nx, ny, now[2] + 1});
				}
			}
		}

		return -1;
	}
}