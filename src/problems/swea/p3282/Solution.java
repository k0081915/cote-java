package problems.swea.p3282;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, K;
    static int[] vol;
    static int[] costs;
    static int answer;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            vol = new int[N];
            costs = new int[N];
            dp = new int[K + 1];
            answer = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                vol[i] = Integer.parseInt(st.nextToken());
                costs[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < N; i++) {
                int v = vol[i];
                int c = costs[i];

                for (int j = K; j >= v; j--) {
                    dp[j] = Math.max(dp[j], dp[j - v] + c);
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[K]).append("\n");
        }
        System.out.println(sb);
    }


}
