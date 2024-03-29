import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };

	private static boolean[][] visit;
	private static boolean[][] isOut;
	private static char[][] map;
	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M];
		isOut = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j]) {
					func(i, j);
				}
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isOut[i][j]) result++;
			}
		}

		System.out.println(result);
	}

	private static boolean func(int x, int y) {
		if (visit[x][y]) {
			return isOut[x][y];
		}

		visit[x][y] = true;

		int nx = x;
		int ny = y;

		if (map[x][y] == 'U') {
			nx = x - 1;
		} else if (map[x][y] == 'R') {
			ny = y + 1;
		} else if (map[x][y] == 'D') {
			nx = x + 1;
		} else if (map[x][y] == 'L') {
			ny = y - 1;
		}

		if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && isOut[nx][ny]) {
			return isOut[x][y] = true;
		}
		
		if (nx < 0 || nx >= N || ny < 0 || ny >= M ) {
			isOut[x][y] = true;
			return true;
		}
		return isOut[x][y] = func(nx, ny);
	}


}