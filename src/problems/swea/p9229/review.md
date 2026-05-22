# SWEA 9229 리뷰

## 1) 문제 유형
- 완전탐색
- 조합
- DFS
- 가지치기

## 2) 문제 접근 방법
- 과자 두 개를 골라 무게 합이 `M` 이하인 경우 중 가장 큰 합을 찾는다.
- 같은 과자를 두 번 고르면 안 되므로 서로 다른 인덱스 두 개를 선택해야 한다.
- 순서는 의미가 없기 때문에 `(i, j)`와 `(j, i)`를 둘 다 볼 필요는 없다.
- DFS로 풀면 `start` 인덱스를 넘겨 다음 과자는 현재보다 뒤쪽에서만 고르게 하면 조합이 된다.
- 두 개를 골랐을 때 합이 `M` 이하이면 정답 후보로 갱신한다.

현재 `Solution.java`는 서로 다른 두 과자를 선택하고 무게 제한을 확인하므로 정답으로 판단됩니다. 다만 순열처럼 같은 조합을 두 번씩 확인하므로, 추천 코드는 중복 없는 조합 DFS로 정리합니다.

## 3) 정답 코드
```java
package problems.swea.p9229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int limit;
    static int answer;
    static int[] snacks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());

            snacks = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                snacks[i] = Integer.parseInt(st.nextToken());
            }

            answer = -1;
            dfs(0, 0, 0);

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int start, int selectedCount, int sum) {
        if (sum > limit) {
            return;
        }

        if (selectedCount == 2) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = start; i < n; i++) {
            // 다음 선택은 i + 1부터 시작해 같은 조합을 순서만 바꿔 다시 보지 않는다.
            dfs(i + 1, selectedCount + 1, sum + snacks[i]);
        }
    }
}
```

## 4) 내 코드와 다른 부분
- 현재 코드도 두 개를 고른 뒤 `sum <= m`이면 최댓값을 갱신하므로 정답입니다.
- 현재 코드는 `check[]`로 사용 여부를 관리하면서 모든 인덱스를 다시 순회합니다.
- 그래서 과자 `0, 1`을 고르는 경우와 `1, 0`을 고르는 경우를 둘 다 탐색합니다.
- 추천 코드는 `start`를 사용해 다음 과자를 뒤쪽에서만 고르므로 각 조합을 한 번만 확인합니다.
- 추천 코드는 `sum > limit`일 때 바로 종료하는 가지치기를 추가했습니다.
- 이 문제는 두 개만 고르므로 사실 이중 반복문이 가장 단순한 풀이도 될 수 있습니다.

## 5) 문제 해결 노하우
- 두 개를 고르는 문제는 먼저 “순서가 의미 있는가?”를 확인해야 한다.
- 순서가 의미 없으면 순열이 아니라 조합으로 탐색해야 중복이 줄어든다.
- `start` 인덱스를 DFS 인자로 넘기면 조합 탐색을 깔끔하게 만들 수 있다.
- 정답 후보가 없을 때 `-1`을 출력해야 하므로 초기값을 `-1`로 두는 방식이 좋다.
- 검증은 아래 케이스를 보면 좋다:
  - 가능한 쌍이 여러 개 있고 최댓값을 골라야 하는 경우
  - 어떤 두 과자를 골라도 `M`을 넘어서 `-1`이 나오는 경우
  - 정확히 `M`이 되는 쌍이 있는 경우
