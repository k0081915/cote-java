# SWEA 1234 리뷰

## 1) 문제 유형
- 구현
- 문자열 처리
- 스택

## 2) 문제 접근 방법
- 인접한 같은 숫자 2개가 생기면 바로 제거해야 한다.
- 제거 후에는 양옆에 있던 문자가 새로 인접해질 수 있으므로, 앞에서부터 단순히 한 번만 비교하면 부족하다.
- 이런 “마지막 문자와 현재 문자 비교 후 제거/추가” 구조는 스택으로 처리하면 자연스럽다.
- 현재 문자가 스택의 top과 같으면 top을 제거하고, 다르면 현재 문자를 스택에 넣는다.
- 모든 문자를 처리한 뒤 스택에 남은 문자들이 최종 암호문이다.

현재 `Solution.java`는 `ArrayList`에서 같은 인접 문자를 제거한 뒤 인덱스를 한 칸 되돌리는 방식으로 반복 제거를 처리하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            String password = st.nextToken();

            char[] stack = new char[length];
            int top = 0;

            for (int i = 0; i < length; i++) {
                char current = password.charAt(i);

                // top 바로 아래 문자가 현재 문자와 같으면 인접한 같은 쌍이므로 제거한다.
                if (top > 0 && stack[top - 1] == current) {
                    top--;
                    continue;
                }

                // 제거되지 않은 문자는 이후 문자와 비교할 수 있도록 스택에 남긴다.
                stack[top++] = current;
            }

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < top; i++) {
                sb.append(stack[i]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 아이디어는 같습니다. 현재 코드도 인접한 같은 문자를 제거하고, 제거 후 인덱스를 되돌려 새로 생긴 인접 쌍을 다시 확인합니다.
- 현재 코드는 `ArrayList<Character>`에 모든 문자를 넣고 `remove`로 삭제합니다.
- 추천 코드는 `char[]`를 스택처럼 사용해 문자를 읽는 즉시 제거 여부를 결정합니다.
- `ArrayList.remove(idx)`는 뒤 원소들을 앞으로 당겨야 해서 반복 삭제가 많으면 비용이 커집니다.
- 추천 코드는 각 문자를 한 번씩만 넣거나 빼므로 시간 복잡도는 `O(N)`입니다.

## 5) 문제 해결 노하우
- “인접한 두 개를 제거하고 나면 새로운 인접 관계가 생긴다”는 문제는 스택을 먼저 의심해도 좋다.
- 스택 top과 현재 문자만 비교하면, 앞쪽 전체를 다시 탐색하지 않아도 연쇄 제거를 처리할 수 있다.
- `StringBuilder`를 스택처럼 쓰는 방법도 가능하지만, 길이가 정해진 숫자 문자열이면 `char[]`와 `top`이 가장 단순하고 빠르다.
- 검증은 아래 케이스를 보면 좋다:
  - `1223` -> `13`
  - `123321` -> 빈 문자열
  - `1111` -> 빈 문자열
  - `123456` -> 그대로 유지
