import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int n;
    private static StringBuilder stringBuilder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            stringBuilder = new StringBuilder();
            n = Integer.parseInt(st.nextToken());

            func(1, 1, 0, 1, new String("1"));
            System.out.println(stringBuilder);
        }
    }

    private static void func(int L, int num, int sum, int op, String oper) {
        if (L == n) {
            sum += (num * op);
            if (sum == 0) {
                stringBuilder.append(oper).append("\n");
            }
            return;
        }
        func(L + 1, (num * 10) + (L + 1), sum, op, oper + " " + (L + 1));
        func(L + 1, L + 1, sum + (num * op), 1, oper + "+" + (L + 1));
        func(L + 1, L + 1, sum + (num * op), -1, oper + "-" + (L + 1));
    }

}
