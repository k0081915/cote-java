# BOJ 3273 리뷰

## 1) 문제 유형
- 투포인터
- 정렬
- 배열에서 두 수의 합

## 2) 문제 접근 방법
- 수열을 오름차순 정렬한 뒤, 양 끝에서 시작하는 투포인터를 사용한다.
- `left`와 `right`가 가리키는 두 수의 합을 `x`와 비교한다.
- 합이 `x`보다 작으면 더 큰 값을 만들어야 하므로 `left++`
- 합이 `x`보다 크면 더 작은 값을 만들어야 하므로 `right--`
- 합이 `x`와 같으면 정답 개수를 증가시키고, 같은 원소를 다시 쓰지 않기 위해 `left++`, `right--`를 함께 수행한다.

현재 `Main.java`는 위 투포인터 로직을 정확히 구현하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.boj.p3273;

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

        int target = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int left = 0;
        int right = n - 1;
        int count = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                count++;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(count);
    }
}
```

## 4) 내 코드와 다른 부분
- 전역 변수 `n`, `x` 없이 `main` 내부 지역 변수만으로 충분하다.
- 별도 `twoPointer()` 함수 없이도 로직 길이가 짧아서, 한 함수 안에 두면 오히려 읽기 쉬울 수 있다.
- 사용하지 않는 `StringBuilder`는 제거하는 편이 깔끔하다.
- 핵심 알고리즘은 같고, 정답 코드는 불필요한 상태를 줄여 더 간결하게 정리한 버전이다.

## 5) 문제 해결 노하우
- "정렬 후 양 끝에서 줄여나가는 방식이 가능한가"를 먼저 떠올리면 투포인터 문제를 빨리 분류할 수 있다.
- 두 수의 합 문제는 해시셋 방식과 투포인터 방식 둘 다 가능하지만, 정렬 기반 풀이는 포인터 이동 이유가 명확해서 디버깅이 쉽다.
- 검증은 아래 입력들을 먼저 돌려보면 좋다:
  - 대표 샘플
  - 정답 쌍이 하나도 없는 경우
  - 정답 쌍이 여러 개인 경우
  - 최소 길이 근처 입력
