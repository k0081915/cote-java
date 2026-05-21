package problems.swea.p2814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n, m;
    static int max;

    static List<List<Integer>> list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   // 정점 수
            m = Integer.parseInt(st.nextToken());   // 간선 수

            max = 1;
            visited = new boolean[n + 1];
            list = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                list.get(v1).add(v2);
                list.get(v2).add(v1);
            }

            for (int i = 1; i <= n; i++) {
                visited[i] = true;
                dfs(i, 1);
                visited = new boolean[n + 1];
            }

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int node, int length) {
        max = Math.max(max, length);

        for (Integer next : list.get(node)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, length + 1);
                visited[next] = false;
            }
        }
    }
}
