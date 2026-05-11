package problems.swea.p1213;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();

            String search = br.readLine();
            String str = br.readLine();

            int answer = 0;
            for (int i = 0; i < str.length() - search.length() + 1; i++) {
                if (search.equals(str.substring(i, i + search.length()))) {
                    answer++;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
