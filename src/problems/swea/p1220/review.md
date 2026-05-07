# SWEA 1220 리뷰

## 1) 문제 유형
- 구현
- 2차원 배열
- 시뮬레이션
- 상태 관리

## 2) 문제 접근 방법
- 자성체는 열 방향으로만 떨어지므로 각 열을 위에서 아래로 확인하면 된다.
- `1`은 N극 성질을 가진 자성체이고 아래쪽으로 이동하려 한다.
- `2`는 S극 성질을 가진 자성체이고 위쪽으로 이동하려 한다.
- 위에서 아래로 보다가 `1`을 만난 뒤 `2`를 만나면 교착 상태가 1개 생긴다.
- 교착을 하나 세면 같은 `1`로 다시 세면 안 되므로 상태를 초기화한다.

현재 `Solution.java`는 각 열을 위에서 아래로 스캔하며 `1 -> 2` 쌍을 세고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            boolean[] hasNorth = new boolean[n];
            int answer = 0;

            for (int row = 0; row < n; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int col = 0; col < n; col++) {
                    int value = Integer.parseInt(st.nextToken());

                    if (value == 1) {
                        hasNorth[col] = true;
                    } else if (value == 2 && hasNorth[col]) {
                        answer++;
                        hasNorth[col] = false;
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 각 열에서 `1`을 본 뒤 `2`가 나오면 교착으로 세는 방식이 맞습니다.
- 현재 코드는 전체 `table`을 저장한 뒤 열을 다시 순회합니다.
- 추천 코드는 입력을 위에서 아래로 읽는 동안 열별 상태를 바로 갱신해서 `int[][]` 저장을 생략합니다.
- `flag` 하나를 열마다 쓰는 대신, 추천 코드는 `hasNorth[col]`로 각 열의 현재 상태를 유지합니다.
- 현재 코드도 정답이고, 추천 코드는 메모리와 순회를 조금 줄인 버전입니다.

## 5) 문제 해결 노하우
- 2차원 배열 문제라도 한 방향으로만 의미가 있으면 전체 배열 저장 없이 상태만 유지할 수 있다.
- 이 문제는 실제 자성체 이동을 모두 시뮬레이션할 필요 없이, 최종적으로 남는 `1 -> 2` 패턴만 세면 된다.
- 교착을 센 뒤에는 같은 N극 상태를 다시 쓰면 중복 카운트가 되므로 상태를 꺼야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - 한 열에 `1 2`가 하나만 있는 경우
  - 한 열에 `1 2 1 2`가 있어 교착이 여러 개인 경우
  - `2`만 먼저 나와서 교착이 생기지 않는 경우
  - `1`만 있고 아래에 `2`가 없는 경우
