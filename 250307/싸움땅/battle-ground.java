import java.util.*;
import java.io.*;
public class Main {
    static class Player {
        int x;
        int y;
        int d;
        int initp;
        int gun;

        public Player(int x, int y, int d, int initp, int gun) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.initp = initp;
            this.gun = gun;
        }
    }
    private static int n,m,k;
    private static PriorityQueue<Integer>[][] map;
    private static int[][] move;
    private static Player[] players;
    private static int[][] d = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    private static int[] score;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());

        map = new PriorityQueue[n][n];
        move = new int[n][n];
        players = new Player[m + 1];
        score = new int[m + 1];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = new PriorityQueue<Integer>((o1,o2) -> {
                    return o2 - o1;
                });
                int v = stoi(st.nextToken());
                if(v != 0) map[i][j].add(v);
            }
        }

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            players[i] = new Player(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1, stoi(st.nextToken()), stoi(st.nextToken()), 0);
            move[players[i].x][players[i].y] = i;
        }

        simul();

        for(int i = 1; i <= m; i++) {
            System.out.print(score[i] + " ");
        }
    }

    private static void simul() {
        for(int i = 0; i < k; i++) {
            move();   
        }
    }

    private static void move() {
        for(int i = 1; i <= m; i++) {
            // System.out.println("------------------");
            int nx = players[i].x + d[players[i].d][0];
            int ny = players[i].y + d[players[i].d][1];

            if(!check(nx, ny)) {
                players[i].d = (players[i].d + 2) % 4;
                nx = players[i].x + d[players[i].d][0];
                ny = players[i].y + d[players[i].d][1];
            }
            move[players[i].x][players[i].y] = 0;
            players[i].x = nx;
            players[i].y = ny;

            if(move[nx][ny] != 0) {
                int[] result = fight(i);
                loserMove(result[1]);
                winnerAction(result[0]);
                move[nx][ny] = result[0];
            } else {
                getGun(i);
                move[players[i].x][players[i].y] = i;
            }
            // for(int j = 0; j < n; j++) {
            //     System.out.println(Arrays.toString(move[j]));
            // }
        }
    }

    private static int[] fight(int p1) {
        int p2 = move[players[p1].x][players[p1].y];
        int p1Power = players[p1].initp + players[p1].gun;
        int p2Power = players[p2].initp + players[p2].gun;
        int diff = Math.abs(p1Power - p2Power);
        int win = -1;
        int lose = -1;
        if(p1Power > p2Power) {
            score[p1] += diff;
            win = p1;
            lose = p2;
        } else if(p1Power < p2Power) {
            score[p2] += diff;
            win = p2;
            lose = p1;
        } else if(p1Power == p2Power) {
            win = players[p1].initp > players[p2].initp ? p1 : p2;
            lose = players[p1].initp > players[p2].initp ? p2 : p1;
        }

        return new int[] {win, lose};
    }

    private static void loserMove(int idx) {
        int x = players[idx].x;
        int y = players[idx].y;

        move[x][y] = 0;

        if(players[idx].gun != 0) {
            map[x][y].add(players[idx].gun);
            players[idx].gun = 0;
        }

        while(true) {
            int nx = x + d[players[idx].d][0];
            int ny = y + d[players[idx].d][1];

            if(!check(nx, ny) || move[nx][ny] != 0) {
                players[idx].d = (players[idx].d + 1) % 4;
                continue;
            }

            players[idx].x = nx;
            players[idx].y = ny;
            break;
        }

        move[players[idx].x][players[idx].y] = idx;

        getGun(idx);
    }

    private static void winnerAction(int idx) {
        getGun(idx);
    }

    private static void getGun(int idx) {
        int x = players[idx].x;
        int y = players[idx].y;
        if(map[x][y].isEmpty()) return;

        if(players[idx].gun == 0) {
            players[idx].gun = map[x][y].poll();
            return;
        }

        if(map[x][y].peek() > players[idx].gun) {
            map[x][y].add(players[idx].gun);
            players[idx].gun = map[x][y].poll();
        }
        return;
    }

    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}