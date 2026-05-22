package problems.swea.p6808;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] gyu;
    static int[] in;
    static boolean[] used;
    static boolean[] selected;

    static int win;
    static int lose;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            win = lose = 0;
            gyu = new int[9];
            in = new int[9];
            used = new boolean[19];
            selected = new boolean[9];

            for (int i = 0; i < 9; i++) {
                int card = Integer.parseInt(st.nextToken());
                in[i] = card;
                used[card] = true;
            }

            int idx = 0;
            for (int i = 1; i <= 18; i++) {
                if (!used[i]) {
                    gyu[idx++] = i;
                }
            }

            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(lose).append(" ").append(win).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int gyuScore, int inScore) {
        if (depth == 9) {
            if (gyuScore > inScore) {
                win++;
            } else if(gyuScore < inScore) {
                lose++;
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!selected[i]) {
                selected[i] = true;

                int gyuCard = gyu[depth];
                int inCard = in[i];
                int sum = gyuCard + inCard;

                if (gyuCard > inCard) {
                    dfs(depth + 1, gyuScore + sum, inScore);
                } else {
                    dfs(depth + 1, gyuScore, inScore + sum);
                }

                selected[i] = false;
            }
        }
    }
}
