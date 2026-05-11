# SWEA 1217 리뷰

## 1) 문제 유형
- 구현
- 재귀
- 거듭제곱

## 2) 문제 접근 방법
- `N`을 `M`번 곱하면 `N^M`을 구할 수 있다.
- 재귀 함수는 지수를 하나씩 줄이면서 `base * power(base, exponent - 1)`을 반환한다.
- 지수가 `0`이면 더 이상 곱할 값이 없으므로 `1`을 반환한다.
- SWEA 1217은 테스트케이스가 10개이고, 각 테스트케이스마다 번호와 `N M`이 주어진다.

현재 `Solution.java`는 입력 조건상 `M >= 1`인 상황에서 재귀로 거듭제곱을 계산하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int testCase = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int base = Integer.parseInt(st.nextToken());
            int exponent = Integer.parseInt(st.nextToken());

            sb.append('#').append(testCase).append(' ')
                    .append(power(base, exponent))
                    .append('\n');
        }

        System.out.print(sb);
    }

    static int power(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        return base * power(base, exponent - 1);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 아이디어는 같습니다. 재귀로 `N`을 반복해서 곱하는 방식이 맞습니다.
- 현재 코드는 `recur(1, n)`으로 시작해서 `cnt == m`일 때 누적 곱을 반환합니다.
- 추천 코드는 `power(base, exponent)` 형태로 지수를 줄이며 계산해서 거듭제곱 정의와 더 직접적으로 맞습니다.
- 추천 코드는 `exponent == 0`도 자연스럽게 처리합니다. 현재 코드는 SWEA 입력 조건에서는 괜찮지만, `m = 0`이 들어오면 종료되지 않습니다.
- `Scanner` 대신 `BufferedReader + StringTokenizer`를 사용해 기존 풀이 스타일과 입출력 방식을 맞췄습니다.

## 5) 문제 해결 노하우
- 재귀 문제는 먼저 "무엇을 줄일 것인가"를 정하면 쉽다. 이 문제에서는 지수 `M`을 하나씩 줄이면 된다.
- 거듭제곱의 기본 종료 조건은 보통 `x^0 = 1`이다.
- 누적값을 들고 가는 재귀도 가능하지만, 수학 정의 그대로 `base * power(base, exponent - 1)`로 쓰면 의미가 더 잘 보인다.
- 검증은 아래 케이스를 보면 좋다:
  - `2 3 -> 8`
  - `5 1 -> 5`
  - 일반화 기준으로 `7 0 -> 1`
