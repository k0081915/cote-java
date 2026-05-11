# SWEA 1213 리뷰

## 1) 문제 유형
- 구현
- 문자열
- 부분 문자열 검색

## 2) 문제 접근 방법
- 검색할 단어와 전체 문장을 입력받는다.
- 전체 문장의 가능한 모든 시작 위치를 순회한다.
- 해당 위치부터 검색 단어와 같은 문자열이 시작되면 정답을 1 증가시킨다.
- 겹치는 경우도 세야 하므로, 찾은 뒤에도 인덱스를 검색 단어 길이만큼 건너뛰면 안 된다.

현재 `Solution.java`는 모든 시작 위치에서 부분 문자열을 비교하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int testCase = Integer.parseInt(br.readLine());
            String pattern = br.readLine();
            String text = br.readLine();

            int answer = 0;
            for (int start = 0; start <= text.length() - pattern.length(); start++) {
                if (text.startsWith(pattern, start)) {
                    answer++;
                }
            }

            sb.append('#').append(testCase).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 가능한 모든 시작 위치에서 검색 단어와 일치하는지 확인하는 방식이 맞습니다.
- 현재 코드는 `substring()`으로 새 문자열을 만들어 `equals()`로 비교합니다.
- 추천 코드는 `text.startsWith(pattern, start)`를 사용해 중간 문자열 생성을 줄였습니다.
- 현재 출력은 반복문의 `tc`를 사용합니다. SWEA 1213은 입력 테스트케이스 번호가 보통 `1~10`이라 문제는 없지만, 추천 코드는 입력으로 받은 `testCase`를 그대로 출력합니다.
- 겹치는 문자열도 세기 위해 `start++`로 한 칸씩 이동하는 점은 동일합니다.

## 5) 문제 해결 노하우
- 문자열 검색에서 겹치는 경우를 세야 하면, 매칭 후에도 인덱스를 1만 증가시켜야 한다.
- Java에서는 특정 위치에서 시작하는지 확인할 때 `startsWith(pattern, index)`를 사용할 수 있다.
- 테스트케이스 번호가 입력으로 들어오는 SWEA 문제는 출력에도 그 번호를 쓰면 더 안전하다.
- 검증은 아래 케이스를 보면 좋다:
  - `ABA` in `ABABA`처럼 겹치는 경우
  - 검색 단어가 한 번도 없는 경우
  - 검색 단어와 전체 문자열 길이가 같은 경우
  - 검색 단어가 한 글자인 경우
