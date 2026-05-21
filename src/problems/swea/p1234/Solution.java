package problems.swea.p1234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            char[] str = st.nextToken().toCharArray();
            List<Character> list = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                list.add(str[i]);
            }

            int idx = 0;
            while (idx < list.size() - 1) {
                if (list.get(idx) == list.get(idx + 1)) {
                    for (int i = 0; i < 2; i++) {
                        list.remove(idx);
                    }

                    idx--;
                    if (idx <= 0) {
                        idx = 0;
                    }

                    continue;
                }

                idx++;
            }

            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                answer.append(list.get(i));
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
