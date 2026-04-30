# SWEA 1208 리뷰

## 1) 문제 유형
- 구현
- 시뮬레이션
- 정렬

## 2) 문제 접근 방법
- 매 덤프마다 가장 높은 상자에서 1개를 빼고, 가장 낮은 상자에 1개를 더한다.
- 덤프 횟수만큼 반복한 뒤 최댓값과 최솟값의 차이를 출력한다.
- 상자 개수가 100개로 작기 때문에 매번 정렬해도 충분히 통과 가능하다.
- 더 효율적으로는 높이 범위가 작다는 점을 이용해 카운팅 배열로도 풀 수 있다.

현재 `Solution.java`는 매 덤프마다 정렬하는 방식으로 문제를 정확히 해결하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int dump = Integer.parseInt(br.readLine());
            int[] count = new int[101];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 100; i++) {
                count[Integer.parseInt(st.nextToken())]++;
            }

            int min = 1;
            int max = 100;

            while (dump-- > 0) {
                while (count[min] == 0) {
                    min++;
                }
                while (count[max] == 0) {
                    max--;
                }

                if (max - min <= 1) {
                    break;
                }

                count[min]--;
                count[min + 1]++;
                count[max]--;
                count[max - 1]++;
            }

            while (count[min] == 0) {
                min++;
            }
            while (count[max] == 0) {
                max--;
            }

            sb.append('#').append(tc).append(' ').append(max - min).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 현재 코드는 매 덤프마다 `Arrays.sort()`를 호출합니다. 상자 수가 작아서 정답에는 문제가 없습니다.
- 정답 코드는 높이별 개수를 저장하는 카운팅 배열을 사용해 최솟값/최댓값 이동만 처리합니다.
- 카운팅 방식은 정렬을 반복하지 않아 더 효율적이고, 높이 범위가 `1~100`처럼 작을 때 잘 맞습니다.
- 덤프 중 이미 평탄화가 거의 끝난 경우 `max - min <= 1`에서 조기 종료할 수 있습니다.

## 5) 문제 해결 노하우
- 입력 크기가 작으면 정렬 시뮬레이션도 충분하지만, 값의 범위가 작으면 카운팅 배열 풀이를 같이 떠올리면 좋다.
- "최댓값에서 빼고 최솟값에 더한다" 유형은 정렬, 우선순위 큐, 카운팅 배열 중 하나로 풀 수 있다.
- 검증은 아래 케이스를 보면 좋다:
  - 덤프 횟수가 0에 가까운 경우
  - 이미 평탄한 경우
  - 최댓값/최솟값이 여러 개 있는 경우
