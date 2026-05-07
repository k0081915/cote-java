package problems.swea.p1220;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int width = Integer.parseInt(br.readLine());
            int[][] table = new int[width][width];

            for (int i = 0; i < width; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < width; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for (int i = 0; i < width; i++) {
                boolean flag = false;
                for (int j = 0; j < width; j++) {
                    if (table[j][i] == 1) {
                        flag = true;
                    } else if (table[j][i] == 2 && flag) {
                        answer++;
                        flag = false;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
