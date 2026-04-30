# SWEA 1209 리뷰

## 1) 문제 유형
- 구현
- 2차원 배열
- 행/열/대각선 합

## 2) 문제 접근 방법
- 100x100 배열에서 각 행의 합, 각 열의 합, 두 대각선의 합 중 최댓값을 구한다.
- 입력을 받을 때 행 합은 바로 계산할 수 있다.
- 열 합과 대각선 합은 배열을 채운 뒤 한 번 더 순회하며 계산한다.
- 테스트케이스는 항상 10개이고, 각 테스트케이스 앞에는 테스트케이스 번호가 주어진다.

현재 `Solution.java`는 행, 열, 두 대각선을 모두 확인하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int SIZE = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();

            int[][] arr = new int[SIZE][SIZE];
            int answer = 0;

            for (int row = 0; row < SIZE; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int rowSum = 0;

                for (int col = 0; col < SIZE; col++) {
                    arr[row][col] = Integer.parseInt(st.nextToken());
                    rowSum += arr[row][col];
                }

                answer = Math.max(answer, rowSum);
            }

            int diagonal = 0;
            int reverseDiagonal = 0;

            for (int col = 0; col < SIZE; col++) {
                int colSum = 0;

                for (int row = 0; row < SIZE; row++) {
                    colSum += arr[row][col];
                }

                answer = Math.max(answer, colSum);
                diagonal += arr[col][col];
                reverseDiagonal += arr[col][SIZE - 1 - col];
            }

            answer = Math.max(answer, Math.max(diagonal, reverseDiagonal));
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 행, 열, 두 대각선 합을 모두 확인하는 방식이 맞습니다.
- 추천 코드는 배열 인덱스를 `0~99`로 사용해 Java 배열 관례에 맞췄습니다.
- 테스트케이스 번호는 출력에 직접 쓰지 않으므로 `br.readLine()`으로 버리고, 반복문의 `tc`를 출력에 사용했습니다.
- `SIZE` 상수로 100을 분리해서 반복 범위의 의미가 더 잘 보이도록 했습니다.

## 5) 문제 해결 노하우
- 2차원 배열 합 문제는 행, 열, 대각선을 각각 어떤 반복문에서 처리할지 먼저 나누면 구현이 안정적이다.
- 대각선은 `arr[i][i]`, 역대각선은 `arr[i][SIZE - 1 - i]` 패턴을 기억하면 좋다.
- 검증은 아래 케이스를 보면 좋다:
  - 행 합이 최댓값인 경우
  - 열 합이 최댓값인 경우
  - 대각선 합이 최댓값인 경우
  - 역대각선 합이 최댓값인 경우
