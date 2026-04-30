package problems.swea.p1244;

import java.io.*;
import java.util.*;

public class Solution {

    static char[] numbers;
    static int chance;
    static int answer;
    static HashSet<String>[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            numbers = st.nextToken().toCharArray();
            chance = Integer.parseInt(st.nextToken());

            answer = 0;

            // 교환 횟수별로 방문한 숫자 상태를 저장
            visited = new HashSet[chance + 1];
            for (int i = 0; i <= chance; i++) {
                visited[i] = new HashSet<>();
            }

            dfs(0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth) {
        // 현재 숫자 상태를 문자열로 변환
        String cur = new String(numbers);

        // 같은 depth에서 같은 숫자 상태가 이미 나왔다면 중복 탐색이므로 종료
        if (visited[depth].contains(cur)) {
            return;
        }
        visited[depth].add(cur);

        // 교환 횟수를 모두 사용했다면 정답 갱신
        if (depth == chance) {
            answer = Math.max(answer, Integer.parseInt(cur));
            return;
        }

        int n = numbers.length;

        // 모든 두 자리 (i,j)를 선택해서 swap 시도
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(i, j); // 자리 교환
                dfs(depth + 1); // 다음 교환 횟수로 진행
                swap(i, j); // 원상복구(백트래킹)
            }
        }
    }

    static void swap(int i, int j) {
        char temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
