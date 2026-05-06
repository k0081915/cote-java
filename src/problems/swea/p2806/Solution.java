package problems.swea.p2806;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int N;
    static int answer;

    static boolean[] colUsed;   // 특정 열에 이미 퀸이 놓였는지 체크
    static boolean[] diag1Used; // ↘ 방향 대각선 체크: row - col + N - 1
    static boolean[] diag2Used; // ↙ 방향 대각선 체크: row + col

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            N = Integer.parseInt(br.readLine());

            answer = 0;

            colUsed = new boolean[N];
            // 대각선 개수는 각각 최대 2N - 1개
            diag1Used = new boolean[2 * N - 1];
            diag2Used = new boolean[2 * N - 1];

            // 0번 행부터 퀸을 놓기 시작
            dfs(0);

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int row) {
        // N개의 행에 모두 퀸을 놓았다면 가능한 배치 1개 완성
        if (row == N) {
            answer++;
            return;
        }

        // 현재 row행에서 모든 col열을 시도
        for (int col = 0; col < N; col++) {
            int diag1 = row - col + N - 1;  // ↘ 방향 대각선 번호
            int diag2 = row + col;  // ↙ 방향 대각선 번호

            // 같은 열 또는 같은 대각선에 퀸이 있으면 놓을 수 없음
            if (colUsed[col] || diag1Used[diag1] || diag2Used[diag2]) {
                continue;
            }

            // 현재 위치(row, col)에 퀸을 놓음
            colUsed[col] = true;
            diag1Used[diag1] = true;
            diag2Used[diag2] = true;

            // 다음 행으로 이동
            dfs(row + 1);

            // 다른 경우를 탐색하기 위해 원상복구
            colUsed[col] = false;
            diag1Used[diag1] = false;
            diag2Used[diag2] = false;
        }
    }

}
