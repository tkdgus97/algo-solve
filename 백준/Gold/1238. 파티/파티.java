import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Node{
		List<Edge> edges;

		public Node(List<Edge> edges) {
			this.edges = edges;
		}
		
		
	}
	
	private static class Edge implements Comparable<Edge>{
		int from;
		int w;
		public Edge(int from, int w) {
			super();
			this.from = from;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int X = Integer.parseInt(st.nextToken());
		
		//돌아갈때
		Node[] nodes = new Node[N + 1];
		//각 지점에서 올때
		Node[] reverseNode = new Node[N + 1];
		
		for (int i = 1; i <= N; i++) {
			nodes[i] = new Node(new ArrayList<>());
			reverseNode[i] = new Node(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());;
			int w = Integer.parseInt(st.nextToken());
			nodes[to].edges.add(new Edge(from, w));	
			reverseNode[from].edges.add(new Edge(to, w));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		int[] goDis = new int[N + 1];
		int[] backDis = new int[N + 1];
		
		Arrays.fill(goDis, Integer.MAX_VALUE);
		Arrays.fill(backDis, Integer.MAX_VALUE);
		
		goDis[X] = backDis[X] = 0;
		
		pq.add(new Edge(X, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for (int i = 0; i < nodes[now.from].edges.size(); i++) {
				if (backDis[nodes[now.from].edges.get(i).from] > now.w + nodes[now.from].edges.get(i).w) {
					backDis[nodes[now.from].edges.get(i).from] = now.w + nodes[now.from].edges.get(i).w;
					pq.add(new Edge(nodes[now.from].edges.get(i).from, now.w + nodes[now.from].edges.get(i).w));
				}
			} 
		}
		
		pq.add(new Edge(X, 0));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			for (int i = 0; i < reverseNode[now.from].edges.size(); i++) {
				if (goDis[reverseNode[now.from].edges.get(i).from] > now.w + reverseNode[now.from].edges.get(i).w) {
					goDis[reverseNode[now.from].edges.get(i).from] = now.w + reverseNode[now.from].edges.get(i).w;
					pq.add(new Edge(reverseNode[now.from].edges.get(i).from, now.w + reverseNode[now.from].edges.get(i).w));
				}
			} 
		}
		
		int result = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			result = Math.max(goDis[i] + backDis[i], result);
		}
		
		System.out.println(result);
	}
}
