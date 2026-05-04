package problems.swea.p5215;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, L;
    static int[] score;
    static int[] cal;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            score = new int[N];
            cal = new int[N];
            answer = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            // 0번 재료부터 탐색
            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int index, int scoreSum, int calSum) {
        // 제한 칼로리를 넘으면 더 이상 탐색할 필요 없음
        if (calSum > L) {
            return;
        }

        // 모든 재료를 확인했으면 최댓값 갱신 후 빠져나옴
        if (index == N) {
            answer = Math.max(scoreSum, answer);
            return;
        }

        // 현재 재료를 선택하는 경우
        dfs(index + 1, scoreSum + score[index], calSum + cal[index]);
        // 현재 재료를 선택하지 않는 경우
        dfs(index + 1, scoreSum, calSum);
    }
}
