package problems.boj.p3273;

// 백준 3273 - https://www.acmicpc.net/problem/3273
import java.util.*;
import java.io.*;

class Main {
    static int n, x;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 크기
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        // 수열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 목표 합
        x = Integer.parseInt(br.readLine());

        // 오름차순 정렬
        Arrays.sort(arr);

        // 투포인터 함수 호출
        System.out.print(twoPointer(arr));
    }

    public static int twoPointer(int[] arr) {
        int left = 0;    // 가장 왼쪽 인덱스
        int right = n - 1;    // 가장 오른쪽 인덱스
        int count = 0;
        int sum = 0;

        // left가 right를 넘어가지 않을 동안 수행
        while(left < right) {
            sum = arr[left] + arr[right];    // 두 수의 합 계산

            // 합이 x와 같으면
            if(sum == x) {
                count++;    // count+1
                // 해당 쌍은 만족했으니 포인터를 옮겨줌
                left++;
                right--;
                continue;
            }

            if(sum < x) left++;    // x보다 작으면 하한을 올려야함
            else right--;    // x보다 크면 상한을 내려야함
        }

        return count;
    }
}
