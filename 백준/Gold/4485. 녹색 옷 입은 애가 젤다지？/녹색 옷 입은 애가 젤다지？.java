import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int idx =1;
		
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			if (N == 0) {
				break;
			}
			boolean[][] visit = new boolean[N][N];
			int[][] map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visit[0][0] = true;
			
			PriorityQueue<int[]> Q = new PriorityQueue<int[]>((o1, o2) -> {
				return o1[2] - o2[2];
			});
			
			Q.add(new int[] {0,0, map[0][0]});
			
			int result = 0;
			while(!Q.isEmpty()) {
				int[] now = Q.poll();
				
				if (now[0] == N - 1 && now[1] == N - 1) {
					result = now[2];
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];
					
					if ((nx >= 0 && nx < N) && (ny >= 0 && ny < N) && !visit[nx][ny]) {
						visit[nx][ny] = true;
						Q.add(new int[] {nx, ny, now[2] + map[nx][ny]});
					}
					
				}
				
			}
			
			sb.append("Problem ").append(idx).append(":").append(" ").append(result).append("\n");
			idx++;
		}


		System.out.println(sb);
	}

}
