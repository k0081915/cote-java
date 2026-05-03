package problems.swea.p1215;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int palindrome = Integer.parseInt(br.readLine());
            char[][] board = new char[8][8];

            int answer = 0;
            for (int i = 0; i < 8; i++) {
                String line = br.readLine();
                for (int j = 0; j < 8 - palindrome + 1; j++) {
                    String word = line.substring(j, j + palindrome);
                    if (isPalindrome(word)) {
                        answer++;
                    }
                }
                for (int j = 0; j < 8; j++) {
                    board[i][j] = line.charAt(j);
                }
            }

            List<String> list = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < 8; j++) {
                    str.append(board[j][i]);
                }
                list.add(String.valueOf(str));
            }

            for (String s : list) {
                for (int i = 0; i < 8 - palindrome + 1; i++) {
                    String word = s.substring(i, i + palindrome);
                    if (isPalindrome(word)) {
                        answer++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isPalindrome(String word) {
        for (int i = 0, j = word.length() - 1; i < word.length() / 2; i++, j--) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
