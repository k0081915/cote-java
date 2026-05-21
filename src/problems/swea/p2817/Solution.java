package problems.swea.p2817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n, k;
    static int answer;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n];
            answer = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int idx, int sum) {
        if (sum > k) {
            return;
        }

        if (idx == n) {
            if (sum == k) {
                answer++;
            }
            return;
        }

        dfs(idx + 1, sum + arr[idx]);
        dfs(idx + 1, sum);
    }
}
