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
	private static List<int[]>[] pList = new ArrayList[3];
	private static int N, M, result = Integer.MIN_VALUE;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < 3; i++) {
			pList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				pList[map[i][j]].add(new int[] { i, j });
			}
		}
		
		combi(0, 0, new int[3][2]);
		
		System.out.println(result);
	}

	private static void combi(int L, int start, int[][] combies) {
		if (L == 3) {
			Queue<int[]> Q = new ArrayDeque<>();
			for (int i = 0; i < combies.length; i++) {
				int[] now = combies[i];
				map[now[0]][now[1]] = 1;
			}

			result = Math.max(result, simul());

			for (int i = 0; i < combies.length; i++) {
				int[] now = combies[i];
				map[now[0]][now[1]] = 0;
			}
			return;
		}

		for (int i = start; i < pList[0].size(); i++) {
			combies[L] = pList[0].get(i);
			combi(L + 1, i + 1, combies);
		}
	}

	private static int simul() {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		boolean[][] visit = new boolean[N][M];
		Queue<int[]> Q = new ArrayDeque<>();
		
		for (int i = 0; i < pList[2].size(); i++) {
			visit[pList[2].get(i)[0]][pList[2].get(i)[1]] = true;
			Q.add(pList[2].get(i));
		}
		
		int safeCnt = pList[0].size() - 3;
		
		while (!Q.isEmpty()) {
			int[] now = Q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M) && map[nx][ny] == 0 &&!visit[nx][ny]) {
					safeCnt -= 1;
					visit[nx][ny] = true;
					Q.add(new int[] {nx, ny});
				}
			}
		}
		
		return safeCnt;
	}

}
