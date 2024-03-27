import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] adjList = new ArrayList[N + 1];

		st = new StringTokenizer(br.readLine());
		st.nextToken();

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 2; i <= N; i++) {
			adjList[Integer.parseInt(st.nextToken())].add(i);
		}

		int[] sum = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			sum[num] += w;

		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < adjList[i].size(); j++) {
				sum[adjList[i].get(j)] += sum[i];
			}
		}

		for (int i = 1; i <= N; i++) {
			sb.append(sum[i]).append(" ");
		}

		System.out.println(sb);
	}

}
