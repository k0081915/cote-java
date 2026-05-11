# SWEA 1216 리뷰

## 1) 문제 유형
- 구현
- 문자열
- 회문 검사
- 2차원 배열

## 2) 문제 접근 방법
- 100x100 글자판에서 가로 또는 세로 방향으로 만들 수 있는 가장 긴 회문 길이를 찾는다.
- 가장 긴 길이를 찾아야 하므로 길이 `100`부터 `1`까지 내려오며 확인하면 된다.
- 특정 길이의 회문을 하나라도 찾으면 그 길이가 정답이므로 바로 종료할 수 있다.
- 가로는 오른쪽 방향, 세로는 아래 방향으로 같은 회문 검사 로직을 적용할 수 있다.

현재 `Solution.java`는 길이를 큰 값부터 줄이며 가로와 세로 회문을 모두 확인하므로 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static final int SIZE = 100;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int testCase = Integer.parseInt(br.readLine());
            board = new char[SIZE][SIZE];

            for (int row = 0; row < SIZE; row++) {
                board[row] = br.readLine().toCharArray();
            }

            int answer = 1;
            for (int length = SIZE; length >= 1; length--) {
                if (existsPalindrome(length)) {
                    answer = length;
                    break;
                }
            }

            sb.append('#').append(testCase).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static boolean existsPalindrome(int length) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col <= SIZE - length; col++) {
                if (isPalindrome(row, col, 0, 1, length)) {
                    return true;
                }
            }
        }

        for (int row = 0; row <= SIZE - length; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isPalindrome(row, col, 1, 0, length)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isPalindrome(int row, int col, int dr, int dc, int length) {
        for (int offset = 0; offset < length / 2; offset++) {
            int leftRow = row + dr * offset;
            int leftCol = col + dc * offset;
            int rightRow = row + dr * (length - 1 - offset);
            int rightCol = col + dc * (length - 1 - offset);

            if (board[leftRow][leftCol] != board[rightRow][rightCol]) {
                return false;
            }
        }

        return true;
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 가장 긴 길이부터 확인하고, 찾는 순간 종료하는 방식이 맞습니다.
- 현재 코드는 가로 검사와 세로 검사를 각각 `isRowPalindrome`, `isColPalindrome`으로 나눴습니다.
- 추천 코드는 방향값 `dr`, `dc`를 사용해 하나의 `isPalindrome()` 함수로 가로/세로를 모두 검사합니다.
- 현재 코드의 `answer = 0`도 문제상 결국 1 이상 회문이 존재하므로 동작에는 문제 없습니다. 추천 코드에서는 의미상 `1`로 시작했습니다.
- 주석의 작은 오타 `ㅊ찾기` 정도만 정리하면 현재 코드도 충분히 깔끔합니다.

## 5) 문제 해결 노하우
- "가장 긴" 값을 찾는 문제는 큰 길이부터 내려오며 확인하면 조기 종료가 가능하다.
- 가로/세로처럼 방향만 다른 탐색은 `dr`, `dc`로 일반화하면 중복을 줄일 수 있다.
- 회문 검사는 실제 문자열을 만들지 않고 양끝 좌표만 비교해도 된다.
- 검증은 아래 케이스를 보면 좋다:
  - 모든 문자가 같아서 정답이 100인 경우
  - 가로에만 긴 회문이 있는 경우
  - 세로에만 긴 회문이 있는 경우
  - 길이 1만 가능한 경우
