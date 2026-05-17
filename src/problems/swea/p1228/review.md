# SWEA 1228 리뷰

## 1) 문제 유형
- 구현
- 리스트
- 명령어 파싱

## 2) 문제 접근 방법
- 암호문을 순서가 있는 리스트로 관리한다.
- 명령은 `I x y s1 s2 ... sy` 형태로 들어온다.
- `x` 위치 바로 앞에 `y`개의 숫자를 순서대로 삽입해야 한다.
- `ArrayList.add(index, value)`를 사용하면 해당 위치에 값을 끼워 넣고 뒤 원소들이 자동으로 밀린다.
- 모든 명령을 처리한 뒤 앞에서 10개 암호문만 출력한다.

현재 `Solution.java`는 명령 파싱과 삽입 순서를 올바르게 처리하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            List<Integer> passwords = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                passwords.add(Integer.parseInt(st.nextToken()));
            }

            int commandCount = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < commandCount; i++) {
                // SWEA 1228의 명령은 삽입 명령 I만 주어진다.
                st.nextToken();

                int index = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                // index에 계속 넣으면 순서가 뒤집히므로 index + offset에 차례로 삽입한다.
                for (int offset = 0; offset < count; offset++) {
                    int value = Integer.parseInt(st.nextToken());
                    passwords.add(index + offset, value);
                }
            }

            sb.append('#').append(tc);
            for (int i = 0; i < 10; i++) {
                sb.append(' ').append(passwords.get(i));
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. `list.add(x + j, value)`로 삽입 순서를 유지하는 방식이 맞습니다.
- 추천 코드는 변수명을 `passwords`, `commandCount`, `index`, `count`처럼 역할이 보이게 정리했습니다.
- 출력은 `"#" + tc + " "`를 먼저 붙인 뒤 값마다 앞에 공백을 붙여 마지막 공백을 만들지 않도록 했습니다.
- 명령어 `I`는 실제 분기가 필요 없지만, 입력 소비를 위해 읽어야 한다는 점을 주석으로 명확히 했습니다.

## 5) 문제 해결 노하우
- 삽입 명령에서 여러 값을 같은 위치에 넣을 때는 삽입 순서가 뒤집히지 않도록 주의해야 한다.
- `add(index, value)`를 반복할 때 `index + offset`에 넣으면 입력 순서 그대로 유지된다.
- 명령어 형식이 고정되어 있어도 명령 문자 자체는 토큰으로 들어오므로 반드시 소비해야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - 맨 앞에 삽입하는 경우
  - 중간에 여러 개를 삽입하는 경우
  - 삽입한 값들이 출력 앞 10개에 영향을 주는 경우
