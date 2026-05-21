# SWEA 4615 리뷰

## 1) 문제 유형
- 구현
- 시뮬레이션
- 2차원 배열
- 8방향 탐색

## 2) 문제 접근 방법
- 오셀로 규칙을 그대로 구현하는 문제다.
- 입력의 `x y color`에서 `x`는 열, `y`는 행이므로 배열에는 `board[y][x]` 형태로 접근한다.
- 돌을 하나 놓은 뒤 8방향을 각각 확인한다.
- 한 방향으로 상대 돌이 연속해서 나오고, 그 끝이 내 돌로 막혀 있을 때만 지나온 상대 돌들을 뒤집는다.
- 중간에 빈 칸을 만나거나 보드 밖으로 나가면 그 방향은 뒤집을 수 없다.

현재 `Solution.java`는 좌표 변환, 초기 배치, 8방향 탐색, 뒤집기 조건을 모두 올바르게 처리하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p4615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int BLACK = 1;
    static final int WHITE = 2;
    static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    static int n;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            board = new int[n][n];
            initBoard();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int col = Integer.parseInt(st.nextToken()) - 1;
                int row = Integer.parseInt(st.nextToken()) - 1;
                int color = Integer.parseInt(st.nextToken());

                place(row, col, color);
            }

            int black = 0;
            int white = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (board[row][col] == BLACK) {
                        black++;
                    } else if (board[row][col] == WHITE) {
                        white++;
                    }
                }
            }

            sb.append('#').append(tc).append(' ')
                    .append(black).append(' ')
                    .append(white).append('\n');
        }

        System.out.print(sb);
    }

    static void initBoard() {
        int mid = n / 2;

        board[mid - 1][mid - 1] = WHITE;
        board[mid][mid] = WHITE;
        board[mid - 1][mid] = BLACK;
        board[mid][mid - 1] = BLACK;
    }

    static void place(int row, int col, int color) {
        board[row][col] = color;

        for (int[] direction : DIRECTIONS) {
            flip(row, col, color, direction[0], direction[1]);
        }
    }

    static void flip(int row, int col, int color, int dr, int dc) {
        int opposite = color == BLACK ? WHITE : BLACK;
        int nr = row + dr;
        int nc = col + dc;

        // 상대 돌이 연속되는 구간을 지나, 그 끝이 내 돌로 닫히는지 먼저 확인한다.
        while (inRange(nr, nc) && board[nr][nc] == opposite) {
            nr += dr;
            nc += dc;
        }

        if (!inRange(nr, nc) || board[nr][nc] != color) {
            return;
        }

        // 닫힌 구간이 확인된 방향만 다시 되돌아가며 상대 돌을 내 색으로 바꾼다.
        nr = row + dr;
        nc = col + dc;
        while (board[nr][nc] == opposite) {
            board[nr][nc] = color;
            nr += dr;
            nc += dc;
        }
    }

    static boolean inRange(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 코드도 8방향으로 상대 돌을 지나 내 돌을 만나면 다시 돌아가며 뒤집는 방식이라 맞습니다.
- 추천 코드는 `initBoard`, `place`, `flip`으로 역할을 나누어 오셀로 규칙을 더 읽기 쉽게 분리했습니다.
- 추천 코드는 `BLACK`, `WHITE`, `DIRECTIONS` 상수를 둬서 숫자 의미와 방향 배열의 의도를 명확히 했습니다.
- 현재 코드는 한 메서드 안에서 모두 처리하므로 제출용으로는 충분히 간결하고, 추천 코드는 리뷰와 유지보수 관점에서 흐름을 더 드러낸 버전입니다.

## 5) 문제 해결 노하우
- `x y` 입력이 나오면 바로 배열 인덱스로 쓰지 말고, `x = 열`, `y = 행`인지 먼저 확인한다.
- 오셀로 뒤집기는 한 칸씩 보자마자 바꾸면 안 된다. 먼저 그 방향이 내 돌로 닫히는지 확인한 뒤 뒤집어야 한다.
- 빈 칸이나 보드 밖을 만나면 해당 방향은 실패다.
- 8방향 탐색 문제는 방향 배열을 고정해 두면 조건문을 반복해서 쓰지 않아도 된다.
- 검증은 아래 케이스를 보면 좋다:
  - 한 방향으로만 뒤집히는 경우
  - 여러 방향이 동시에 뒤집히는 경우
  - 상대 돌이 있어도 끝이 빈 칸이라 뒤집히지 않는 경우
  - 가장자리 근처에서 보드 밖으로 나가는 경우
