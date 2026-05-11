package problems.swea.p1217;

import java.util.Scanner;

public class Solution {

    static int n, m;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int tc = 1; tc <= 10; tc++) {
            sc.nextInt();

            n = sc.nextInt();
            m = sc.nextInt();

            int answer = recur(1, n);

            System.out.printf("#%d %d\n", tc, answer);
        }
    }

    static int recur(int cnt, int mul) {
        if (cnt == m) {
            return mul;
        }

        return recur(cnt + 1, mul * n);
    }
}
