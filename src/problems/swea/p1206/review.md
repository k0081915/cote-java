# SWEA 1206 리뷰

## 1) 문제 유형
- 구현
- 배열 탐색
- 시뮬레이션

## 2) 문제 접근 방법
- 각 건물의 조망권은 양옆 2칸의 건물 중 가장 높은 건물보다 얼마나 더 높은지로 결정된다.
- 인덱스 `2`부터 `N - 3`까지만 확인하면 된다.
- 현재 건물 기준 왼쪽 2개, 오른쪽 2개 중 최댓값을 구한다.
- 현재 건물이 그 최댓값보다 높으면 차이만큼 조망권 세대 수에 더한다.

현재 `Solution.java`는 SWEA 1206의 기본 입력 조건에서는 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] buildings = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;
            for (int i = 2; i < n - 2; i++) {
                int nearbyMax = Math.max(
                        Math.max(buildings[i - 2], buildings[i - 1]),
                        Math.max(buildings[i + 1], buildings[i + 2])
                );

                if (buildings[i] > nearbyMax) {
                    answer += buildings[i] - nearbyMax;
                }
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 아이디어는 같습니다. 현재 건물과 좌우 2칸을 비교하는 방식이 맞습니다.
- 정답 코드는 `i == 2`, `i == N - 3` 특수 처리를 제거하고 모든 위치를 같은 로직으로 처리합니다.
- 좌우 2칸의 최댓값을 먼저 구하면 `diff1~diff4`를 따로 관리하지 않아도 됩니다.
- 문제 조건상 양끝 2칸에는 건물이 없지만, 그래도 일반식으로 쓰는 편이 더 단순하고 실수 여지가 적습니다.

## 5) 문제 해결 노하우
- 주변 몇 칸만 보는 구현 문제는 "비교 대상의 최댓값/최솟값을 먼저 만들 수 있는가"를 생각하면 코드가 짧아진다.
- 특수 케이스를 만들기 전에 반복 범위를 조정해서 같은 로직으로 처리할 수 있는지 먼저 확인하면 좋다.
- 검증은 아래 케이스를 보면 좋다:
  - 가운데 건물 하나만 조망권이 있는 경우
  - 모든 건물이 낮거나 같은 경우
  - 여러 건물이 각각 조망권을 갖는 경우
