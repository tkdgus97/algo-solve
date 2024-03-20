import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Edge {
		int to;
		int from;
		int w;
		public Edge(int to, int from, int w) {
			super();
			this.to = to;
			this.from = from;
			this.w = w;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		
		int[] parent = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		List<Edge> edges = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(to, from, w));
		}
		
		Collections.sort(edges, (o1, o2) -> {
			return o1.w - o2.w;
		});
		
		int result = 0;
		
		for (Edge edge : edges) {
			if (find(parent, edge.to) != find(parent, edge.from)) {
				union(edge.to, edge.from, parent);
				result += edge.w;
			}
		}
		
		System.out.println(result);
	}
	
	private static int find(int[] parent,int n) {
		if (parent[n] == n) {
			return n;
		}
		return find(parent , parent[n]);
	}
	
	private static void union(int a, int b, int[] parent) {
		int aP = find(parent, a);
		int bP = find(parent, b);
		
		if (a < b) parent[bP] = aP;
		else parent[aP] = bP;
	}
	
}
