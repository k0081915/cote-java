package problems.boj.p1806;

import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // left, right를 동일한 인덱스에서 시작
        int left = 0;
        int right = 0;
        // 최소 길이를 배열 최대 길이+1로 설정
        int minLength = 100001;
        // 초기 sum 세팅
        int sum = arr[left];

        while(left <= right) {
            // sum < S: 합이 부족하므로 슬라이딩 윈도우 right 늘림
            if(sum < S) {
                // right가 배열 길이와 같으면 더이상 늘리지 못하므로 break
                if(right + 1 == N) {
                    break;
                }
                // right++ 해주고 sum에 더해줌
                right++;
                sum += arr[right];
            } else {    // sum >= S: 합이 충분하므로 슬라이딩 윈도우 left 좁힘
                // 최소 길이 갱신
                minLength = Math.min(minLength, right - left + 1);
                // sum에서 빼주고 left++
                sum -= arr[left];
                left++;
            }
        }

        // 최소 길이가 초기값과 같으면 0 출력
        if(minLength == 100001) System.out.print(0);
        else System.out.print(minLength);
    }
}