import java.util.*;
import java.io.*;
public class Main {
    private static int n,l,q;
    private static int[][] map;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static int[] originHp;
    private static Night[] nights;

    static class Night {
        int r;
        int c;
        int h;
        int w;
        int hp;
        int damage;
        public Night(int r, int c, int h, int w, int hp, int damage) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.hp = hp;
            this.damage = damage;
        }
    }

    public static void main(String[] args) throws Exception{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //체스판 크기
        l = stoi(st.nextToken());
        //기사 수
        n = stoi(st.nextToken());
        //명령 수
        q = stoi(st.nextToken());

        map = new int[l][l];
        originHp = new int[n];

        for(int r = 0; r < l; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < l; c++) {
                map[r][c] = stoi(st.nextToken());
            }
        }

        nights = new Night[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int r = stoi(st.nextToken()) - 1;
            int c = stoi(st.nextToken()) - 1;
            int h = stoi(st.nextToken());
            int w = stoi(st.nextToken());
            int hp = stoi(st.nextToken());
            originHp[i] = hp;

            nights[i] = new Night(r,c,h,w,hp,0);
        }

        for(int t = 0; t < q; t++) {
            st = new StringTokenizer(br.readLine());
            int idx = stoi(st.nextToken());
            int dir = stoi(st.nextToken());
            // System.out.println("=========================");
            // System.out.println(t + " : " + idx + " " + dir);
            simul(idx - 1, dir);
        }

        int result = 0;
        for(int i = 0; i < n; i++) {
            if(nights[i].hp > 0) {
                result += (originHp[i] - nights[i].hp);
            } 
        }
        System.out.println(result);
    }

    private static void simul(int idx, int dir) {
        tryMove(idx, dir);
    }

    private static void tryMove(int idx, int dir) {
        boolean[] visit = new boolean[n];
        visit[idx] = true;
        Queue<Integer> q = new LinkedList<>();
        int[] tmpnx = new int[n];
        int[] tmpny = new int[n];

        for(int i = 0; i < n; i++) {
            nights[i].damage = 0;
        }

        q.add(idx);

        while(!q.isEmpty()) {
            int now = q.poll();
            int nx = nights[now].r + dx[dir];
            int ny = nights[now].c + dy[dir];
            // System.out.println("1. " + now + " : "+ nx + " " + ny);
            if(!rangeCheck(nx, ny)) return;
            if((nx + nights[now].h - 1) > l || (ny + nights[now].w - 1) > l) return;
            
            for(int x = nx; x <= nx + nights[now].h - 1 ; x++) {
                for(int y = ny; y <= ny + nights[now].w - 1; y++) {
                    if(map[x][y] == 1) {
                        nights[now].damage++;
                    }
                    if(map[x][y] == 2) {
                        return;
                    }
                }
            }
            // System.out.println(now + " : "+ nx + " " + ny);
            for(int i = 0; i < n; i++) {
                if(visit[i] || nights[i].hp <= 0) continue;
                if(nx > nights[i].r + nights[i].h - 1 || nights[i].r > nx + nights[now].h - 1) continue;
                if(ny > nights[i].c + nights[i].w - 1 || nights[i].c > ny + nights[now].w - 1) continue;
                q.add(i);
                visit[i] = true;
            }

            tmpnx[now] = nx;
            tmpny[now] = ny;
        }

        nights[idx].damage = 0;

        for(int i = 0; i < n; i++) {
            if(visit[i]) {
                nights[i].r = tmpnx[i];
                nights[i].c = tmpny[i];
                nights[i].hp -= nights[i].damage;
            }
        }
    }

    private static boolean rangeCheck(int nx, int ny) {
        return (nx >= 0 && nx < l) && (ny >= 0 && ny < l);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}