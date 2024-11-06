import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        List<Long> decreNum = new ArrayList<>();

        for (long i = 0; i < 10; i++) {
            rec(i, decreNum);
        }

        decreNum.sort(Long::compare);
        long result = -1;
        if (n <= decreNum.size()){
            result = decreNum.get(n - 1);
        }
        System.out.println(result);
    }

    private static void rec(Long num, List<Long> decreNum) {
        decreNum.add(num);

        for (int i = 0; i < num % 10; i++) {
            rec(num * 10 + i, decreNum);
        }
    }
    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
