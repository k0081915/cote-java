# SWEA 4831 리뷰

## 1) 문제 유형
- 그리디
- 구현
- 배열

## 2) 문제 접근 방법
- 현재 위치에서 한 번 충전으로 갈 수 있는 범위는 `current + K`까지다.
- 목적지에 바로 도착할 수 있으면 더 충전할 필요가 없다.
- 도착할 수 없다면, 갈 수 있는 범위 안에서 가장 먼 충전소를 선택해야 충전 횟수를 최소화할 수 있다.
- 갈 수 있는 범위 안에 충전소가 하나도 없으면 목적지까지 갈 수 없으므로 정답은 `0`이다.

현재 `Solution.java`는 현재 위치에서 갈 수 있는 가장 먼 충전소를 선택하는 그리디 방식으로 구현되어 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p4831;

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
            int maxMove = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int chargerCount = Integer.parseInt(st.nextToken());

            boolean[] hasCharger = new boolean[destination + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < chargerCount; i++) {
                hasCharger[Integer.parseInt(st.nextToken())] = true;
            }

            int current = 0;
            int answer = 0;

            while (current + maxMove < destination) {
                int next = -1;

                // 충전 횟수를 줄이기 위해 현재 위치에서 도달 가능한 가장 먼 충전소를 고른다.
                for (int stop = current + maxMove; stop > current; stop--) {
                    if (hasCharger[stop]) {
                        next = stop;
                        break;
                    }
                }

                // 갈 수 있는 충전소가 없으면 목적지 도달이 불가능하다.
                if (next == -1) {
                    answer = 0;
                    break;
                }

                current = next;
                answer++;
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 갈 수 있는 범위 안에서 가장 먼 충전소를 찾는 방식이 맞습니다.
- 현재 코드는 `flag`로 충전소를 찾았는지 관리합니다.
- 추천 코드는 `next = -1`을 사용해 다음 충전 위치가 정해졌는지 표현했습니다.
- 변수명을 `maxMove`, `destination`, `hasCharger`, `current`처럼 역할 중심으로 정리했습니다.
- 현재 코드도 정답이고, 추천 코드는 상태 의미가 조금 더 직접적으로 드러나는 버전입니다.

## 5) 문제 해결 노하우
- "최소 충전 횟수"는 현재 위치에서 최대한 멀리 가는 선택이 유리한 전형적인 그리디 구조다.
- 현재 위치에서 갈 수 있는 범위를 뒤에서부터 확인하면 가장 먼 충전소를 바로 찾을 수 있다.
- 목적지에 이미 도달 가능한 경우에는 충전소를 찾지 않아도 된다.
- 검증은 아래 케이스를 보면 좋다:
  - 목적지까지 바로 갈 수 있는 경우
  - 중간에 여러 번 충전해야 하는 경우
  - 갈 수 있는 범위 안에 충전소가 없어 실패하는 경우
