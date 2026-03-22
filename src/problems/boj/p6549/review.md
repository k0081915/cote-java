# BOJ 6549 리뷰

## 1) 문제 유형
- 스택
- 단조 증가 스택
- 히스토그램에서 가장 큰 직사각형

## 2) 문제 접근 방법
- 스택에는 "높이"가 아니라 "인덱스"를 저장한다.
- 스택이 항상 높이 기준 오름차순이 되도록 유지한다.
- 현재 높이가 스택 top보다 작아지는 순간, top 막대를 높이로 하는 최대 직사각형 넓이를 계산할 수 있다.
- 넓이 계산:
  - 높이 = `heights[stack.pop()]`
  - 왼쪽 경계 = `stack.peek()` 또는 스택이 비면 `-1`
  - 너비 = `i - leftBoundary - 1`
- 마지막까지 스택에 남은 막대를 처리하기 위해 `i == n`일 때 높이 `0`인 막대를 본 것처럼 한 번 더 순회한다.

현재 `Main.java`는 위 단조 스택 로직을 정확히 구현하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.boj.p6549;

// 백준 6549 - https://www.acmicpc.net/problem/6549
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String line = br.readLine();

            // 입력이 끝나면 종료
            if(line == null) {
                break;
            }

            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());

            // n이 0이면 전체 입력 종료
            if(n == 0) {
                break;
            }

            long[] heights = new long[n];
            int idx = 0;

            // 한 줄에 히스토그램 높이가 다 안들어올 수도 있으므로
            // n개를 모두 읽을 때까지 계속 토큰을 채움
            while(idx < n) {
                if(!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                heights[idx++] = Long.parseLong(st.nextToken());
            }

            sb.append(getMaxArea(heights)).append('\n');
        }

        System.out.println(sb);
    }

    static long getMaxArea(long[] heights) {
        int n = heights.length;
        // 스택에는 인덱스를 저장
        Deque<Integer> stack = new ArrayDeque<>();
        long maxArea = 0L;

        // 왼쪽부터 막대를 하나씩 확인
        for(int i = 0; i <= n; i++) {
            // i == n 일때 높이 0인 막대를 하나 본다고 가정해서
            // 스택에 남아있는 모든 막대를 한 번에 정리한다
            long currentHeight = (i == n) ? 0 : heights[i];

            // 현재 막대가 스택 top 막대보다 낮아지는 순간
            // top 막대를 높이로 하는 최대 직사각형 넓이를 계산할 수 있다
            while(!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                long height = heights[stack.pop()];

                // 스택이 비어있으면
                // 현재 막대 i 이전까지 전부 확장 가능 -> 너비 = i
                // 비어있지 않으면
                // stack.peek() 다음부터 i-1까지 확장 가능
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();

                int width = i - leftBoundary - 1;

                maxArea = Math.max(maxArea, height * width);
            }

            // 현재 막대 인덱스를 스택에 넣는다
            stack.push(i);
        }

        return maxArea;
    }
}
```

## 4) 내가 실수한 부분
- 넓이를 계산할 때 너비를 `i - stack.peek()`처럼 잡으면 오프바이원 오류가 나기 쉽다. 반드시 `i - leftBoundary - 1`로 계산해야 한다.
- 스택에 높이값만 저장하면 너비 계산이 어려워지므로 인덱스를 저장하는 편이 안전하다.
- 마지막 정리 루프를 따로 두지 않으면 오름차순으로 끝나는 입력에서 최댓값을 놓치기 쉽다.
- 이 문제는 정답 범위가 커서 `int`로 넓이를 계산하면 오버플로우 위험이 있다. `long` 사용이 필요하다.

## 5) 문제 해결 노하우
- "현재 값이 더 작아지는 순간 무엇을 확정할 수 있는가"를 기준으로 스택 문제를 보면 구현이 쉬워진다.
- 단조 스택 문제는 보통 "값"보다 "인덱스"를 저장해야 범위를 계산하기 편하다.
- 검증은 아래 입력들을 먼저 돌려보면 빠르다:
  - 대표 샘플: `7 2 1 4 5 1 3 3`
  - 전부 같은 높이: `4 1000 1000 1000 1000`
  - 엄격 증가 / 엄격 감소 케이스
  - 막대가 1개인 최소 입력
