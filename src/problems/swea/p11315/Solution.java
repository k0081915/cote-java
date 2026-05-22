package problems.swea.p11315;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static char[][] board;
    static List<int[]> list;

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            board = new char[n][n];
            list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < n; j++) {
                    char ch = str.charAt(j);
                    board[i][j] = ch;
                    if (ch == 'o') {
                        list.add(new int[]{i, j});
                    }
                }
            }

            String answer = "NO";
            for (int i = 0; i < list.size(); i++) {
                int cr = list.get(i)[0];
                int cc = list.get(i)[1];
                if (check(cr, cc)) {
                    answer = "YES";
                    break;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static boolean check(int cr, int cc) {

        for (int i = 0; i < 8; i++) {
            int sum = 1;
            int nr = cr + dr[i];
            int nc = cc + dc[i];

            while (isPossible(nr, nc) && board[nr][nc] == 'o') {
                nr += dr[i];
                nc += dc[i];
                sum++;

                if (sum >= 5) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isPossible(int nr, int nc) {
        return 0 <= nr && nr < n && 0 <= nc && nc < n;
    }
}
