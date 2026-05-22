# SWEA 1221 리뷰

## 1) 문제 유형
- 구현
- 문자열 처리
- 카운팅 정렬
- 정렬 기준 매핑

## 2) 문제 접근 방법
- GNS 숫자 문자열의 순서는 일반 사전순이 아니라 문제에서 정한 순서다.
- 순서는 `ZRO, ONE, TWO, THR, FOR, FIV, SIX, SVN, EGT, NIN`으로 고정되어 있다.
- 입력 문자열을 이 순서의 인덱스로 바꿔 생각하면 정렬 기준이 명확해진다.
- 숫자 종류가 10개뿐이므로 실제 정렬을 하지 않고 각 문자열의 개수만 세면 된다.
- 출력할 때는 고정 순서대로 `count[i]`번씩 문자열을 append하면 정렬된 결과가 된다.

현재 `Solution.java`는 문자열을 숫자로 매핑한 뒤 정렬하고 다시 문자열로 출력하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static final String[] NUMBERS = {
            "ZRO", "ONE", "TWO", "THR", "FOR",
            "FIV", "SIX", "SVN", "EGT", "NIN"
    };

    static final Map<String, Integer> INDEX = new HashMap<>();

    static {
        for (int i = 0; i < NUMBERS.length; i++) {
            INDEX.put(NUMBERS[i], i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String testCaseName = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            int[] count = new int[NUMBERS.length];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                // 문자열을 정렬하지 않고, 문제에서 정한 순서의 인덱스 빈도만 센다.
                count[INDEX.get(st.nextToken())]++;
            }

            sb.append(testCaseName).append('\n');
            for (int i = 0; i < NUMBERS.length; i++) {
                // 고정 순서대로 count만큼 출력하면 이미 정렬된 결과가 된다.
                for (int j = 0; j < count[i]; j++) {
                    sb.append(NUMBERS[i]).append(' ');
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 접근은 같습니다. 현재 코드도 GNS 문자열을 숫자 순서로 매핑한 뒤 정렬합니다.
- 현재 코드는 `int[] arr`에 변환 값을 저장하고 `Arrays.sort`로 정렬합니다.
- 추천 코드는 숫자 종류가 10개뿐이라는 점을 이용해 `count[10]`에 빈도만 저장합니다.
- 현재 코드는 `O(N log N)`, 추천 코드는 `O(N + 10)`입니다.
- 현재 코드의 `init()`은 테스트케이스마다 호출되지만, 추천 코드는 정적 초기화로 매핑을 한 번만 만듭니다.
- 현재 코드의 `answer` 변수는 선언만 되고 사용되지 않으므로 제거해도 됩니다.

## 5) 문제 해결 노하우
- 정렬 기준이 일반적인 숫자/문자 순서와 다르면 먼저 “기준 순서를 인덱스로 바꾸기”를 생각하면 좋다.
- 값의 종류가 작고 고정되어 있으면 전체 정렬보다 카운팅 정렬이 더 단순하고 빠르다.
- SWEA 1221의 첫 토큰은 실제 출력해야 하는 테스트케이스 이름이므로, `#1`처럼 직접 만들기보다 입력받은 값을 그대로 써도 안전하다.
- 검증은 아래 케이스를 보면 좋다:
  - 입력이 역순으로 주어진 경우
  - 같은 GNS 숫자가 여러 번 반복되는 경우
  - 특정 숫자가 하나도 나오지 않는 경우
