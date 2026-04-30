package problems.swea.p1206;

import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= 10; t++)
        {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] buildings = new int[N];

            for(int i = 0; i < N; i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;
            for(int i = 2; i < N - 2; i++) {
                int diff1 = buildings[i] - buildings[i - 2];
                int diff2 = buildings[i] - buildings[i - 1];
                int diff3 = buildings[i] - buildings[i + 1];
                int diff4 = buildings[i] - buildings[i + 2];

                if(i == 2) {
                    if(diff3 <= 0 || diff4 <= 0) {
                        continue;
                    }
                    sum += Math.min(diff3, diff4);
                } else if(i == N - 3) {
                    if(diff1 <= 0 || diff2 <= 0) {
                        continue;
                    }
                    sum += Math.min(diff1, diff2);
                } else {
                    if(diff1 <= 0 || diff2 <= 0 || diff3 <= 0 || diff4 <= 0) {
                        continue;
                    }
                    sum += Math.min(diff1, Math.min(diff2, Math.min(diff3, diff4)));
                }
            }

            sb.append('#').append(t).append(' ').append(sum).append('\n');
        }

        System.out.println(sb);
    }
}
