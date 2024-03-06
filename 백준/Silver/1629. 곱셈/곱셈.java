import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(func(N,B,C));
    }

    private static long func(int A, int B, int C) {
        if (B == 0) {
            return 1;
        }

        long mod = func(A, B / 2, C);

        if (B % 2 == 1) {
            return (mod * mod % C) * A % C;
        }
        return mod * mod % C;
    }
}
