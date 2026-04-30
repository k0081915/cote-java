# SWEA 2805 리뷰

## 1) 문제 유형
- 구현
- 2차원 배열
- 마름모 영역 탐색

## 2) 문제 접근 방법
- 농장의 크기 `N`은 홀수이고, 수확 영역은 가운데를 중심으로 한 마름모 모양이다.
- 가운데 인덱스를 `mid = N / 2`로 둔다.
- 각 칸 `(row, col)`에 대해 `|row - mid| + |col - mid| <= mid`이면 수확 영역에 포함된다.
- 또는 각 행마다 수확 가능한 시작 열과 끝 열을 계산해서 더할 수도 있다.

현재 `Solution.java`는 열 기준으로 마름모 영역을 순회하고 있으며, 조건상 같은 영역을 합산하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int mid = n / 2;
            int answer = 0;

            for (int row = 0; row < n; row++) {
                String line = br.readLine();
                int distance = Math.abs(row - mid);
                int start = distance;
                int end = n - 1 - distance;

                for (int col = start; col <= end; col++) {
                    answer += line.charAt(col) - '0';
                }
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 아이디어는 같습니다. 가운데를 기준으로 마름모 영역만 합산하는 방식이 맞습니다.
- 현재 코드는 1-index 배열을 만들고 열 기준으로 순회합니다. 추천 코드는 입력을 받는 즉시 행 기준으로 필요한 열만 더합니다.
- 추천 코드는 별도 `int[][]` 배열을 저장하지 않아도 되므로 메모리와 코드가 조금 더 가볍습니다.
- `Integer.parseInt(String.valueOf(line.charAt(j)))` 대신 `line.charAt(j) - '0'`을 쓰면 한 자리 숫자를 더 간단히 정수로 바꿀 수 있습니다.
- 현재 코드의 `import java.util.Arrays;`는 사용하지 않으므로 제거해도 됩니다.

## 5) 문제 해결 노하우
- 마름모 영역은 보통 중심으로부터의 거리 조건 `|row - mid| + |col - mid| <= mid`로 생각하면 안정적이다.
- 행별로 보면 가운데 행까지는 범위가 넓어지고, 이후에는 다시 좁아지는 패턴이다.
- 입력값이 한 자리 숫자로 붙어 들어오면 `charAt()`으로 읽고 `'0'`을 빼는 방식이 깔끔하다.
- 검증은 아래 케이스를 보면 좋다:
  - `N = 1`인 최소 케이스
  - 가운데 행의 값만 큰 경우
  - 모서리에 큰 값이 있지만 수확 영역 밖인 경우
