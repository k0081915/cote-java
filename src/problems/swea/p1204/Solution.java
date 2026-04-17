package problems.swea.p1204;

import java.util.*;
import java.io.*;

public class Solution {

    public static void main(String[] args) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();


        for(int test_case = 1; test_case <= T; test_case++)
        {
            int[] scores = new int[101];

            sc.nextInt();
            for(int i = 0; i < 1000; i++) {
                scores[sc.nextInt()]++;
            }

            int max = 0, idx = 0;
            for(int i = 1; i <= 100; i++) {
                if(max <= scores[i]) {
                    max = scores[i];
                    idx = i;
                }
            }

            System.out.printf("#%d %d\n", test_case, idx);
        }

    }
}
