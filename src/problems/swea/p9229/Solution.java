package problems.swea.p9229;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n, m;
    static int[] snack;
    static boolean[] check;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   // 과자 수
            m = Integer.parseInt(st.nextToken());   // 무게 제한
            snack = new int[n];
            check = new boolean[n];
            answer = -1;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                snack[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int sum) {
        if (depth == 2) {
            if (sum <= m) {
                answer = Math.max(answer, sum);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                check[i] = true;
                dfs(depth + 1, sum + snack[i]);
                check[i] = false;
            }
        }
    }
}
