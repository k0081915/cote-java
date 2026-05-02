package problems.swea.p1240;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static List<String> code = Arrays.asList("0001101", "0011001", "0010011", "0111101", "0100011",
            "0110001", "0101111", "0111011", "0110111", "0001011");

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            String password = "";
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                if (line.contains("1")) {
                    for (int j = m - 1; j >= 0; j--) {
                        if (line.charAt(j) == '1') {
                            password = line.substring(j - 55, j + 1);
                            break;
                        }
                    }
                }
            }

            int[] numbers = new int[8];
            for (int i = 0, j = 0; i <= password.length() - 7; i += 7, j++) {
                String str = password.substring(i, i + 7);
                numbers[j] = code.indexOf(str);
            }

            int sum = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (i % 2 == 0) {
                    sum += numbers[i] * 3;
                } else {
                    sum += numbers[i];
                }
            }

            int answer = 0;
            if (sum % 10 == 0) {
                answer = Arrays.stream(numbers).sum();
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
