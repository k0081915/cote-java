package problems.swea.p1209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int t = Integer.parseInt(br.readLine());

            int[][] arr = new int[101][101];
            int sum;
            int max = 0;

            for (int i = 1; i <= 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                sum = 0;
                for (int j = 1; j <= 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    sum += arr[i][j];
                }
                max = Math.max(max, sum);
            }

            for (int i = 1; i <= 100; i++) {
                sum = 0;
                for (int j = 1; j <= 100; j++) {
                    sum += arr[j][i];
                }
                max = Math.max(max, sum);
            }

            sum = 0;
            int sum2 = 0;
            for (int i = 1, j = 100; i <= 100; i++, j--) {
                sum += arr[i][i];
                sum2 += arr[i][j];
            }
            max = Math.max(max, Math.max(sum,sum2));

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
}
