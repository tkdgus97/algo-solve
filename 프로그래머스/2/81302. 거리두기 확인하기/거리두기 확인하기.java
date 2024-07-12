import java.util.*;
class Solution {
        private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        int idx = 0;
        for (String[] place : places) {
            boolean flag = true;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (place[i].charAt(j) == 'P') {
                        if (!bfs(i, j, place)) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (!flag) break;
            }

            if (flag) answer[idx] = 1;
            idx++;
        }
        return answer;
    }

    private static boolean bfs(int r, int c, String[] place) {
        boolean[][] visit = new boolean[5][5];

        Queue<int[]> q = new LinkedList<>();
        visit[r][c] = true;
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                int dis = (Math.abs(nx - r) + Math.abs(ny - c));

                if ((nx >= 0 && nx < 5) && (ny >= 0 && ny < 5) && dis <= 2 && !visit[nx][ny]) {
                    visit[nx][ny] = true;

                    if (place[nx].charAt(ny) == 'P' && dis == 1) {
                        return false;
                    }
                    if (place[nx].charAt(ny) == 'P' && dis == 2) {
                        return false;
                    }
                    if (place[nx].charAt(ny) == 'O' && dis == 1) {
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return true;
    }
}