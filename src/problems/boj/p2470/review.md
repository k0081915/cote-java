# BOJ 2470 리뷰

## 1) 문제 유형
- 투포인터
- 정렬
- 두 수의 합 최적화

## 2) 문제 접근 방법
- 용액들을 오름차순 정렬한 뒤, 양 끝에서 시작하는 투포인터를 사용한다.
- 현재 합이 `0`보다 작으면 더 큰 값이 필요하므로 `left++`
- 현재 합이 `0`보다 크면 더 작은 값이 필요하므로 `right--`
- 각 단계에서 `|sum|`이 지금까지의 최솟값보다 작으면 정답 쌍을 갱신한다.
- 합이 정확히 `0`이면 더 좋은 답이 없으므로 바로 종료할 수 있다.

현재 `Main.java`는 위 투포인터 로직을 정확히 구현하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.boj.p2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        int answerLeft = arr[left];
        int answerRight = arr[right];
        long bestAbs = Long.MAX_VALUE;

        while (left < right) {
            long sum = (long) arr[left] + arr[right];
            long absSum = Math.abs(sum);

            if (absSum < bestAbs) {
                bestAbs = absSum;
                answerLeft = arr[left];
                answerRight = arr[right];
            }

            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                break;
            }
        }

        System.out.println(answerLeft + " " + answerRight);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 알고리즘은 같습니다. 정렬 후 투포인터로 양 끝에서 좁혀 오는 방식이 맞습니다.
- 정답 코드는 `sum`, `bestAbs`를 `long`으로 두어 자료형 안정성을 조금 더 높였습니다.
- 변수명도 `answerLeft`, `answerRight`, `bestAbs`처럼 역할이 바로 보이게 정리했습니다.
- 현재 제출 코드도 충분히 통과 가능한 형태이고, 정답 코드는 같은 아이디어를 조금 더 안전하고 읽기 쉽게 다듬은 버전입니다.

## 5) 문제 해결 노하우
- 정렬된 상태에서 "합이 너무 작으면 왼쪽 증가, 너무 크면 오른쪽 감소"라는 기준이 보이면 투포인터 문제일 가능성이 높다.
- 두 수의 합이 `0`에 가장 가까운 문제는 브루트포스가 아니라 정렬 + 투포인터로 `O(n log n)`에 해결할 수 있다.
- 검증은 아래 케이스를 먼저 돌려보면 좋다:
  - 음수/양수가 섞인 일반 케이스
  - 모두 음수인 경우
  - 모두 양수인 경우
  - 합이 정확히 `0`이 되는 쌍이 있는 경우
