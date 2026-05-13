# SWEA 1873 리뷰

## 1) 문제 유형
- 구현
- 시뮬레이션
- 2차원 배열
- 방향 처리

## 2) 문제 접근 방법
- 전차의 현재 위치와 방향을 함께 관리한다.
- 이동 명령 `U`, `D`, `L`, `R`이 들어오면 먼저 전차 방향을 바꾼다.
- 방향을 바꾼 뒤, 다음 칸이 맵 안이고 평지 `.`이면 전차를 이동시킨다.
- 발사 명령 `S`가 들어오면 현재 방향으로 포탄을 직진시킨다.
- 포탄은 벽돌 벽 `*`을 만나면 해당 칸을 평지로 만들고 멈추며, 강철 벽 `#`을 만나면 그대로 멈춘다.

현재 `Solution.java`는 방향 전환, 이동, 포탄 처리를 모두 문제 조건에 맞게 구현하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    // 방향 인덱스: 0=위, 1=아래, 2=왼쪽, 3=오른쪽
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};
    static final char[] TANK = {'^', 'v', '<', '>'};

    static int h;
    static int w;
    static int tankRow;
    static int tankCol;
    static int direction;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];

            for (int row = 0; row < h; row++) {
                String line = br.readLine();
                for (int col = 0; col < w; col++) {
                    map[row][col] = line.charAt(col);
                    // 전차 문자를 찾으면 위치와 방향을 함께 저장한다.
                    setTankIfNeeded(row, col);
                }
            }

            // 명령 개수는 commands.length()와 같으므로 입력만 소비한다.
            br.readLine();
            String commands = br.readLine();
            for (int i = 0; i < commands.length(); i++) {
                execute(commands.charAt(i));
            }

            sb.append('#').append(tc).append(' ');
            for (int row = 0; row < h; row++) {
                sb.append(map[row]).append('\n');
            }
        }

        System.out.print(sb);
    }

    static void setTankIfNeeded(int row, int col) {
        for (int dir = 0; dir < TANK.length; dir++) {
            if (map[row][col] == TANK[dir]) {
                tankRow = row;
                tankCol = col;
                direction = dir;
                return;
            }
        }
    }

    static void execute(char command) {
        // 발사는 전차 위치를 바꾸지 않고 현재 방향만 사용한다.
        if (command == 'S') {
            shoot();
            return;
        }

        // 이동 명령은 먼저 바라보는 방향부터 바꾼다.
        direction = getDirection(command);
        map[tankRow][tankCol] = TANK[direction];

        // 다음 칸이 맵 안의 평지일 때만 실제 위치를 이동한다.
        int nextRow = tankRow + DR[direction];
        int nextCol = tankCol + DC[direction];
        if (!isInside(nextRow, nextCol) || map[nextRow][nextCol] != '.') {
            return;
        }

        map[tankRow][tankCol] = '.';
        tankRow = nextRow;
        tankCol = nextCol;
        map[tankRow][tankCol] = TANK[direction];
    }

    static int getDirection(char command) {
        if (command == 'U') {
            return 0;
        }
        if (command == 'D') {
            return 1;
        }
        if (command == 'L') {
            return 2;
        }
        return 3;
    }

    static void shoot() {
        // 포탄은 전차 바로 앞 칸부터 현재 방향으로 직진한다.
        int row = tankRow + DR[direction];
        int col = tankCol + DC[direction];

        while (isInside(row, col)) {
            // 강철 벽은 파괴하지 못하고 포탄만 사라진다.
            if (map[row][col] == '#') {
                return;
            }

            // 벽돌 벽은 평지로 바뀌고 포탄은 사라진다.
            if (map[row][col] == '*') {
                map[row][col] = '.';
                return;
            }

            row += DR[direction];
            col += DC[direction];
        }
    }

    static boolean isInside(int row, int col) {
        return row >= 0 && row < h && col >= 0 && col < w;
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 로직은 같습니다. 현재 코드도 이동과 포탄 처리를 문제 조건대로 수행합니다.
- 현재 코드는 `moveUp`, `moveDown`, `moveLeft`, `moveRight`와 포탄 방향별 분기를 각각 따로 둡니다.
- 추천 코드는 방향 배열 `DR`, `DC`를 사용해 이동과 발사를 하나의 공통 로직으로 처리합니다.
- 방향 문자를 `TANK` 배열로 관리해서 전차 표시 갱신도 공통화했습니다.
- 현재 코드는 학습 단계에서 흐름이 명확하고, 추천 코드는 중복을 줄여 유지보수하기 쉬운 버전입니다.

## 5) 문제 해결 노하우
- 2차원 시뮬레이션에서 방향이 4개로 반복되면 `dr`, `dc` 배열을 먼저 떠올리면 코드가 많이 줄어든다.
- 이동 명령은 "방향 변경"과 "이동 가능하면 이동"을 분리해서 생각하면 안정적이다.
- 발사 명령은 현재 방향으로 한 칸씩 전진하며 `*`, `#`, 맵 밖 중 무엇을 먼저 만나는지만 보면 된다.
- 검증은 아래 케이스를 보면 좋다:
  - 방향만 바뀌고 이동은 막히는 경우
  - 평지로 실제 이동하는 경우
  - 포탄이 벽돌 벽을 부수는 경우
  - 포탄이 강철 벽이나 맵 밖에서 멈추는 경우
