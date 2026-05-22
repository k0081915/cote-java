package problems.swea.p6485;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static List<int[]> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());

            list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.add(new int[]{a, b});
            }

            sb.append("#").append(tc);
            int p = Integer.parseInt(br.readLine());
            for (int i = 0; i < p; i++) {
                int c = Integer.parseInt(br.readLine());
                sb.append(" ").append(cross(c));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int cross(int c) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int start = list.get(i)[0];
            int end = list.get(i)[1];

            if (start <= c && c <= end) {
                cnt++;
            }
        }
        return cnt;
    }
}
