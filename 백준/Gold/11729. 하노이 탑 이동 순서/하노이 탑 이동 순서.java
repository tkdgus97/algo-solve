import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int move = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
//		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		
		func(N, 1, 2, 3);
		if (N <= 20) {
			System.out.println(move);
			System.out.println(sb);
		} else {
			System.out.println(move);
		}
	}
	
	private static void func(int num, int start, int mid, int to) {
		if (num == 1) {
			move++;
			sb.append(start).append(" ").append(to).append("\n");
			return;
		}
		
		func(num - 1, start, to, mid);
		
		sb.append(start).append(" ").append(to).append("\n");
		move++;
		
		func(num - 1, mid, start, to);
	}
}
