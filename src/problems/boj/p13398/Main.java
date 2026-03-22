package problems.boj.p13398;

// 백준(DP) 연속합2 - https://www.acmicpc.net/problem/13398
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int noRemove = arr[0];  // 삭제를 한 번도 하지 않은 연속합
        int remove = arr[0];    // 삭제를 한 번 수행한 연속합
        int max = arr[0];   // 글로벌 최댓값 유지

        for (int i = 1; i < n; i++) {
            int cur = arr[i];

            // 주의: nextRemove를 먼저 계산해야 함
            // 이전 단계의 noRemove 값이 nextRemoved 계산에 필요하기 때문
            int nextRemove = Math.max(noRemove, remove + cur);
            int nextNoRemove = Math.max(noRemove + cur, cur);

            remove = nextRemove;
            noRemove = nextNoRemove;

            // 최댓값 갱신
            max = Math.max(max, Math.max(remove, noRemove));
        }

        System.out.println(max);
    }
}
