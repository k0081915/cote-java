package problems.swea.p4615;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int[][] board;

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   // 보드 길이 (4, 6, 8)
            int m = Integer.parseInt(st.nextToken());   // 돌 놓는 횟수

            board = new int[n][n];
            board[n / 2 - 1][n / 2] = 1;
            board[n / 2][n / 2 - 1] = 1;
            board[n / 2 - 1][n / 2 - 1] = 2;
            board[n / 2][n / 2] = 2;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int col = Integer.parseInt(st.nextToken()) - 1;
                int row = Integer.parseInt(st.nextToken()) - 1;
                int color = Integer.parseInt(st.nextToken());

                board[row][col] = color;

                for (int j = 0; j < 8; j++) {
                    int opposite = color == 1 ? 2 : 1;
                    int nr = row + dr[j];
                    int nc = col + dc[j];

                    while (isPossible(nr, nc) && board[nr][nc] == opposite) {
                        nr += dr[j];
                        nc += dc[j];
                    }

                    if (isPossible(nr, nc) && board[nr][nc] == color) {
                        nr = row + dr[j];
                        nc = col + dc[j];
                        while (board[nr][nc] == opposite) {
                            board[nr][nc] = color;
                            nr += dr[j];
                            nc += dc[j];
                        }
                    }
                }
            }

            int black = 0;
            int white = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 1) {
                        black++;
                    } else if (board[i][j] == 2) {
                        white++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(black).append(" ").append(white).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isPossible(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < n;
    }
}
