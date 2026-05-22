# SWEA 6485 리뷰

## 1) 문제 유형
- 구현
- 배열 카운팅
- 구간 처리

## 2) 문제 접근 방법
- 각 버스 노선은 `A`번 정류장부터 `B`번 정류장까지 모두 지난다.
- 질의로 주어진 정류장 `C`에 대해 몇 개의 노선이 `C`를 포함하는지 출력해야 한다.
- 현재 코드처럼 질의마다 모든 노선을 확인해도 정답이다.
- 정류장 번호 범위가 작으므로, 노선을 입력받을 때 `A`부터 `B`까지의 정류장 카운트를 미리 증가시켜도 된다.
- 이후 질의는 `count[C]`를 바로 출력하면 된다.

현재 `Solution.java`는 각 질의 정류장마다 모든 노선을 확인하고, `start <= c && c <= end`로 양끝 포함 조건을 올바르게 처리하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p6485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int MAX_STOP = 5000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] count = new int[MAX_STOP + 1];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                // 노선이 지나가는 모든 정류장의 카운트를 미리 증가시킨다.
                for (int stop = start; stop <= end; stop++) {
                    count[stop]++;
                }
            }

            sb.append('#').append(tc);

            int p = Integer.parseInt(br.readLine());
            for (int i = 0; i < p; i++) {
                int stop = Integer.parseInt(br.readLine());
                sb.append(' ').append(count[stop]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 현재 코드도 정답입니다. 질의 정류장 `c`가 각 노선의 `[start, end]` 안에 있는지 확인하는 방식이 맞습니다.
- 현재 코드는 노선 목록을 저장한 뒤, 질의마다 모든 노선을 다시 순회합니다.
- 추천 코드는 노선 입력 시 정류장별 통과 횟수를 미리 누적합니다.
- 현재 코드는 질의 하나당 `O(N)`, 추천 코드는 질의 하나당 `O(1)`입니다.
- 정류장 범위가 `1`부터 `5000`까지로 작기 때문에 배열 카운팅이 간단하고 효율적입니다.

## 5) 문제 해결 노하우
- 구간 `[A, B]`가 여러 개 나오고 특정 점의 포함 개수를 묻는 문제는 카운팅 배열이나 누적합을 먼저 생각하면 좋다.
- 이 문제는 정류장 범위가 작아서 직접 `A`부터 `B`까지 증가시켜도 충분하다.
- 범위가 훨씬 커지면 `diff[A]++`, `diff[B + 1]--` 후 누적합을 쓰는 방식이 더 좋다.
- 구간 문제에서는 양끝 포함인지 항상 확인해야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - 여러 노선이 같은 정류장에서 겹치는 경우
  - 노선의 시작 정류장과 끝 정류장을 질의하는 경우
  - 어떤 노선도 지나지 않는 정류장을 질의하는 경우
