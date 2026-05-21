# SWEA 2814 리뷰

## 1) 문제 유형
- DFS
- 백트래킹
- 그래프
- 최장 단순 경로

## 2) 문제 접근 방법
- 이 문제는 그래프에서 같은 정점을 두 번 방문하지 않는 경로 중 가장 긴 경로의 길이를 찾는 문제다.
- 최단 거리가 아니라 가능한 모든 단순 경로를 확인해야 하므로 BFS보다 DFS/백트래킹이 적합하다.
- 모든 정점이 시작점이 될 수 있으므로 `1`번부터 `N`번까지 각각 DFS를 시작한다.
- DFS 중 다음 정점을 방문할 때 `visited[next] = true`로 표시하고, 재귀가 끝나면 다시 `false`로 되돌린다.
- 이 방문 해제가 있어야 다른 경로에서 같은 정점을 다시 사용할 수 있다.

현재 `Solution.java`는 모든 시작점에서 DFS를 수행하고, 재귀 복귀 시 방문 상태를 되돌리고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p2814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int answer;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                graph[to].add(from);
            }

            answer = 1;
            visited = new boolean[n + 1];

            for (int start = 1; start <= n; start++) {
                visited[start] = true;
                dfs(start, 1);
                visited[start] = false;
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int current, int length) {
        answer = Math.max(answer, length);

        for (int next : graph[current]) {
            if (visited[next]) {
                continue;
            }

            // 현재 경로에 next를 포함해 본 뒤, 재귀가 끝나면 되돌려 다른 경로도 탐색한다.
            visited[next] = true;
            dfs(next, length + 1);
            visited[next] = false;
        }
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 코드도 인접 리스트와 DFS 백트래킹으로 가장 긴 경로를 찾습니다.
- 현재 코드는 각 시작점 DFS가 끝난 뒤 `visited = new boolean[n + 1]`로 방문 배열을 새로 만듭니다.
- 추천 코드는 시작점도 `visited[start] = false`로 되돌려 같은 방문 배열을 재사용합니다.
- 현재 코드의 방식도 정답성 문제는 없지만, 백트래킹 관점에서는 추천 코드처럼 방문 처리와 해제가 한 쌍으로 보이는 편이 더 명확합니다.
- 추천 코드는 `List<Integer>[] graph`를 사용해 `List<List<Integer>>`보다 인덱스 접근을 조금 단순하게 했습니다.

## 5) 문제 해결 노하우
- “가장 긴 경로”라고 해서 BFS를 쓰면 안 된다. BFS는 보통 최단 거리나 레벨 탐색에 강하다.
- 이 문제는 가능한 경로 조합을 모두 시도해야 하므로 DFS로 들어갔다가 방문 상태를 되돌리는 백트래킹이 핵심이다.
- 방문 배열은 “현재 경로에서 이미 쓴 정점”을 뜻한다. 전체 탐색에서 영구적으로 막는 배열이 아니다.
- 정점 수가 작기 때문에 모든 시작점에서 DFS를 해도 가능하다.
- 검증은 아래 케이스를 보면 좋다:
  - `1-2-3`처럼 일직선 그래프의 정답은 `3`
  - `1-2`, `3-4`처럼 분리된 그래프의 정답은 `2`
  - 간선이 없는 `N=1` 그래프의 정답은 `1`
