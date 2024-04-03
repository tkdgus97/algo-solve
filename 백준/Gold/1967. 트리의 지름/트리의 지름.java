import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		int to;
		int v;

		public Node(int to, int v) {
			super();
			this.to = to;
			this.v = v;
		}
	}

	private static int maxNode = 1;
	private static int maxV = 0;
	private static List<Node>[] nodes;

	private static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			nodes[s].add(new Node(e, v));
			nodes[e].add(new Node(s, v));
		}

		visit = new boolean[N + 1];
		visit[1] = true;
		func(1, 0);

		visit = new boolean[N + 1];
		visit[maxNode] = true;
		func(maxNode, 0);

		System.out.println(maxV);
	}

	private static void func(int node, int sum) {
		if (maxV < sum) {
			maxNode = node;
			maxV = sum;
		}

		for (Node n : nodes[node]) {
			if (!visit[n.to]) {
				visit[n.to] = true;
				func(n.to, sum + n.v);
			}
		}
	}

}