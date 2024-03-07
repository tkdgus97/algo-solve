import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
//		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[9];
		
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		func(0, 0, 0, arr, 0);
		System.out.println(sb);
	}
	
	private static void func(int L, int start, int total, int[] arr, int combi) {
		if (!sb.toString().equals("")) {
			return;
		}
		if (total > 100) {
			return;
		}
		if (L == 7)  {
			if (total == 100) {
				if (sb.toString().equals("")) {
					for (int i = 0; i < 9; i++) {
						if ((combi & (1 << i)) > 0) {
							sb.append(arr[i]).append("\n");
						}
					}
				}
			}
			return;
		}
		
		for (int i = start; i < 9; i++) {
			if ((combi & (1 << i)) == 0) {
				combi = (combi ^ (1 << i));
				func(L + 1, i + 1, total + arr[i], arr, combi);
				combi = (combi ^ (1 << i));
			}
		}
	}
}
