# SWEA 1230 리뷰

## 1) 문제 유형
- 구현
- 리스트
- 명령어 파싱

## 2) 문제 접근 방법
- 암호문을 순서가 있는 리스트로 관리한다.
- 명령은 `I`, `D`, `A` 세 종류가 있다.
- `I x y ...`: `x` 위치부터 `y`개의 숫자를 순서대로 삽입한다.
- `D x y`: `x` 위치부터 `y`개의 숫자를 삭제한다.
- `A y ...`: 맨 뒤에 `y`개의 숫자를 추가한다.
- 모든 명령 처리 후 앞에서 10개 암호문만 출력한다.

기존 `Solution.java`는 `D` 명령에서 삭제 인덱스가 밀리는 문제로 오답 가능성이 있었고, `delete()`를 같은 위치 `x`를 반복 삭제하도록 수정했습니다.

## 3) 정답 코드
```java
package problems.swea.p1230;

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
                String command = st.nextToken();

                if (command.equals("I")) {
                    int index = Integer.parseInt(st.nextToken());
                    int count = Integer.parseInt(st.nextToken());

                    // 삽입 위치가 매번 밀리므로 index + offset에 넣어 입력 순서를 유지한다.
                    for (int offset = 0; offset < count; offset++) {
                        passwords.add(index + offset, Integer.parseInt(st.nextToken()));
                    }
                } else if (command.equals("D")) {
                    int index = Integer.parseInt(st.nextToken());
                    int count = Integer.parseInt(st.nextToken());

                    // 하나를 지우면 뒤 원소가 index로 당겨지므로 같은 index를 반복 삭제한다.
                    for (int offset = 0; offset < count; offset++) {
                        passwords.remove(index);
                    }
                } else {
                    int count = Integer.parseInt(st.nextToken());

                    // A 명령은 순서 그대로 맨 뒤에 붙이면 된다.
                    for (int offset = 0; offset < count; offset++) {
                        passwords.add(Integer.parseInt(st.nextToken()));
                    }
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
- `I`와 `A` 명령 처리 방향은 맞습니다.
- 기존 `D` 명령은 `list.remove(x + i)`를 사용해서 삭제할 때마다 한 칸씩 건너뛰는 문제가 있었습니다.
- 삭제는 원소가 앞으로 당겨지므로 `list.remove(x)`를 `y`번 반복해야 합니다.
- 추천 코드는 별도 `insertList`, `addList`를 만들지 않고 토큰을 읽는 즉시 리스트에 반영합니다.
- 출력은 값마다 앞에 공백을 붙여 마지막 불필요한 공백을 줄였습니다.

## 5) 문제 해결 노하우
- 리스트 삭제는 삽입보다 인덱스 변화에 더 민감하다. 삭제 후 뒤 원소가 앞으로 당겨지는지 항상 확인해야 한다.
- 여러 개를 삽입할 때는 `index + offset`, 여러 개를 삭제할 때는 같은 `index` 반복이 기본 패턴이다.
- 명령어 파싱 문제는 명령별로 필요한 토큰 개수가 다르므로, 각 분기에서 정확히 필요한 만큼만 읽어야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - `I`로 중간에 여러 개 삽입하는 경우
  - `D`로 연속된 여러 개를 삭제하는 경우
  - `A`로 뒤에 여러 개 추가하는 경우
  - 세 명령이 한 줄에 섞여 있는 경우
