package problems.boj.p2470;

import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(arr);

        // left, right 설정
        int left = 0;
        int right = N - 1;

        // 초기값 설정
        int x = arr[left];
        int y = arr[right];

        // 가장 0에 가까운 값
        int min = Integer.MAX_VALUE;

        // left가 right 보다 작을 때 동안 실행
        while(left < right) {
            // 합산
            int sum = arr[left] + arr[right];

            // 합의 절대값이 min보다 작으면
            if(Math.abs(sum) < min) {
                // min 갱신, x y 갱신
                min = Math.abs(sum);
                x = arr[left];
                y = arr[right];
            }

            // sum 음수: 왼쪽에 편향되어 있다는 뜻 -> left 증가
            // sum 양수: 오른쪽에 편향되어 있다는 뜻 -> right 감소
            // sum 0: 0에 가장 가까우므로 break
            if(sum < 0) {
                left++;
            } else if(sum > 0) {
                right--;
            } else {
                break;
            }
        }

        System.out.println(x + " " + y);
    }
}
