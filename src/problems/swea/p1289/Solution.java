package problems.swea.p1289;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            String str = br.readLine();
            char current = '0'; // 현재 bit 상태

            int answer = 0;
            for (int i = 0; i < str.length(); i++) {
                // 문자열 왼쪽부터 확인하면서 비트 상태가 다르면 교체
                if (str.charAt(i) != current) {
                    current = current == '0' ? '1' : '0';
                    answer++;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
