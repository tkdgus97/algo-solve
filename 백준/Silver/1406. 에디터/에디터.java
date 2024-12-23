import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        String s = st.nextToken();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        Stack<Character> s1 = new Stack<>();
        Stack<Character> s2 = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            s1.push(s.charAt(i));
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            if (oper.equals("L")) {
                if (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            } else if (oper.equals("D")) {
                if (!s2.isEmpty()) {
                    s1.push(s2.pop());
                }
            } else if (oper.equals("B")) {
                if (!s1.isEmpty()) {
                    s1.pop();
                }
            } else if (oper.equals("P")) {
                s1.push(st.nextToken().charAt(0));
            }
        }

        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }

        while (!s1.isEmpty()) {
            sb.append(s1.pop());
        }

        sb.reverse();
        System.out.println(sb);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

