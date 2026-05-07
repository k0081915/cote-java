# SWEA 1289 리뷰

## 1) 문제 유형
- 구현
- 문자열
- 상태 변화 카운팅

## 2) 문제 접근 방법
- 초기 메모리 상태는 모든 비트가 `0`이다.
- 왼쪽부터 목표 문자열을 확인하면서 현재 상태와 목표 비트가 다르면 한 번 수정해야 한다.
- 한 위치에서 수정하면 그 위치부터 오른쪽 끝까지 같은 비트로 바뀐다.
- 따라서 실제 배열을 바꿀 필요 없이, 현재 구간의 비트 상태만 `current`로 관리하면 된다.
- 목표 비트가 `current`와 달라지는 지점의 개수가 곧 최소 수정 횟수다.

현재 `Solution.java`는 왼쪽부터 비트 상태가 달라지는 지점만 세고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            String target = br.readLine();
            char current = '0';
            int answer = 0;

            for (int i = 0; i < target.length(); i++) {
                if (target.charAt(i) != current) {
                    current = target.charAt(i);
                    answer++;
                }
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 상태와 목표 비트가 달라지는 지점만 세면 됩니다.
- 현재 코드는 상태가 다를 때 `current = current == '0' ? '1' : '0'`로 토글합니다.
- 추천 코드는 `current = target.charAt(i)`로 목표 비트에 직접 맞춥니다.
- 이 문제에서는 목표 비트가 현재 상태와 다를 때 반드시 반대 비트이므로 두 방식 모두 맞습니다.
- 추천 코드는 "현재 상태를 목표와 같게 맞춘다"는 의미가 조금 더 직접적으로 드러납니다.

## 5) 문제 해결 노하우
- 구간 전체가 한 번에 바뀌는 문제는 실제로 모든 칸을 바꾸지 않아도 되는 경우가 많다.
- 왼쪽부터 확정되는 구조라면 현재 상태만 들고 가면서 변화 지점만 세면 된다.
- 이 문제의 답은 `0`에서 시작해 목표 문자열을 읽을 때 비트가 바뀌는 횟수와 같다.
- 검증은 아래 케이스를 보면 좋다:
  - 이미 전부 `0`인 경우
  - 처음부터 `1`로 시작하는 경우
  - `1010`처럼 자주 바뀌는 경우
  - `0011`처럼 중간부터 한 번만 바뀌는 경우
