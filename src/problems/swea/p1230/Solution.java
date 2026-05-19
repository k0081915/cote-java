package problems.swea.p1230;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            list = new ArrayList<>();
            int n = Integer.parseInt(br.readLine());    // 원본 암호문 개수

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(br.readLine());    // 명령어 개수
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int x, y;
                String cmd = st.nextToken();
                switch (cmd) {
                    case "I":   // x 다음 y개 삽입
                        List<Integer> insertList = new ArrayList<>();
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            insertList.add(Integer.parseInt(st.nextToken()));
                        }
                        insert(x, y, insertList);
                        break;
                    case "D":   // x 다음 y개 삭제
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        delete(x, y);
                        break;
                    case "A":   // 맨 뒤 y개 추가
                        List<Integer> addList = new ArrayList<>();
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            addList.add(Integer.parseInt(st.nextToken()));
                        }
                        add(y, addList);
                        break;
                }
            }

            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void insert(int x, int y, List<Integer> insertList) {
        for (int i = 0; i < y; i++) {
            list.add(x + i, insertList.get(i));
        }
    }

    static void delete(int x, int y) {
        for (int i = 0; i < y; i++) {
            list.remove(x);
        }
    }

    static void add(int y, List<Integer> addList) {
        for (int i = 0; i < y; i++) {
            list.add(list.size(), addList.get(i));
        }
    }
}
