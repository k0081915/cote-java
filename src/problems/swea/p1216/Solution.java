package problems.swea.p1216;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    static char[][] arr;
    static final int SIZE = 100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int t = Integer.parseInt(br.readLine());

            arr = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                arr[i] = br.readLine().toCharArray();
            }

            int answer = 0;

            // 가장 긴 회문 길이를 ㅊ찾기 위해 100부터 1까지 감소하며 검사
            for (int len = SIZE; len >= 1; len--) {
                if (existPalindrome(len)) {
                    answer = len;
                    break;  // 가장 긴 길이부터 검사했으므로 찾는 순간 break
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    // 길이가 len인 회문이 가로 세로 방향에 하나라도 있는지 확인
    static boolean existPalindrome(int len) {
        // 가로 방향 검사
        for (int row = 0; row < SIZE; row++) {
            for (int i = 0; i <= SIZE - len; i++) {
                if (isRowPalindrome(row, i, len)) {
                    return true;
                }
            }
        }

        // 세로 방향 검사
        for (int col = 0; col < SIZE; col++) {
            for (int i = 0; i <= SIZE - len; i++) {
                if (isColPalindrome(col, i, len)) {
                    return true;
                }
            }
        }

        return false;
    }

    // 특정 행 row에서 start부터 start + len - 1까지가 회문인지 확인
    static boolean isRowPalindrome(int row, int start, int len) {
        int left = start;
        int right = start + len - 1;

        // 양쪽 끝에서부터 가운데로 좁히면서 비교
        while(left < right) {
            if (arr[row][left] != arr[row][right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 특정 열 col에서 start부터 start + len - 1까지가 회문인지 확인
    static boolean isColPalindrome(int col, int start, int len) {
        int top = start;
        int bottom = start + len - 1;

        // 위아래 끝에서부터 가운데로 좁히면서 비교
        while(top < bottom) {
            if (arr[top][col] != arr[bottom][col]) {
                return false;
            }
            top++;
            bottom--;
        }
        return true;
    }
}
