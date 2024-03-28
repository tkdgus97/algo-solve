import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int TC = 0; TC < T; TC++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			List<int[]> mart = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				mart.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			st = new StringTokenizer(br.readLine());
			int pestivalX = Integer.parseInt(st.nextToken());
			int pestivalY = Integer.parseInt(st.nextToken());
			sb.append(BFS(startX, startY, pestivalX, pestivalY, mart, N) ? "happy" : "sad").append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean BFS(int startX, int startY, int pestivalX, int pestivalY, List<int[]> mart, int N) {
		Queue<int[]> Q = new ArrayDeque<int[]>();
		boolean[] visit = new boolean[N];
		Q.add(new int[] {startX, startY});
		
		while(!Q.isEmpty()) {
			int[] now = Q.poll();
			
			if (Math.abs(pestivalX - now[0]) + Math.abs(pestivalY - now[1]) <= 1000) {
				return true;
			}
			
			for (int i = 0; i < N; i++) {
				if ((Math.abs(mart.get(i)[0] - now[0]) + Math.abs(mart.get(i)[1] - now[1]) <= 1000) && !visit[i]) {
					visit[i] = true;
					Q.add(mart.get(i));
				}
			}
		}
		
		return false;
	}
}