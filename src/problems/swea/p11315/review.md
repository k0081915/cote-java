# SWEA 11315 리뷰

## 1) 문제 유형
- 구현
- 2차원 배열
- 방향 탐색
- 문자열 처리

## 2) 문제 접근 방법
- 보드의 각 칸을 확인하다가 `o`를 만나면 연속된 돌이 5개 이상 있는지 검사한다.
- 오목은 가로, 세로, 두 대각선 중 하나만 성립하면 된다.
- 한 줄은 양방향 중 한쪽 방향만 확인해도 모든 연속 구간을 찾을 수 있다.
- 따라서 오른쪽, 아래, 오른쪽 아래 대각선, 왼쪽 아래 대각선 4방향만 보면 충분하다.
- 한 방향으로 최대 5칸을 확인하면서 범위를 벗어나거나 `o`가 아니면 실패 처리한다.

현재 `Solution.java`는 모든 `o` 위치에서 8방향으로 연속된 `o`를 세고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p11315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static final int[][] DIRECTIONS = {
            {0, 1},   // 가로
            {1, 0},   // 세로
            {1, 1},   // 오른쪽 아래 대각선
            {1, -1}   // 왼쪽 아래 대각선
    };

    static int n;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            board = new char[n][n];

            for (int row = 0; row < n; row++) {
                board[row] = br.readLine().toCharArray();
            }

            String answer = hasFiveStones() ? "YES" : "NO";
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static boolean hasFiveStones() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] != 'o') {
                    continue;
                }

                for (int[] direction : DIRECTIONS) {
                    if (isFive(row, col, direction[0], direction[1])) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean isFive(int row, int col, int dr, int dc) {
        for (int step = 0; step < 5; step++) {
            int nr = row + dr * step;
            int nc = col + dc * step;

            // 5칸 중 하나라도 범위를 벗어나거나 돌이 아니면 이 방향은 실패다.
            if (!inRange(nr, nc) || board[nr][nc] != 'o') {
                return false;
            }
        }

        return true;
    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 코드도 `o` 위치에서 방향을 따라가며 연속 개수를 확인합니다.
- 현재 코드는 8방향을 모두 확인합니다.
- 추천 코드는 중복을 줄이기 위해 오른쪽, 아래, 오른쪽 아래, 왼쪽 아래 4방향만 확인합니다.
- 현재 코드는 `o` 위치를 `List<int[]>`에 저장한 뒤 다시 순회합니다.
- 추천 코드는 보드를 순회하면서 바로 검사하므로 별도 좌표 리스트가 필요 없습니다.
- 현재 풀이도 정답이고, 추천 코드는 방향과 상태를 더 단순화한 버전입니다.

## 5) 문제 해결 노하우
- 연속된 선을 찾는 2차원 배열 문제는 방향 배열을 먼저 정하면 코드가 훨씬 단순해진다.
- 양방향을 모두 볼 필요가 있는지 생각해보면 중복 탐색을 줄일 수 있다.
- “5개 이상” 조건은 시작점부터 5칸만 확인해도 충분하다. 6개 이상이면 그 안에 5개 연속 구간이 반드시 있기 때문이다.
- 경계 체크는 돌 확인보다 먼저 해야 배열 범위 오류를 피할 수 있다.
- 검증은 아래 케이스를 보면 좋다:
  - 가로로 5개 연속
  - 세로로 5개 연속
  - 두 대각선 방향으로 5개 연속
  - 4개만 연속되어 `NO`가 나오는 경우
