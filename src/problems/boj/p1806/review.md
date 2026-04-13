# BOJ 1806 리뷰

## 1) 문제 유형
- 투포인터
- 슬라이딩 윈도우
- 부분합

## 2) 문제 접근 방법
- 수열의 모든 원소가 양수이므로, 구간 합이 부족하면 오른쪽 포인터를 늘리고 충분하면 왼쪽 포인터를 줄이는 슬라이딩 윈도우가 가능하다.
- `sum >= S`인 순간마다 현재 구간 길이로 최소 길이를 갱신한다.
- 이후 왼쪽 값을 빼고 `left++` 하여 더 짧은 정답이 가능한지 계속 확인한다.
- 끝까지 갱신이 한 번도 안 되면 조건을 만족하는 구간이 없으므로 `0`을 출력한다.

현재 `Main.java`는 위 슬라이딩 윈도우 로직을 정확히 구현하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.boj.p1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum >= s) {
                answer = Math.min(answer, right - left + 1);
                sum -= arr[left++];
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 아이디어는 같습니다. 양수 배열이라는 조건을 이용한 슬라이딩 윈도우 풀이가 맞습니다.
- 정답 코드는 `right`를 `for`문으로 전진시키고, 조건을 만족하는 동안 `while (sum >= s)`로 왼쪽을 줄여서 흐름을 더 표준적으로 정리했습니다.
- 현재 제출 코드처럼 `left`, `right`를 모두 직접 제어해도 정답은 맞지만, 추천 코드는 종료 조건과 예외 처리가 더 단순합니다.
- `minLength = 100001` 같은 문제 의존 초기값 대신 `Integer.MAX_VALUE`를 써서 재사용성을 높였습니다.

## 5) 문제 해결 노하우
- 모든 원소가 양수라는 조건이 보이면 "합이 커질수록 오른쪽 확장, 작아질수록 왼쪽 축소"가 가능하므로 슬라이딩 윈도우를 먼저 떠올리면 좋다.
- 이 문제는 "최소 길이"를 묻기 때문에, 조건을 만족하는 순간 바로 멈추지 말고 왼쪽을 최대한 줄여봐야 한다.
- 검증은 아래 케이스를 먼저 돌려보면 좋다:
  - 대표 샘플
  - 답이 없는 경우
  - 길이 1이 정답인 경우
  - 배열 전체를 써야 겨우 조건을 만족하는 경우
