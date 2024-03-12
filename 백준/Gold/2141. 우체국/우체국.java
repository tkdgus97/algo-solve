import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static class Home implements Comparable<Home>{
		long p;
		long k;
		public Home(long p, long k) {
			super();
			this.p = p;
			this.k = k;
		}
		@Override
		public int compareTo(Home o) {
			// TODO Auto-generated method stub
			return Long.compare(this.p, o.p);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		
		Home[] homes = new Home[N];
		
		long total = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			homes[i] = new Home(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			
			total += homes[i].k;
		}
		

		Arrays.sort(homes);
		
		total = (total + 1)/2;
		long tmp = 0;
		for (int i = 0; i < N; i++) {
			tmp += homes[i].k;
			if (tmp >= total) {
				System.out.println(homes[i].p);
				break;
			}
		}
		
		
	}
}
