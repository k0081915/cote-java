package problems.swea.p1860;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static final int MAX_TIME = 111111;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 사람 수
            int m = Integer.parseInt(st.nextToken());   // 시간 초
            int k = Integer.parseInt(st.nextToken());   // 시간 내 만들 수 있는 붕어빵 수

            int[] arrival = new int[MAX_TIME + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arrival[Integer.parseInt(st.nextToken())] += 1;
            }

            String answer = "Impossible";
            int time = 0;
            int sum = 0;
            while (n > 0) {
                int people = arrival[time];
                if (people != 0) {
                    if (sum - people >= 0) {
                        sum -= people;
                        n -= people;
                        answer = "Possible";
                    } else {
                        answer = "Impossible";
                        break;
                    }
                }

                time++;

                if (time % m == 0) {
                    sum += k;
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
