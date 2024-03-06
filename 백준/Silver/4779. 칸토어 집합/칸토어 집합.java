import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static char[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		String str;
		while ((str = br.readLine()) != null) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(str);
			
			arr = new char[(int) Math.pow(3, N)];
			
			Arrays.fill(arr, '-');
			
			func(0, (int) Math.pow(3, N));
			
			for (char c : arr) {
				sb.append(c);
			}
			System.out.println(sb);
		}
	}
	
	private static void func(int start, int size) {
		if (size == 1) {
			return;
		}
		
		int mid = size / 3;
		
		for (int i = start + mid; i < start + 2 * mid; i++) {
			arr[i] = ' ';
		}
		
		func(start, mid);
		func(start + 2*mid, mid);
	}
}
