package problems.swea.p1208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            int[] arr = new int[100];
            int dump = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < dump; i++) {
                Arrays.sort(arr);
                arr[0]++;
                arr[arr.length - 1]--;
            }

            Arrays.sort(arr);
            int answer = arr[arr.length - 1] - arr[0];

            sb.append("#").append(tc).append(" ").append(answer).append("\n");

        }
        System.out.println(sb);
    }
}
