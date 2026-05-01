package problems.swea.p1225;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int t = Integer.parseInt(br.readLine());
            arr = new int[9];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int minus = 1;
            while (true) {
                if (minus > 5) {
                    minus = 1;
                }

                arr[0] -= minus++;
                if (arr[0] <= 0) {
                    arr[0] = 0;
                    arrange();
                    break;
                }
                arrange();
            }

            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                answer.append(arr[i]).append(" ");
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void arrange() {
        arr[8] = arr[0];
        for (int i = 1; i <= 8; i++) {
            arr[i - 1] = arr[i];
        }
    }
}
