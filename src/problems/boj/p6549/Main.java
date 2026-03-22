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

            // 한 중에 히스토그램 높이가 다 안들어올 수도 있으므로
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