import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Str{
        String s;
        int idx;

        public Str(String s, int idx) {
            this.s = s;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        List<Str> order = new ArrayList<>();
        HashMap<String, List<Str>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 1; j <= s.length(); j++) {
                String substr = s.substring(0,j);
                List<Str> li = map.getOrDefault(substr, new ArrayList<>());
                order.add(new Str(substr, i));
                li.add(new Str(s, i));
                map.put(substr, li);
            }
        }

        order.sort((o1, o2) -> {
            if (Integer.compare(o1.idx, o2.idx) != 0) return Integer.compare(o1.idx, o2.idx);
            return Integer.compare(o1.s.length(), o2.s.length());
        });

        int max = 0;
        String s1 = "";
        String s2 = "";

        for (int i = 0; i < order.size(); i++) {
            Str now = order.get(i);
            if (map.get(now.s).size() < 2) continue;
            if (now.s.length() <= max) continue;
            List<Str> list = map.get(now.s);
            list.sort((o1, o2) -> Integer.compare(o1.idx, o2.idx));
            max = now.s.length();
            s1 = list.get(0).s;
            s2 = list.get(1).s;
        }

        System.out.println(s1);
        System.out.println(s2);
    }



    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}