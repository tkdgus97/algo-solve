import java.util.*;
import java.io.*;
public class Main {
    static class Virus {
        int r;
        int c;
        int age;
        int v;
        public Virus(int r, int c, int age, int v) {
            this.r = r;
            this.c = c;
            this.age = age;
            this.v = v;
        }
    }
    private static int n,m,k;
    private static int[] dx = {-1,0,1,0, -1,-1, 1,1};
    private static int[] dy = {0,1,0,-1, -1,1,1,-1};
    private static int[][] food;
    private static List<Virus> viruses = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());

        food = new int[n][n];

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < n; c++) {
                food[r][c] = stoi(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = stoi(st.nextToken()) - 1;
            int c = stoi(st.nextToken()) - 1;
            int age = stoi(st.nextToken());
            viruses.add(new Virus(r,c,age, 0));
        }
        int result = simul();
        System.out.println(result);
    }

    private static int simul() {
        int[][] map = new int[n][n];
        for(int r = 0; r < n; r++) {
            Arrays.fill(map[r], 5);
        }
        int time = 0;
        while(time < k) {
            //viruses.sort((o1,o2) -> {
            //    if(o1.v == o2.v) {
            //        return Integer.compare(o1.age, o2.age);
            //    }
            //    return Integer.compare(o2.v, o1.v);
            //});

            List<Integer> dead = new ArrayList<>();
            //1
            for(int i = 0; i < viruses.size(); i++) {
                Virus virus = viruses.get(i);
                if(time == virus.v) {
                    if(map[virus.r][virus.c] >= virus.age) {
                        //System.out.println(virus.r + " " + virus.c + " " + virus.age + " " + virus.v);
                        map[virus.r][virus.c] -= virus.age;
                        virus.age += 1;
                        virus.v += 1;
                    } else {
                        dead.add(i);
                    }
                }
            }

            //2
            for(int i = 0; i < dead.size(); i++) {
                Virus virus = viruses.get(dead.get(i));
                virus.v = -1;
                map[virus.r][virus.c] += (virus.age / 2);
            }
            //3
            for(int i = 0; i < viruses.size(); i++) {
                Virus virus = viruses.get(i);
                if((time + 1) == virus.v) {
                    if(virus.age % 5 == 0) {
                        // System.out.println(virus.r + " " + virus.c + " " + virus.v);
                        for(int j = 0; j < 8; j++) {
                            int nx = virus.r + dx[j];
                            int ny = virus.c + dy[j];

                            if(checkRange(nx, ny)) {
                                viruses.add(new Virus(nx, ny, 1, time + 1));
                            }
                        }
                    }
                }
            }

            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    map[r][c] += food[r][c];
                }
            }
            time++;
        }
        int result = 0;
        for(int i = 0; i < viruses.size(); i++) {
            Virus virus = viruses.get(i);
            if(k == virus.v) {

                result++;
            }
        }
        return result;
    }

    private static boolean checkRange(int nx, int ny) {
        return (nx >= 0 && nx < n) && (ny >= 0 && ny < n);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}