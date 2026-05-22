# SWEA 3431 리뷰

## 1) 문제 유형
- 구현
- 조건 분기
- 수학

## 2) 문제 접근 방법
- 운동 권장 시간은 `L` 이상 `U` 이하이다.
- 현재 운동 시간 `X`가 `L`보다 작으면 `L - X`만큼 더 운동해야 한다.
- `X`가 `L` 이상 `U` 이하이면 이미 조건을 만족하므로 `0`을 출력한다.
- `X`가 `U`보다 크면 너무 많이 운동한 것이므로 `-1`을 출력한다.
- 세 구간이 서로 겹치지 않도록 조건을 나누면 된다.

현재 `Solution.java`는 `X > U`, `X < L`, 그 외 범위를 정확히 나누고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p3431;

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
            int lower = Integer.parseInt(st.nextToken());
            int upper = Integer.parseInt(st.nextToken());
            int current = Integer.parseInt(st.nextToken());

            int answer;
            if (current < lower) {
                // 최소 권장 시간보다 부족한 만큼만 더 운동하면 된다.
                answer = lower - current;
            } else if (current <= upper) {
                // 권장 범위 안에 있으면 추가 운동이 필요 없다.
                answer = 0;
            } else {
                // 최대 권장 시간을 초과하면 문제 조건상 -1을 출력한다.
                answer = -1;
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 코드도 `U < X`이면 `-1`, `X < L`이면 `L - X`, 나머지는 `0`으로 처리합니다.
- 추천 코드는 조건을 `current < lower`, `current <= upper`, 초과 순서로 배치해 세 구간을 문제 설명 순서대로 읽히게 했습니다.
- 현재 코드의 변수명 `L`, `U`, `X`도 문제와 같아 충분히 명확합니다.
- 현재 풀이에서 정답성 문제는 없습니다.

## 5) 문제 해결 노하우
- 구간 조건 문제는 먼저 가능한 상태를 나누는 것이 중요하다.
- 이 문제는 `부족`, `적정`, `초과` 세 상태뿐이다.
- 경계값인 `X == L`, `X == U`는 적정 범위이므로 `0`이 나와야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - `X < L`인 경우
  - `X == L`인 경우
  - `L < X < U`인 경우
  - `X == U`인 경우
  - `X > U`인 경우
