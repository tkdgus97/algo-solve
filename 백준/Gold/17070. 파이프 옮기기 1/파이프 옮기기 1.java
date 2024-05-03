import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int result = 0;
	private static int[] dx=  {0,1,1};
	private static int[] dy = {1,1,0};
	private static int[][] checkDx = {{0}, {0,1,1}, {1}};
	private static int[][] checkDy = {{1}, {1,1,0}, {0}};
	private static int[][] goDir = {{0,1}, {0,1,2}, {1,2}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,1,N,0, map);
		System.out.println(result);
	} 
	
	private static void dfs(int x, int y, int N, int dir, int[][] map) {
		if (x >= N || y >= N) {
			return;
		}
		if (x == N - 1 && y == N - 1) {
			result += 1;
			return;
		}
		
		for (int i = 0; i < goDir[dir].length; i++) {
			boolean goFlag = true;
			for (int j = 0; j < checkDx[goDir[dir][i]].length; j++) {
				int nx = x + checkDx[goDir[dir][i]][j];
				int ny = y + checkDy[goDir[dir][i]][j];
				
				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N) && map[nx][ny] == 0) {
					continue;
				}
				goFlag = false;
			}
			if (!goFlag) {
				continue;
			}
			
			dfs(x + dx[goDir[dir][i]], y + dy[goDir[dir][i]], N, goDir[dir][i], map);
		}
	}
	
}