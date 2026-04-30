# SWEA 1244 리뷰

## 1) 문제 유형
- 백트래킹
- DFS
- 상태 중복 제거

## 2) 문제 접근 방법
- 주어진 교환 횟수를 정확히 모두 사용해야 하므로, DFS 깊이를 교환 횟수로 둔다.
- 각 깊이에서 가능한 모든 두 자리 조합 `(i, j)`를 교환한다.
- 같은 깊이에서 같은 숫자 상태가 다시 나오면 이후 탐색 결과도 같으므로 중복 탐색을 막는다.
- `depth == chance`가 되면 현재 숫자를 정답 후보로 갱신한다.

현재 `Solution.java`는 깊이별 방문 상태를 사용한 백트래킹으로 문제를 해결하고 있어 정답 방향입니다.

## 3) 정답 코드
```java
package problems.swea.p1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static char[] digits;
    static int limit;
    static int answer;
    static Set<String>[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            digits = st.nextToken().toCharArray();
            limit = Integer.parseInt(st.nextToken());
            answer = 0;

            @SuppressWarnings("unchecked")
            Set<String>[] sets = new HashSet[limit + 1];
            visited = sets;
            for (int i = 0; i <= limit; i++) {
                visited[i] = new HashSet<>();
            }

            dfs(0);
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int depth) {
        String state = new String(digits);
        if (!visited[depth].add(state)) {
            return;
        }

        if (depth == limit) {
            answer = Math.max(answer, Integer.parseInt(state));
            return;
        }

        for (int i = 0; i < digits.length - 1; i++) {
            for (int j = i + 1; j < digits.length; j++) {
                swap(i, j);
                dfs(depth + 1);
                swap(i, j);
            }
        }
    }

    static void swap(int i, int j) {
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 알고리즘은 같습니다. 모든 자리 교환을 DFS로 탐색하고, 깊이별 `visited`로 중복 상태를 제거하는 방식이 맞습니다.
- 정답 코드는 `Set<String>[]` 타입을 사용하고 `@SuppressWarnings("unchecked")`로 제네릭 배열 경고를 한 지점에 모읍니다.
- `visited[depth].add(state)`의 반환값을 바로 사용하면 `contains()` 후 `add()`를 따로 호출하지 않아도 됩니다.
- 현재 코드도 정답 방향이고, 추천 코드는 같은 풀이를 조금 더 간결하게 정리한 버전입니다.

## 5) 문제 해결 노하우
- "정확히 K번 교환" 문제는 그리디가 막히는 케이스가 많아서, 숫자 길이가 작다면 백트래킹을 먼저 고려하면 좋다.
- 같은 숫자 상태라도 남은 교환 횟수가 다르면 결과가 달라질 수 있으므로, 방문 처리는 전체가 아니라 깊이별로 해야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - 한 번만 바꾸면 되는 경우
  - 같은 숫자가 포함된 경우
  - 교환 횟수가 숫자 길이보다 큰 경우
