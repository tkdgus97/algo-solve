import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = stoi(st.nextToken());
        long result = 0;
        if (n == 1) {
            result = k * 8L;
        } else if (n == 5) {
            result = (k * 8L) + 4;
        } else {
            if (k % 2 == 1) result += 5 - n;
            else result += n - 1;
            result += k * 4L;
        }

        System.out.println(result);


    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
