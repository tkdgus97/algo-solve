import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s = br.readLine();
        int min = 1;
        int p = 0;

        while (p < s.length()){
            String tmp = String.valueOf(min);
            for (int i = 0; i < tmp.length(); i++) {
                if ((tmp.charAt(i) - '0') ==  (s.charAt(p) - '0')) {
                    p++;
                }
                if (p >= s.length()) break;
            }
            min++;
        }
        System.out.println(min - 1);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
