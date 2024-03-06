import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		char c;
		Node left;
		Node right;
		public Node(char c) {
			this.c = c;
		}
		
	}
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		Node[] tree = new Node[N + 1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char l = st.nextToken().charAt(0);
			char r = st.nextToken().charAt(0);
			
			if (tree[root - 'A'] == null) {
				tree[root - 'A'] = new Node(root);
			}
			if (l != '.') {
				tree[l - 'A'] = new Node(l);
				tree[root - 'A'].left = tree[l - 'A']; 
				
			}
			if (r != '.') {
				tree[r - 'A'] = new Node(r);
				tree[root - 'A'].right = tree[r - 'A'];
			}
			
		}
		
		preFunc(tree[0]);
		System.out.println();
		
		midFunc(tree[0]);
		System.out.println();
		
		postFunc(tree[0]);
		System.out.println();
		
	}
	
	private static void preFunc(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.c);
		preFunc(node.left);
		preFunc(node.right);
	}
	
	private static void midFunc(Node node) {
		if (node == null) {
			return;
		}
		midFunc(node.left);
		System.out.print(node.c);
		midFunc(node.right);
	}
	
	private static void postFunc(Node node) {
		if (node == null) {
			return;
		}
		postFunc(node.left);
		postFunc(node.right);
		System.out.print(node.c);
	}
	
}
