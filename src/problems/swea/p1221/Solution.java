package problems.swea.p1221;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

    static HashMap<String, Integer> strToNum = new HashMap<>();
    static HashMap<Integer, String> numToStr = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];

            init();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = strToNum.get(st.nextToken());
            }

            Arrays.sort(arr);

            sb.append("#").append(tc).append("\n");
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(numToStr.get(arr[i])).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void init() {
        strToNum.put("ZRO", 0);
        strToNum.put("ONE", 1);
        strToNum.put("TWO", 2);
        strToNum.put("THR", 3);
        strToNum.put("FOR", 4);
        strToNum.put("FIV", 5);
        strToNum.put("SIX", 6);
        strToNum.put("SVN", 7);
        strToNum.put("EGT", 8);
        strToNum.put("NIN", 9);

        numToStr.put(0, "ZRO");
        numToStr.put(1, "ONE");
        numToStr.put(2, "TWO");
        numToStr.put(3, "THR");
        numToStr.put(4, "FOR");
        numToStr.put(5, "FIV");
        numToStr.put(6, "SIX");
        numToStr.put(7, "SVN");
        numToStr.put(8, "EGT");
        numToStr.put(9, "NIN");
    }
}
