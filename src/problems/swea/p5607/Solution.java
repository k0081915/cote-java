package problems.swea.p5607;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static final long MOD = 1234567891L;
    static final int maxN = 1000000;
    static long[] fact;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        fact = new long[maxN + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());


            long top = fact[n];
            long bottom = fact[r] * fact[n - r] % MOD;

            long answer = top * pow(bottom, MOD - 2) % MOD;

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static long pow(long base, long exp) {
        if (exp == 0) {
            return 1;
        }

        long half = pow(base, exp / 2);
        long result = half * half % MOD;

        if (exp % 2 == 1) {
            result = result * base % MOD;
        }

        return result;
    }
}
