import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static class Node {
		Map<String, Node> node = new HashMap<>();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Node root = new Node();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int depth = Integer.parseInt(st.nextToken());
			Node cur = root;
			for (int j = 0; j < depth; j++) {
				String s = st.nextToken();
				cur.node.putIfAbsent(s, new Node());
				cur = cur.node.get(s);
			}
		}
		
		print(root, new StringBuilder(), 0);
		System.out.println(sb);
	}
	
	private static void print(Node n, StringBuilder sb2, int depth) {
		Object[] keys = n.node.keySet().toArray();
		Arrays.sort(keys);
		
		for (Object object : keys) {
			sb.append(sb2).append((String) object).append("\n");
			print(n.node.get((String) object), sb2.append("--"), depth + 1);
			sb2.delete(depth * 2, depth * 2 + 2);
		}
	}


}
