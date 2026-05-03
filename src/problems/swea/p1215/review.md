# SWEA 1215 리뷰

## 1) 문제 유형
- 구현
- 문자열
- 회문 검사

## 2) 문제 접근 방법
- 8x8 글자판에서 길이 `L`인 회문을 가로와 세로 방향으로 모두 찾는다.
- 시작 위치는 `0`부터 `8 - L`까지 가능하다.
- 가로는 `(row, start)`에서 오른쪽으로 `L`칸을 검사한다.
- 세로는 `(start, col)`에서 아래쪽으로 `L`칸을 검사한다.
- 양끝 문자를 안쪽으로 좁혀가며 다르면 회문이 아니다.

현재 `Solution.java`는 가로 회문과 세로 회문을 모두 확인하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static final int SIZE = 8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int length = Integer.parseInt(br.readLine());
            char[][] board = new char[SIZE][SIZE];

            for (int row = 0; row < SIZE; row++) {
                board[row] = br.readLine().toCharArray();
            }

            int answer = 0;
            for (int row = 0; row < SIZE; row++) {
                for (int start = 0; start <= SIZE - length; start++) {
                    if (isRowPalindrome(board, row, start, length)) {
                        answer++;
                    }
                }
            }

            for (int col = 0; col < SIZE; col++) {
                for (int start = 0; start <= SIZE - length; start++) {
                    if (isColumnPalindrome(board, start, col, length)) {
                        answer++;
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static boolean isRowPalindrome(char[][] board, int row, int start, int length) {
        for (int offset = 0; offset < length / 2; offset++) {
            if (board[row][start + offset] != board[row][start + length - 1 - offset]) {
                return false;
            }
        }
        return true;
    }

    static boolean isColumnPalindrome(char[][] board, int start, int col, int length) {
        for (int offset = 0; offset < length / 2; offset++) {
            if (board[start + offset][col] != board[start + length - 1 - offset][col]) {
                return false;
            }
        }
        return true;
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 가로와 세로 방향의 길이 `L` 회문을 모두 세는 방식이 맞습니다.
- 현재 코드는 가로는 `substring()`으로 검사하고, 세로는 문자열 리스트를 새로 만든 뒤 다시 `substring()`으로 검사합니다.
- 추천 코드는 `char[][]`에서 바로 회문을 검사해서 중간 문자열과 `List<String>` 생성을 줄였습니다.
- `SIZE` 상수로 8을 분리해 반복 범위의 의미가 더 잘 보이도록 했습니다.
- 현재 코드도 충분히 통과 가능한 형태이고, 추천 코드는 메모리와 흐름을 조금 더 단순화한 버전입니다.

## 5) 문제 해결 노하우
- 회문 검사는 문자열을 뒤집지 않아도 양끝 포인터를 좁혀가면 바로 확인할 수 있다.
- 2차원 배열에서 가로/세로를 둘 다 봐야 하는 문제는 같은 시작 범위를 두 방향에 각각 적용하면 된다.
- 검증은 아래 케이스를 보면 좋다:
  - 모든 문자가 같아서 가능한 모든 구간이 회문인 경우
  - 가로 회문만 있는 경우
  - 세로 회문만 있는 경우
  - 회문 길이가 1 또는 8에 가까운 경우
