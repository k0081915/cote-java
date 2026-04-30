package problems.swea.p2805;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                String line = br.readLine();
                for (int j = 0; j < n; j++) {
                    arr[i][j + 1] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }

            int sum = 0;
            int start = 0;
            int mid = n / 2 + 1;

            for (int i = 0; i < n; i++) {
                for (int j = mid - start; j <= mid + start; j++) {
                    sum += arr[j][i + 1];
                }
                if (i >= mid - 1) {
                    start--;
                } else {
                    start++;
                }
            }

            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }

        System.out.println(sb);
    }
}
