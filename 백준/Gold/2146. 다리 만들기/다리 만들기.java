import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
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
		
		boolean[][] visit = new boolean[N][N];
		
		int landNum = 2;
		ArrayList<Queue<int[]>> startList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					startList.add(BFS(i,j, landNum,N, map));
					landNum++;
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < startList.size(); i++) {
			result = Math.min(searchBFS(startList.get(i), map, N), result);
		}
		
		System.out.println(result);
		
	}
	
	private static Queue<int[]> BFS(int x, int y, int landNum, int N, int[][] map) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		map[x][y] = landNum;
		Queue<int[]> Q = new ArrayDeque<>();
		Queue<int[]> result = new ArrayDeque<>();
		Q.add(new int[] {x,y});
		
		boolean[][] visit = new boolean[N][N];
		
		while(!Q.isEmpty()) {
			int[] now = Q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N)) {
					if (map[nx][ny] == 1) {
						map[nx][ny] = landNum;
						Q.add(new int[] {nx, ny});						
					} else if (map[nx][ny] == 0 && !visit[now[0]][now[1]]) {
						visit[now[0]][now[1]] = true;
						result.add(new int[] {now[0], now[1], 0, landNum});
					}
				}
			}
		}
		return result;
	}
	
	private static int searchBFS(Queue<int[]> Q, int[][] map, int N) {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		int[][] dis = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(dis[i], Integer.MAX_VALUE);
		}
		
		
		while(!Q.isEmpty()) {
			int[] now = Q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N)) {
					if (map[nx][ny] == 0 && dis[nx][ny] > now[2] + 1) {
						dis[nx][ny] = now[2] + 1;
						Q.add(new int[] {nx, ny, now[2] + 1, now[3]});
					} else if(map[nx][ny] != 0 && map[nx][ny] != now[3]) {
						return now[2];
					}
					
				}
			}
		}
		
		return 0;
	}
}
