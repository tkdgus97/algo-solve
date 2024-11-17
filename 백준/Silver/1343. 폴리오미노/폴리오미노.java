import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().replaceAll("XXXX", "AAAA");
        String s1 = s.replaceAll("XX", "BB");
        int x = s1.indexOf("X");
        if(x != -1) {
            System.out.println(-1);
        } else {
            System.out.println(s1);
        }
    }
}