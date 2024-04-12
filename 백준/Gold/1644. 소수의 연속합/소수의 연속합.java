
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//문제 풀이용
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        boolean[] prime = new boolean[N + 1];

        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (!prime[i]) {
                list.add(i);
                for (int j = i+i; j <= N; j += i) {
                    prime[j] = true;
                }
            }
        }
        int sum = 0, lt = 0, cnt = 0;

        for (int rt = 0; rt < list.size(); rt++) {
            sum += list.get(rt);
            if (sum == N) {
                cnt++;
            }
            while (sum >= N) {
                sum -= list.get(lt);
                if (sum == N) {
                    cnt++;
                }
                lt++;
            }
        }
        System.out.println(cnt);
    }
}
