import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());	//N
		
		int[] popul = new int[N + 1];		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			popul[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()); 
			list[i] = new ArrayList<>();
			
			int nodeCnt = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < nodeCnt; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		int[] region = new int[N + 1];
		
		combi(1, region, N, popul, list);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	private static void combi(int L, int[] region, int N, int[] popul, ArrayList<Integer>[] connectInfo) {
		if (L == N + 1) {
			int oCnt = 0;
			int tCnt = 0;
			
			int reCnt = 0;
			
			for (int i = 1; i <= N; i++) {
				if (region[i] == 1) {
					oCnt += popul[i];
				} else {
					tCnt += popul[i];
				}
			}
			
			boolean[] visit = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				if (!visit[i]) {
					BFS(i, visit, region, connectInfo);
					reCnt++;
				}
			}
			
			if (reCnt == 2) {
				result = Math.min(Math.abs(oCnt - tCnt), result);
			}
			
			return;
		}
		
		region[L] = 1;
		combi(L + 1, region, N, popul, connectInfo);
		
		region[L] = 0;
		combi(L + 1, region, N, popul, connectInfo);
	}
	
	private static void BFS(int start, boolean[] visit, int[] region, ArrayList<Integer>[] connectInfo) {
		visit[start] = true;
		Queue<Integer> Q = new ArrayDeque<>();
		
		Q.add(start);
		
		while(!Q.isEmpty()) {
			int now = Q.poll();
			
			for (int i = 0; i < connectInfo[now].size(); i++) {
				if (region[connectInfo[now].get(i)] == region[now] && !visit[connectInfo[now].get(i)]) {
					visit[connectInfo[now].get(i)] = true;
					Q.add(connectInfo[now].get(i));
				}
			}
		}
	}
	
}
	
	
