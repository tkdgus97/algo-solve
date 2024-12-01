import java.util.*;
import java.io.*;
public class Main {
    private static int n,m,k;
    private static Tower[][] map;
    private static int[] dx = {0,1,0,-1, -1,-1,1,1};
    private static int[] dy = {1,0,-1,0, -1,1,1,-1};

    private static List<Tower> list;
    private static boolean[][] isDamage;

    static class Tower implements Comparable<Tower> {
        int r;
        int c;
        int atk;
        int time;

        public Tower(int r, int c, int atk, int time) {
            this.r = r;
            this.c = c;
            this.atk = atk;
            this.time = time;
        }

        @Override
        public int compareTo(Tower t) {
            if(this.atk != t.atk) return this.atk - t.atk;
            if(this.time != t.time) return t.time - this.time;
            if(this.r + this.c != t.r + t.c) return (t.r + t.c) - (this.r + this.c);
            return t.c - this.c;
        }
    }

    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());

        map = new Tower[n][m];

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < m; c++) {
                int v = stoi(st.nextToken());
                map[r][c] = new Tower(r,c,v,0);
            }
        }

        simul();

        int max = Integer.MIN_VALUE;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                max = Math.max(map[r][c].atk, max);
            }
        }

        System.out.println(max);
    }

    private static void simul() {
        for(int i = 0; i < k; i++){
            init();

            if(list.size() <= 1) break;

            Tower attack = findAttack();
            Tower target = findTarget();

            if(!laser(attack, target)) {
                boom(attack, target);
            }

            destroyTower();
            repair();
        }
    }

    private static Tower findAttack() {
        Tower t = list.get(0);
        t.time++;
        t.atk += (n + m);
        isDamage[t.r][t.c] = true;
        return list.get(0);
    }

    private static Tower findTarget() {
        Tower t = list.get(list.size() - 1);
        isDamage[t.r][t.c] = true;
        return t;
    }



    private static boolean laser(Tower attack, Tower target) {
        boolean[][] visit = new boolean[n][m];
        Point[][] route = new Point[n][m];
        Queue<Point> q = new LinkedList<>();
        visit[attack.r][attack.c] = true;
        q.add(new Point(attack.r, attack.c));
        boolean flag = false;
        while(!q.isEmpty()) {
            Point cur = q.poll();

            if(cur.r == target.r && cur.c == target.c) {
                flag = true;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = (cur.r + dx[i] + n) % n;
                int ny = (cur.c + dy[i] + m) % m;

                if(!visit[nx][ny] && map[nx][ny].atk != 0) {
                    route[nx][ny] = new Point(cur.r, cur.c);
                    visit[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }

        if(flag) {
            map[target.r][target.c].atk -= attack.atk;
            Point cur = route[target.r][target.c];
            while(cur.r != attack.r || cur.c != attack.c) {
                isDamage[cur.r][cur.c] = true;
                map[cur.r][cur.c].atk -= (attack.atk / 2);
                cur = route[cur.r][cur.c];
            }
        }

        return flag;
    }

    private static void boom(Tower attack, Tower target) {
        map[target.r][target.c].atk -= attack.atk;
        for(int i = 0; i < 8; i++) {
            int nx = (target.r + dx[i] + n) % n;
            int ny = (target.c + dy[i] + m) % m;

            if(map[nx][ny].atk != 0 && nx != attack.r && ny != attack.c) {
                isDamage[nx][ny] = true;
                map[nx][ny].atk -= (attack.atk / 2);
            }
        }
    }

    private static void destroyTower() {
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c].atk < 0) {
                    map[r][c].atk = 0;
                }
            }
        }
    }

    private static void repair() {
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c].atk > 0 && !isDamage[r][c]) {
                    map[r][c].atk++;
                }
            }
        }
    }

    private static void init() {
        list = new ArrayList<>();
        isDamage = new boolean[n][m];

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c].atk > 0) {
                    list.add(map[r][c]);
                }
            }
        }

        Collections.sort(list);
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}