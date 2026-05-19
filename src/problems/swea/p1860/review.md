# SWEA 1860 리뷰

## 1) 문제 유형
- 구현
- 정렬
- 시뮬레이션

## 2) 문제 접근 방법
- 손님 도착 시간을 오름차순으로 정렬한다.
- `time`초까지 만들 수 있는 붕어빵 수는 `(time / M) * K`개다.
- 정렬된 순서에서 `i`번째 손님까지 처리하려면 최소 `i + 1`개의 붕어빵이 필요하다.
- 어떤 손님 도착 시점에 생산량이 필요한 개수보다 적으면 `Impossible`이다.
- 모든 손님을 처리할 수 있으면 `Possible`이다.

현재 `Solution.java`는 시간별 도착 인원과 재고를 시뮬레이션하며 손님을 처리하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1860;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arrivals = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arrivals[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrivals);

            boolean possible = true;
            for (int i = 0; i < n; i++) {
                // arrivals[i]초까지 완성된 붕어빵 수와 지금까지 온 손님 수를 비교한다.
                int madeBread = (arrivals[i] / m) * k;
                int neededBread = i + 1;

                // 한 명이라도 도착 시점에 받을 붕어빵이 없으면 전체가 Impossible이다.
                if (madeBread < neededBread) {
                    possible = false;
                    break;
                }
            }

            sb.append('#').append(tc).append(' ')
                    .append(possible ? "Possible" : "Impossible")
                    .append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 현재 코드는 시간별 도착 인원 배열을 만들고, 매초 재고를 갱신하며 시뮬레이션합니다. 이 방식도 정답입니다.
- 추천 코드는 도착 시간을 정렬한 뒤 각 손님 도착 시점의 누적 생산량만 비교합니다.
- 시간 전체를 순회하지 않아도 되므로 `MAX_TIME` 같은 상수가 필요 없습니다.
- `i`번째 손님까지 필요한 붕어빵 수가 `i + 1`로 바로 드러나서 검증식이 더 간단합니다.
- 핵심은 "각 도착 시점까지 만들어진 붕어빵 수 >= 지금까지 도착한 손님 수"입니다.

## 5) 문제 해결 노하우
- 도착 시간이 있는 문제는 먼저 정렬해서 시간 순서대로 처리하면 상태가 단순해진다.
- 특정 시점까지 주기적으로 생산되는 수량은 `(time / 주기) * 생산량`으로 바로 계산할 수 있다.
- 모든 손님에게 줄 수 있는지 묻는 문제는 각 시점의 누적 공급량과 누적 수요량을 비교하면 된다.
- 검증은 아래 케이스를 보면 좋다:
  - 첫 손님이 0초 또는 생산 전 시점에 오는 경우
  - 같은 시간에 여러 손님이 오는 경우
  - 모든 손님을 처리할 수 있는 경우
  - 중간 시점에 재고가 부족해지는 경우
