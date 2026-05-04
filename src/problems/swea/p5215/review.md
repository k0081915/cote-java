# SWEA 5215 리뷰

## 1) 문제 유형
- DFS
- 백트래킹
- 0-1 배낭 DP

## 2) 문제 접근 방법
- 각 재료는 선택하거나 선택하지 않는 두 가지 경우만 있다.
- DFS로 풀면 `index`번째 재료를 선택하는 경우와 선택하지 않는 경우를 모두 탐색한다.
- 칼로리 합이 제한 `L`을 넘으면 더 탐색할 필요가 없으므로 가지치기한다.
- 더 효율적으로는 칼로리를 용량으로 보는 0-1 배낭 DP를 사용할 수 있다.
- `dp[c]`: 칼로리 `c` 이하로 만들 수 있는 최대 맛 점수

현재 `Solution.java`는 DFS + 가지치기로 모든 가능한 조합을 확인하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());

            int[] dp = new int[limit + 1];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());

                for (int c = limit; c >= calorie; c--) {
                    dp[c] = Math.max(dp[c], dp[c - calorie] + score);
                }
            }

            sb.append('#').append(tc).append(' ').append(dp[limit]).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 현재 코드는 DFS로 각 재료의 선택/비선택을 모두 탐색합니다. 이 방식도 정답입니다.
- 추천 코드는 0-1 배낭 DP입니다. 칼로리 제한 안에서 최대 점수를 구하는 문제라 DP가 더 직접적입니다.
- DP에서 칼로리를 뒤에서 앞으로 순회하는 이유는 같은 재료를 한 번만 사용하기 위해서입니다.
- DFS는 `2^N` 조합을 확인하고, DP는 `N * L` 범위에서 해결합니다.
- 현재 코드의 DFS는 백트래킹 학습에는 좋고, 추천 코드는 같은 문제를 더 전형적인 최적화 문제로 푸는 버전입니다.

## 5) 문제 해결 노하우
- "각 물건을 한 번씩만 선택할 수 있고, 제한 용량 안에서 최대 가치" 형태는 0-1 배낭 문제로 볼 수 있다.
- DFS로 먼저 생각했다면 상태를 `index`, `scoreSum`, `calSum`으로 잡으면 된다.
- DP로 바꿀 때는 `dp[칼로리] = 최대 점수`로 정의하면 자연스럽다.
- 검증은 아래 케이스를 보면 좋다:
  - 아무 재료도 선택할 수 없는 경우
  - 하나의 재료만 선택하는 것이 최적인 경우
  - 여러 재료 조합이 최적인 경우
  - 칼로리 합이 정확히 제한과 같은 경우
