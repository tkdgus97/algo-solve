import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];

        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && picture[i][j] != 0) {
                    visit[i][j] = true;
                    maxSizeOfOneArea = Math.max(bfs(m,n,picture, i,j, visit), maxSizeOfOneArea);
                    numberOfArea++;
                }
            }
        }
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private static int bfs(int m, int n, int[][] map, int x, int y, boolean[][] visit) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        int area = 1;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if ((nx >= 0 && nx < m) && (ny >= 0 && ny < n) && map[nx][ny] == map[now[0]][now[1]] && !visit[nx][ny]) {
                    area++;
                    visit[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }

            }
        }
        return area;

    }


}