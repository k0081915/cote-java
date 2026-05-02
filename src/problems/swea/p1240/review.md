# SWEA 1240 리뷰

## 1) 문제 유형
- 구현
- 문자열 처리
- 암호코드 디코딩

## 2) 문제 접근 방법
- 암호코드는 항상 8자리이고, 각 숫자는 7비트 패턴으로 표현되므로 총 56비트다.
- 입력 행 중 `1`이 포함된 행을 찾고, 오른쪽에서 가장 마지막 `1`의 위치를 기준으로 56비트를 잘라낸다.
- 잘라낸 문자열을 7글자씩 나누어 숫자로 디코딩한다.
- 검증식 `(홀수 자리 합 * 3 + 짝수 자리 합) % 10 == 0`을 만족하면 8개 숫자의 합을 출력하고, 아니면 `0`을 출력한다.

현재 `Solution.java`는 오른쪽 끝의 `1`을 기준으로 암호를 추출하고 검증식도 맞게 적용하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static final Map<String, Integer> CODE = new HashMap<>();

    static {
        CODE.put("0001101", 0);
        CODE.put("0011001", 1);
        CODE.put("0010011", 2);
        CODE.put("0111101", 3);
        CODE.put("0100011", 4);
        CODE.put("0110001", 5);
        CODE.put("0101111", 6);
        CODE.put("0111011", 7);
        CODE.put("0110111", 8);
        CODE.put("0001011", 9);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            String password = null;
            for (int row = 0; row < n; row++) {
                String line = br.readLine();

                if (password != null) {
                    continue;
                }

                for (int col = m - 1; col >= 0; col--) {
                    if (line.charAt(col) == '1') {
                        password = line.substring(col - 55, col + 1);
                        break;
                    }
                }
            }

            int[] numbers = new int[8];
            int numberSum = 0;
            int checkSum = 0;

            for (int i = 0; i < 8; i++) {
                String pattern = password.substring(i * 7, i * 7 + 7);
                numbers[i] = CODE.get(pattern);
                numberSum += numbers[i];

                if (i % 2 == 0) {
                    checkSum += numbers[i] * 3;
                } else {
                    checkSum += numbers[i];
                }
            }

            int answer = checkSum % 10 == 0 ? numberSum : 0;
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 마지막 `1`을 기준으로 56비트를 추출하는 방식이 맞습니다.
- 현재 코드는 `List.indexOf()`로 패턴을 숫자로 바꿉니다. 추천 코드는 `Map<String, Integer>`를 사용해 "패턴 -> 숫자" 관계를 더 직접적으로 표현했습니다.
- 암호를 한 번 찾은 뒤에도 남은 입력 행은 읽어야 하므로, 추천 코드에서는 `password != null`이면 탐색만 건너뛰고 입력 소비는 계속합니다.
- 숫자 합을 `Arrays.stream(numbers).sum()`으로 다시 계산하지 않고, 디코딩 중에 `numberSum`을 같이 누적합니다.
- 현재 코드도 통과 가능한 형태이고, 추천 코드는 디코딩 구조를 조금 더 명확히 나눈 버전입니다.

## 5) 문제 해결 노하우
- 고정 길이 패턴 디코딩 문제는 "추출 위치 찾기 -> 일정 길이로 자르기 -> 패턴 매핑 -> 검증" 순서로 쪼개면 안정적이다.
- 암호코드는 뒤쪽에 0이 붙어 있으므로 오른쪽에서 처음 만나는 `1`을 찾으면 끝 위치를 쉽게 알 수 있다.
- 검증식에서 문제의 "홀수 자리"는 사람 기준 1번째, 3번째, 5번째, 7번째 자리이므로 배열 인덱스로는 `0, 2, 4, 6`이다.
- 검증은 아래 케이스를 보면 좋다:
  - 유효한 암호라서 숫자 합을 출력하는 경우
  - 검증식이 틀려서 `0`을 출력하는 경우
  - 암호 앞뒤에 0이 많이 붙어 있는 경우
