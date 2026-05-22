# SWEA 6808 리뷰

## 1) 문제 유형
- DFS
- 백트래킹
- 순열
- 완전탐색

## 2) 문제 접근 방법
- 규영이의 카드 9장은 입력 순서대로 고정되어 있다.
- 인영이는 남은 카드 9장을 낼 수 있으며, 가능한 모든 순서를 확인해야 한다.
- 각 라운드에서 카드 숫자가 큰 사람이 두 카드의 합만큼 점수를 얻는다.
- 인영이 카드 순열을 DFS로 만들면서 라운드별 점수를 누적한다.
- 9라운드가 끝났을 때 규영이 점수가 더 크면 승리, 작으면 패배로 센다.

현재 `Solution.java`는 입력 카드와 남은 카드의 역할명이 반대로 되어 있지만, 마지막에 출력 순서를 뒤집어 결과는 맞게 나옵니다. 다만 문제 모델과 변수명이 어긋나서 헷갈리기 쉬우므로, 추천 코드는 규영이 카드를 입력 순서대로 고정하고 인영이 카드를 순열로 탐색하는 형태로 작성합니다.

## 3) 정답 코드
```java
package problems.swea.p6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] gyuCards;
    static int[] inCards;
    static boolean[] selected;
    static int win;
    static int lose;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            gyuCards = new int[9];
            inCards = new int[9];
            selected = new boolean[9];
            boolean[] usedCard = new boolean[19];
            win = 0;
            lose = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                gyuCards[i] = Integer.parseInt(st.nextToken());
                usedCard[gyuCards[i]] = true;
            }

            int index = 0;
            for (int card = 1; card <= 18; card++) {
                if (!usedCard[card]) {
                    inCards[index++] = card;
                }
            }

            dfs(0, 0, 0);
            sb.append('#').append(tc).append(' ').append(win).append(' ').append(lose).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int round, int gyuScore, int inScore) {
        if (round == 9) {
            if (gyuScore > inScore) {
                win++;
            } else if (gyuScore < inScore) {
                lose++;
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (selected[i]) {
                continue;
            }

            selected[i] = true;

            int gyuCard = gyuCards[round];
            int inCard = inCards[i];
            int score = gyuCard + inCard;

            // 이번 라운드에서 더 큰 카드를 낸 사람에게 두 카드의 합을 더한다.
            if (gyuCard > inCard) {
                dfs(round + 1, gyuScore + score, inScore);
            } else {
                dfs(round + 1, gyuScore, inScore + score);
            }

            // 다른 인영이 카드 순서를 만들기 위해 선택 상태를 되돌린다.
            selected[i] = false;
        }
    }
}
```

## 4) 내 코드와 다른 부분
- 현재 코드는 입력으로 받은 규영이 카드를 `in`에 넣고, 남은 인영이 카드를 `gyu`에 넣습니다.
- 그 상태에서 남은 카드를 고정하고 입력 카드를 순열로 돌린 뒤, 최종 출력에서 `lose win`으로 뒤집습니다.
- 결과는 맞게 나오지만, 변수명과 문제의 역할이 반대라 디버깅할 때 혼동이 생기기 쉽습니다.
- 추천 코드는 입력 카드를 `gyuCards`, 남은 카드를 `inCards`로 두어 문제 설명과 코드가 일치합니다.
- DFS 구조 자체는 같습니다. 한쪽 카드 순서를 고정하고 다른 쪽 카드의 모든 순열을 탐색합니다.

## 5) 문제 해결 노하우
- 순서가 중요한 카드 배치 문제는 조합이 아니라 순열이다.
- 한 사람의 카드 순서는 고정되어 있으므로, 다른 사람의 카드 9장만 순열로 만들면 된다.
- `9! = 362880`이라 모든 순열을 탐색해도 충분히 가능하다.
- 방문 배열은 “이번 순열에서 이미 사용한 인영이 카드”를 의미하므로, 재귀가 끝나면 반드시 해제해야 한다.
- 검증은 아래 케이스를 보면 좋다:
  - `1 3 5 7 9 11 13 15 17` -> `112097 250783`
  - `1 2 3 4 5 6 7 8 9` -> `0 362880`
  - `10 11 12 13 14 15 16 17 18` -> `362880 0`
