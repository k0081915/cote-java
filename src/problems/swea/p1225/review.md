# SWEA 1225 리뷰

## 1) 문제 유형
- 구현
- 큐
- 시뮬레이션

## 2) 문제 접근 방법
- 8개의 숫자를 큐에 넣고, 앞에서 하나를 꺼내 감소시킨 뒤 뒤로 보낸다.
- 감소값은 `1, 2, 3, 4, 5`가 반복된다.
- 감소한 값이 `0` 이하가 되면 `0`으로 바꿔 큐 뒤에 넣고 해당 테스트케이스를 종료한다.
- 최종 큐 상태를 앞에서부터 출력하면 암호가 된다.

현재 `Solution.java`는 배열을 직접 밀어 큐처럼 사용하고 있으며, 문제 조건에 맞게 동작하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();

            Deque<Integer> queue = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            int decrease = 1;
            while (true) {
                int next = queue.poll() - decrease;

                if (next <= 0) {
                    queue.offer(0);
                    break;
                }

                queue.offer(next);
                decrease = decrease == 5 ? 1 : decrease + 1;
            }

            sb.append('#').append(tc);
            while (!queue.isEmpty()) {
                sb.append(' ').append(queue.poll());
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 동작은 같습니다. 앞 숫자를 감소시키고 뒤로 보내는 시뮬레이션이 맞습니다.
- 현재 코드는 `int[9]` 배열과 `arrange()`로 직접 한 칸씩 당겨 큐를 흉내냅니다.
- 추천 코드는 `Deque<Integer>`를 사용해서 `poll()`과 `offer()`로 문제 동작을 그대로 표현합니다.
- 테스트케이스 번호는 출력에 직접 쓰지 않으므로 `br.readLine()`으로 버리고, 반복문의 `tc`를 사용했습니다.
- 출력용 `StringBuilder answer`를 따로 만들지 않고 전체 출력 버퍼에 바로 붙였습니다.

## 5) 문제 해결 노하우
- "앞에서 빼서 뒤에 넣는다"는 문장이 보이면 큐를 먼저 떠올리면 좋다.
- 감소값처럼 `1~5`가 반복되는 값은 조건문이나 모듈러 연산으로 순환시킬 수 있다.
- 종료 조건이 발생한 값도 큐 뒤에 들어가야 하므로, `0`을 넣고 나서 반복을 끝내야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - 알려진 샘플 입력
  - 첫 사이클 안에 0 이하가 되는 경우
  - 감소값이 5에서 다시 1로 돌아가는 경우
