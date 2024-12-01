import java.util.*;
import java.io.*;
public class Main {
    private static int n,m,k;
    private static int[][] map;
    private static int[][] lastAttack;
    private static int[] dx = {0,1,0,-1, -1,-1,1,1};
    private static int[] dy = {1,0,-1,0, -1,1,1,-1};

    private static List<Tower> list = new ArrayList<>();
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
            this.time = 0;
        }

        public int compareTo(Tower t) {
            if(this.atk != t.atk) return this.atk - t.atk;
            if(this.time != t.time) return t.time - this.time;
            if((this.r + this.c) != (t.r + t.c)) return (t.r + t.c) - (this.r + this.c);
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

        map = new int[n][m];
        lastAttack = new int[n][m];

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < m; c++) {
                map[r][c] = stoi(st.nextToken());
            }
        }

        simul();

        Collections.sort(list);
        System.out.println(list.get(list.size() - 1).atk);
    }

    private static void simul() {
        while(k-- > 0) {
            // System.out.println("=========================");
            init();

            if(list.size() <= 1) break;

            Collections.sort(list);

            Tower atack = findAtackTower();
            Tower target = findTargetTower();
            isDamage[atack.r][atack.c] = true;
            isDamage[target.r][target.c] = true;
            // System.out.println("attack : " + atack.r + " " + atack.c);
            // System.out.println("tartget : " +target.r + " " + target.c);

            if(!laser(atack, target)) {
                boom(atack, target);
            }
            // for(int r = 0; r < n; r++) {
            //     System.out.println(Arrays.toString(map[r]));
            // }

            removeTower();
            repair();
        }
    }

    private static Tower findAtackTower() {
        Tower atack = list.get(0);
        atack.atk = atack.atk + (n + m);
        map[atack.r][atack.c] += (n + m);
        lastAttack[atack.r][atack.c]++; 
        return atack;
    }

    private static Tower findTargetTower() {
        return list.get(list.size() - 1);
    }

    private static boolean laser(Tower atack, Tower target) {
        boolean[][] visit = new boolean[n][m];

        Queue<Point> q = new LinkedList<>();
        Point[][] route = new Point[n][m];

        visit[atack.r][atack.c] = true;
        q.add(new Point(atack.r, atack.c));

        boolean flag = false;
        route[atack.r][atack.c] = new Point(atack.r, atack.c);
        while(!q.isEmpty()) {
            Point now = q.poll();
            if(target.r == now.r && target.c == now.c) {
                flag = true;
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = (now.r + dx[i] + n) % n;
                int ny = (now.c + dy[i] + m) % m;

                if(!visit[nx][ny] && map[nx][ny] != 0) {
                    route[nx][ny] = new Point(now.r, now.c);
                    visit[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }

        if(flag) {
            map[target.r][target.c] -= atack.atk;
            Point cur = route[target.r][target.c];
            while(true) {
                isDamage[cur.r][cur.c] = true;
                map[cur.r][cur.c] -= (atack.atk / 2);
                cur = route[cur.r][cur.c];
                if(cur.r == atack.r && cur.c == atack.c) break;
            }
            return true;
        }
        return false;
    }

    private static void boom(Tower atack, Tower target) {
        map[target.r][target.c] -= atack.atk;
        for(int i = 0; i < 8; i++) {
            int nx = (target.r + dx[i] + n) % n;
            int ny = (target.c + dy[i] + m) % m;

            if(map[nx][ny] > 0) {
                isDamage[nx][ny] = true;
                map[nx][ny] -= (atack.atk / 2);
            }
        }
    }

    private static void removeTower() {
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c] < 0) {
                    map[r][c] = 0;
                }
            }
        }
    }

    private static void repair() {
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c] > 0 && !isDamage[r][c]) {
                    map[r][c] += 1;
                }
            }
        }
    }

    private static void init() {
        isDamage = new boolean[n][m];
        list = new ArrayList<>();
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c] > 0) {
                    list.add(new Tower(r, c, map[r][c], lastAttack[r][c]));
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}