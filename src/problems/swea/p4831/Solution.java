package problems.swea.p4831;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static boolean[] charger;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            charger = new boolean[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                // 충전기 위치 표시
                charger[Integer.parseInt(st.nextToken())] = true;
            }

            int answer = 0;
            int pos = 0;
            while (pos + k < n) {
                boolean flag = false;

                for (int i = pos + k; i > pos; i--) {
                    if (charger[i]) {
                        pos = i;
                        answer++;
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    answer = 0;
                    break;
                }

            }


            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
