import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[N];
			int[] lis = new int[N];
			
			st = new StringTokenizer(br.readLine());
			int lisIdx = 0;
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if (i > 0) {
					if (lis[lisIdx] < arr[i]) {
						lis[++lisIdx] = arr[i];
					} else {
						lis[bnSerach(0, lisIdx, arr[i], lis)] = arr[i];
					}
				} else {
					lis[i] = arr[i];
				}
			}
			
			int result = N;
			for (int i = 0; i < N; i++) {
				if (lis[i] == 0) {
					result = i;
					break;
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int bnSerach(int start, int end, int target, int[] lis) {
		while(start < end) {
			int mid = (start + end) / 2;
			
			if (lis[mid] >= target) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}
 
}
