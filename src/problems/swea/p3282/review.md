# SWEA 3282 리뷰

## 1) 문제 유형
- DP
- 0/1 Knapsack
- 배낭 문제
- 1차원 DP 최적화

## 2) 문제 접근 방법
- 각 물건은 한 번만 선택할 수 있다.
- `dp[capacity]`를 현재까지 본 물건들로 용량 `capacity` 이하에서 얻을 수 있는 최대 가치로 둔다.
- 물건 하나의 부피가 `volume`, 가치가 `value`라면 `dp[capacity - volume] + value`로 선택하는 경우를 계산한다.
- 선택하지 않는 경우는 기존 `dp[capacity]` 그대로다.
- 1차원 DP에서는 같은 물건을 여러 번 쓰지 않도록 용량을 큰 값에서 작은 값으로 역순 갱신해야 한다.

현재 `Solution.java`는 용량을 `K`부터 `v`까지 역순으로 순회하며 0/1 배낭 DP를 올바르게 처리하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p3282;

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
                int volume = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                // 0/1 배낭은 같은 물건을 한 번만 써야 하므로 용량을 역순으로 갱신한다.
                for (int capacity = limit; capacity >= volume; capacity--) {
                    dp[capacity] = Math.max(dp[capacity], dp[capacity - volume] + value);
                }
            }

            sb.append('#').append(tc).append(' ').append(dp[limit]).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 코드도 1차원 DP와 역순 순회로 0/1 배낭을 정확히 처리합니다.
- 현재 코드는 `vol`, `costs` 배열에 물건 정보를 모두 저장한 뒤 DP를 수행합니다.
- 추천 코드는 물건을 입력받자마자 DP에 반영해서 별도 물건 배열을 생략합니다.
- 현재 코드의 `answer` 변수는 사용되지 않으므로 제거해도 됩니다.
- 현재 풀이에서 정답성 문제는 없습니다.

## 5) 문제 해결 노하우
- “각 물건을 선택하거나 선택하지 않는다”는 구조는 0/1 Knapsack DP로 볼 수 있다.
- `dp[w]`의 의미를 “용량 w에서 얻을 수 있는 최대 가치”로 잡으면 전이가 단순해진다.
- 1차원 DP에서 용량을 정방향으로 순회하면 같은 물건을 여러 번 사용하는 완전배낭이 된다.
- 0/1 배낭은 반드시 `K`에서 `volume` 방향으로 역순 순회해야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - 용량을 정확히 채우는 조합이 최적인 경우
  - 한 물건을 중복으로 쓰면 더 커질 수 있지만 실제로는 한 번만 써야 하는 경우
  - 여러 물건 조합이 단일 물건보다 좋은 경우
