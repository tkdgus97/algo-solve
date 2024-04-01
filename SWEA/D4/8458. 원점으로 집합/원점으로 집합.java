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
			
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int isOdd = (Math.abs(x - 0) + Math.abs(y - 0)) % 2;
			int gap = Math.abs(x - 0) + Math.abs(y - 0);
			boolean flag = true;
			
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				if ((Math.abs(x - 0) + Math.abs(y - 0)) % 2 != isOdd) {
					flag = false;
				}
				
				if ((Math.abs(x - 0) + Math.abs(y - 0)) > gap) {
					gap = Math.abs(x - 0) + Math.abs(y - 0);
				}
			}
			
			if (!flag) {
				sb.append("#").append(tc).append(" ").append(-1).append("\n");
				continue;
			}
			int answer = 0;
			while(gap > 0) {
				answer++;
				gap -= answer;
			}
			
			answer = gap % 2 == 0 ? answer : (answer % 2 == 0 ? answer + 1 : answer + 2);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	} 
	
}
