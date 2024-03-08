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

		while (true) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			char[][][] map;
			map = new char[L][R][C];
			int[][] locInfo = new int[2][3];

			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					st = new StringTokenizer(br.readLine());
					String s = st.nextToken();
					for (int k = 0; k < C; k++) {
						map[i][j][k] = s.charAt(k);
						if (map[i][j][k] == 'S') {
							locInfo[0][0] = i;
							locInfo[0][1] = j;
							locInfo[0][2] = k;
						}
						if (map[i][j][k] == 'E') {
							locInfo[1][0] = i;
							locInfo[1][1] = j;
							locInfo[1][2] = k;
						}
					}
				}
				br.readLine();
			}
			int result = BFS(map, locInfo, L,R,C);
			sb.append(result != 0 ? "Escaped in "+ result +" minute(s)." : "Trapped!").append("\n");
		}

		System.out.println(sb);
	}

	private static int BFS(char[][][] map, int[][] locInfo, int L, int R, int C) {
		int[] dx = { -1, 0, 1, 0, 0, 0 };
		int[] dy = { 0, 1, 0, -1, 0, 0 };
		int[] dz = { 0, 0, 0, 0, -1, 1 };

		Queue<int[]> Q = new ArrayDeque<int[]>();

		Q.add(new int[] { locInfo[0][0], locInfo[0][1], locInfo[0][2], 0 });

		while (!Q.isEmpty()) {
			int[] now = Q.poll();

			for (int i = 0; i < 6; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				int nz = now[2] + dz[i];

				if (nx == locInfo[1][0] && ny == locInfo[1][1] && nz == locInfo[1][2]) {
					return now[3] + 1;
				}

				if ((nx >= 0 && nx < L) && (ny >= 0 && ny < R) && (nz >= 0 && nz < C) && map[nx][ny][nz] == '.') {
					map[nx][ny][nz] = '#';
					Q.add(new int[] {nx, ny, nz , now[3] + 1});
				}
			}
		}
		return 0;
	}
}
