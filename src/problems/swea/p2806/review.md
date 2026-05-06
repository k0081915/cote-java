# SWEA 2806 리뷰

## 1) 문제 유형
- DFS
- 백트래킹
- N-Queen
- 대각선 체크

## 2) 문제 접근 방법
- 한 행에 퀸을 하나씩 놓는 방식으로 DFS를 진행한다.
- 현재 행 `row`에서 가능한 모든 열 `col`을 시도한다.
- 같은 열에 이미 퀸이 있으면 놓을 수 없다.
- 대각선도 공격 범위이므로 두 방향 대각선을 함께 체크해야 한다.
- `row == N`이 되면 모든 행에 퀸을 놓은 것이므로 가능한 배치 수를 1 증가시킨다.

현재 `Solution.java`는 열과 두 대각선을 boolean 배열로 관리하는 정석 백트래킹 풀이이며, 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p2806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static int n;
    static int answer;
    static boolean[] usedColumn;
    static boolean[] usedDownDiagonal;
    static boolean[] usedUpDiagonal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            answer = 0;

            usedColumn = new boolean[n];
            usedDownDiagonal = new boolean[2 * n - 1];
            usedUpDiagonal = new boolean[2 * n - 1];

            dfs(0);
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int row) {
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            int downDiagonal = row - col + n - 1;
            int upDiagonal = row + col;

            if (usedColumn[col] || usedDownDiagonal[downDiagonal] || usedUpDiagonal[upDiagonal]) {
                continue;
            }

            usedColumn[col] = true;
            usedDownDiagonal[downDiagonal] = true;
            usedUpDiagonal[upDiagonal] = true;

            dfs(row + 1);

            usedColumn[col] = false;
            usedDownDiagonal[downDiagonal] = false;
            usedUpDiagonal[upDiagonal] = false;
        }
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 거의 같습니다. 현재 코드가 이미 정석 풀이에 가깝습니다.
- 추천 코드는 변수명을 `usedColumn`, `usedDownDiagonal`, `usedUpDiagonal`처럼 조금 더 길게 써서 의미를 드러냈습니다.
- 대각선 인덱스 계산은 현재 코드와 동일합니다.
- `row - col + n - 1`: 한 방향 대각선
- `row + col`: 다른 방향 대각선
- 현재 풀이에서 특별히 고칠 정답성 문제는 없습니다.

## 5) 문제 해결 노하우
- N-Queen은 "한 행에 하나씩 둔다"로 상태를 줄이면 2차원 보드를 직접 들고 다니지 않아도 된다.
- 같은 열 체크는 `col`, 대각선 체크는 `row - col`, `row + col` 패턴을 기억하면 된다.
- `row - col`은 음수가 될 수 있으므로 배열 인덱스로 쓸 때 `n - 1`을 더해준다.
- 검증은 아래 케이스를 보면 좋다:
  - `N = 1`이면 정답 `1`
  - `N = 4`이면 정답 `2`
  - `N = 8`이면 정답 `92`
