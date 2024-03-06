import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    private static int[] fibo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        fibo = new int[N + 1];
        func(N);
        System.out.println(fibo[N]);
    }

    private static int func(int N) {
        if (N == 0) return fibo[N] = 0;
        if (N == 1) return fibo[N] = 1;
        if (fibo[N] != 0) return fibo[N];
        return fibo[N] = func(N - 1) + func(N - 2);
    }

}
