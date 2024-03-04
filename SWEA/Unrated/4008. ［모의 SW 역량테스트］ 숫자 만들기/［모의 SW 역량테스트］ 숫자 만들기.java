import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int min, max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //테스트 케이스 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            int[] number = new int[N];

            st = new StringTokenizer(br.readLine());
            int[] operCnt = new int[4];
            for (int i = 0; i < 4; i++) {
                operCnt[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                number[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0,N - 1, number, operCnt, number[0]);

            sb.append("#").append(test_case).append(" ").append(max - min).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int L, int N, int[] numbers, int[] oper, int value) {
        if (L == N) {
            min = Math.min(min, value);
            max = Math.max(max, value);
            return;
        }

        if (oper[0] > 0) {
            oper[0] -= 1;
            dfs(L  + 1, N, numbers, oper, value + numbers[L + 1]);
            oper[0] += 1;
        }
        if (oper[1] > 0) {
            oper[1] -= 1;
            dfs(L  + 1, N, numbers, oper, value - numbers[L + 1]);
            oper[1] += 1;
        }
        if (oper[2] > 0) {
            oper[2] -= 1;
            dfs(L  + 1, N, numbers, oper, value * numbers[L + 1]);
            oper[2] += 1;
        }
        if (oper[3] > 0) {
            oper[3] -= 1;
            dfs(L  + 1, N, numbers, oper, value / numbers[L + 1]);
            oper[3] += 1;
        }

    }
}
