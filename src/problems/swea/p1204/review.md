# SWEA 1204 리뷰

## 1) 문제 유형
- 구현
- 배열 카운팅
- 최빈값

## 2) 문제 접근 방법
- 점수 범위가 `0~100`으로 작기 때문에 점수별 빈도를 세는 배열을 사용하면 된다.
- 학생 1000명의 점수를 읽으면서 `scores[점수]++`로 빈도를 누적한다.
- 이후 `0~100` 구간을 순회하며 가장 많이 나온 점수를 찾는다.
- 동점일 때는 더 큰 점수를 출력해야 하므로, 같은 빈도여도 현재 점수로 갱신되도록 처리한다.

현재 `Solution.java`는 위 로직을 정확히 구현하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1204;

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
            br.readLine();

            int[] count = new int[101];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 1000; i++) {
                count[Integer.parseInt(st.nextToken())]++;
            }

            int answer = 0;
            int maxFreq = 0;
            for (int score = 0; score <= 100; score++) {
                if (count[score] >= maxFreq) {
                    maxFreq = count[score];
                    answer = score;
                }
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 점수별 카운팅 배열을 두고 최빈값을 찾는 방식이 맞습니다.
- 정답 코드는 `Scanner` 대신 `BufferedReader + StringTokenizer`를 써서 입출력을 더 가볍게 정리했습니다.
- 출력도 매 테스트마다 바로 찍지 않고 `StringBuilder`에 모아 한 번에 출력하도록 바꿨습니다.
- 동점 처리 기준은 동일합니다. `>=`를 사용해 같은 빈도면 더 큰 점수로 갱신합니다.

## 5) 문제 해결 노하우
- 값의 범위가 작으면 해시맵보다 고정 크기 카운팅 배열이 더 단순하고 빠르다.
- "최빈값 + 동점 시 큰 값" 조건은 순회 방향이나 비교 연산자 하나로 해결되는 경우가 많다.
- 검증은 아래 케이스를 먼저 보면 좋다:
  - 최빈값이 하나만 있는 경우
  - 동점이 여러 개라서 가장 큰 점수를 골라야 하는 경우
  - 특정 점수 하나가 매우 많이 반복되는 경우
