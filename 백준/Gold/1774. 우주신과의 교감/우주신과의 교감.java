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
	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static class Edge {
		int to;
		int from;
		double w;

		public Edge(int to, int from, double w) {
			this.to = to;
			this.from = from;
			this.w = w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] parent = new int[N + 1];
		Node[] nodes = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			parent[i] = i;
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(x, y);
		}

		List<Edge> edges = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double sum = Math.pow(nodes[j].x - nodes[i].x, 2) + Math.pow(nodes[j].y - nodes[i].y, 2);
				edges.add(new Edge(i, j, Math.sqrt(sum)));
				edges.add(new Edge(j, i, Math.sqrt(sum)));
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (find(parent,a) != find(parent, b)) {
				union(a, b, parent);
			}
		}
		
		double result = 0;
		
		Collections.sort(edges, (o1, o2) ->{
			return Double.compare(o1.w, o2.w);
		});
		
		for (Edge edge : edges) {
			if (find(parent, edge.to) != find(parent, edge.from)) {
				union(edge.to, edge.from, parent);
				result += edge.w;
			}
		}
		
		System.out.printf("%.2f\n", result);
	}

	private static int find(int[] parent, int n) {
		if (parent[n] == n) {
			return n;
		}
		return find(parent, parent[n]);
	}

	private static void union(int a, int b, int[] parent) {
		int aP = find(parent, a);
		int bP = find(parent, b);

		if (a < b)
			parent[bP] = aP;
		else
			parent[aP] = bP;
	}

}
