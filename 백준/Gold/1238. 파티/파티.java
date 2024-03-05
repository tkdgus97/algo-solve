import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		List<Edge> edges;

		public Node() {
			this.edges = new ArrayList<>();
		}

	}

	private static class Edge implements Comparable<Edge>{
		int to;
		int t;

		public Edge(int to, int t) {
			this.to = to;
			this.t = t;
		}

		@Override
		public int compareTo(Edge o) {
			return this.t - o.t;
		}


	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[N + 1];
		Node[] reverse = new Node[N + 1];

		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node();
			reverse[i] = new Node();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			nodes[from].edges.add(new Edge(to, t));
			reverse[to].edges.add(new Edge(from, t));
		}

		int[] distance = new int[N + 1];
		int[] reverseDistance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(reverseDistance, Integer.MAX_VALUE);
		distance[X] = reverseDistance[X] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.add(new Edge(X, 0));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for (Edge e : nodes[now.to].edges) {
				if (distance[e.to] > e.t + now.t) {
					distance[e.to] = now.t + e.t;
					pq.add(new Edge(e.to, now.t + e.t));
				}
			}

		}
		
		pq.add(new Edge(X, 0));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for (Edge e : reverse[now.to].edges) {
				if (reverseDistance[e.to] > e.t + now.t) {
					reverseDistance[e.to] = now.t + e.t;
					pq.add(new Edge(e.to, now.t + e.t));
				}
			}

		}
		
		int result = Integer.MIN_VALUE;
		
		for (int i = 1; i <= N; i++) {
			result = Math.max(result,  distance[i] + reverseDistance[i]);
		}
		System.out.println(result);
	}
}
