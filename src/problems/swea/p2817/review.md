# SWEA 2817 리뷰

## 1) 문제 유형
- DFS
- 백트래킹
- 부분집합
- 가지치기

## 2) 문제 접근 방법
- 각 숫자를 부분수열에 포함할지, 포함하지 않을지 선택하며 모든 조합을 확인한다.
- `idx`는 현재 확인할 원소 위치, `sum`은 지금까지 선택한 원소들의 합으로 둔다.
- 현재 합이 `K`를 넘으면 더 진행해도 `K`가 될 수 없으므로 탐색을 중단한다.
- SWEA 2817의 수열 원소는 양수이기 때문에 `sum > K` 가지치기가 안전하다.
- 모든 원소를 확인했을 때 `sum == K`이면 가능한 부분수열을 1개 찾은 것이다.

현재 `Solution.java`는 선택/미선택 DFS와 `sum > K` 가지치기를 올바르게 사용하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p2817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int target;
    static int count;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            numbers = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            count = 0;
            dfs(0, 0);

            sb.append('#').append(tc).append(' ').append(count).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int index, int sum) {
        // 모든 숫자가 양수이므로 목표 합을 넘은 뒤에는 다시 작아질 수 없다.
        if (sum > target) {
            return;
        }

        if (index == n) {
            if (sum == target) {
                count++;
            }
            return;
        }

        // 현재 숫자를 선택하는 경우와 선택하지 않는 경우를 모두 탐색한다.
        dfs(index + 1, sum + numbers[index]);
        dfs(index + 1, sum);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 코드도 `idx`번째 원소를 선택하거나 선택하지 않는 DFS로 모든 부분집합을 확인합니다.
- 현재 코드의 `sum > k` 가지치기도 맞습니다. 원소가 모두 양수라 더 탐색해도 합이 줄어들지 않기 때문입니다.
- 추천 코드는 변수명을 `target`, `count`, `numbers`로 조금 더 의미 있게 바꿨습니다.
- 추천 코드에는 가지치기 조건과 선택/미선택 분기의 의미를 주석으로 명확히 적었습니다.
- 현재 풀이에서 정답성 문제는 없습니다.

## 5) 문제 해결 노하우
- “부분수열의 합”은 순서를 바꾸지 않고 원소를 고르는 문제라, 각 원소마다 선택/미선택으로 생각하면 쉽다.
- 입력 원소가 양수인지 확인하면 가지치기 가능 여부를 판단할 수 있다.
- 양수만 있을 때는 `sum > K`에서 바로 종료해도 된다.
- 음수가 섞인 문제라면 나중에 합이 다시 줄어들 수 있으므로 같은 가지치기를 쓰면 안 된다.
- 검증은 아래 케이스를 보면 좋다:
  - `1 2 1 2`, `K=3`이면 정답 `4`
  - `1 2 3`, `K=6`이면 정답 `1`
  - `1 2 3`, `K=7`이면 정답 `0`
