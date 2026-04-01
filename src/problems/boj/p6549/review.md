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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;

class Main {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (true) {
            int n = fs.nextInt();
            if (n == 0) {
                break;
            }

            long[] heights = new long[n];
            for (int i = 0; i < n; i++) {
                heights[i] = fs.nextLong();
            }

            sb.append(getMaxArea(heights, n)).append('\n');
        }

        System.out.println(sb);
    }

    static long getMaxArea(long[] heights, int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        long maxArea = 0L;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                long height = heights[stack.pop()];
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                long width = i - leftBoundary - 1L;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            long height = heights[stack.pop()];
            int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
            long width = n - leftBoundary - 1L;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) {
                    return -1;
                }
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ' && c != -1);

            long value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }
            return value;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }
}
```

## 4) 내 코드와 다른 부분
- 입력이 큰 편이라 `BufferedReader + StringTokenizer`보다 바이트 기반 스캐너가 더 안정적으로 빠르다.
- 센티널 인덱스를 스택에 넣는 방식 대신, 메인 루프와 마무리 정리 루프를 분리하면 흐름이 더 명확하다.
- 너비는 `int`로도 계산 가능하지만, 넓이 계산과 일관성을 위해 `long`으로 맞추는 편이 안전하다.
- 정답 코드는 "통과하는 코드"보다 "큰 입력에서도 해석과 성능이 모두 안정적인 코드" 쪽으로 다듬었다.

## 5) 문제 해결 노하우
- "현재 값이 더 작아지는 순간 무엇을 확정할 수 있는가"를 기준으로 스택 문제를 보면 구현이 쉬워진다.
- 단조 스택 문제는 보통 "값"보다 "인덱스"를 저장해야 범위를 계산하기 편하다.
- 검증은 아래 입력들을 먼저 돌려보면 빠르다:
  - 대표 샘플: `7 2 1 4 5 1 3 3`
  - 전부 같은 높이: `4 1000 1000 1000 1000`
  - 엄격 증가 / 엄격 감소 케이스
  - 막대가 1개인 최소 입력
