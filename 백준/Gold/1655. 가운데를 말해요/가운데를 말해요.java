import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minQ = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			
			if (maxQ.size() == minQ.size()) {
				maxQ.add(v);
				
				if (!minQ.isEmpty() && minQ.peek() < maxQ.peek()) {
					minQ.add(maxQ.poll());
					maxQ.add(minQ.poll());
				}
			} else {
				minQ.add(v);
				
				if (minQ.peek() < maxQ.peek()) {
					minQ.add(maxQ.poll());
					maxQ.add(minQ.poll());
				}
			}
			
			sb.append(maxQ.peek()).append("\n");
		}
		System.out.println(sb);
	}

}