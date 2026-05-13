package problems.swea.p1873;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 */

public class Solution {

    static int h, w;    // 맵 높이, 너비
    static int tr, tc;  // 현재 전차 위치

    static char[][] map;    // 게임 맵

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int test = 1; test <= t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());   // 맵 높이
            w = Integer.parseInt(st.nextToken());   // 맵 너비

            map = new char[h][w];

            for (int i = 0; i < h; i++) {
                String str = br.readLine();  // 맵 입력
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
                        tr = i;
                        tc = j;
                    }
                }
            }

            int n = Integer.parseInt(br.readLine());    // 사용자 입력 개수
            String cmd = br.readLine(); // 사용자 입력

            for (int i = 0; i < n; i++) {
                switch(cmd.charAt(i)) {
                    case 'U':
                        moveUp();
                        break;
                    case 'D':
                        moveDown();
                        break;
                    case 'L':
                        moveLeft();
                        break;
                    case 'R':
                        moveRight();
                        break;
                    case 'S':
                        shoot();
                        break;
                }
            }

            // 답 출력
            sb.append("#").append(test).append(" ");

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void moveUp() {
        map[tr][tc] = '^';

        // 게임 맵 범위 이내 && 평지여야만 이동 가능
        if (tr - 1 >= 0 && map[tr - 1][tc] == '.') {
            map[tr][tc] = '.';  // 원래 자리 평지로 변경
            tr -= 1;    // 현재 전차 행 -1
            map[tr][tc] = '^';  // 전차 세팅
        }
    }

    static void moveDown() {
        map[tr][tc] = 'v';

        // 게임 맵 범위 이내 && 평지여야만 이동 가능
        if (tr + 1 < h && map[tr + 1][tc] == '.') {
            map[tr][tc] = '.';  // 원래 자리 평지로 변경
            tr += 1;    // 현재 전차 행 +1
            map[tr][tc] = 'v';  // 전차 세팅
        }
    }

    static void moveLeft() {
        map[tr][tc] = '<';

        // 게임 맵 범위 이내 && 평지여야만 이동 가능
        if (tc - 1 >= 0 && map[tr][tc - 1] == '.') {
            map[tr][tc] = '.';  // 원래 자리 평지로 변경
            tc -= 1;    // 현재 전차 열 -1
            map[tr][tc] = '<';  // 전차 세팅
        }
    }

    static void moveRight() {
        map[tr][tc] = '>';

        // 게임 맵 범위 이내 && 평지여야만 이동 가능
        if (tc + 1 < w && map[tr][tc + 1] == '.') {
            map[tr][tc] = '.';  // 원래 자리 평지로 변경
            tc += 1;    // 현재 전차 열 +1
            map[tr][tc] = '>';  // 전차 세팅
        }
    }

    static void shoot() {
        char dir = map[tr][tc]; // 현재 전차가 바라보고 있는 방향
        int r = tr; // 포탄 행 이동
        int c = tc; // 포탄 열 이동

        if (dir == '^') {
            while (true) {
                r -= 1; // 포탄을 계속 위로 이동시킴

                // 맵 밖을 벗어나면 break
                if (r < 0) {
                    break;
                }

                // 강철 벽을 만나면 아무것도 하지 않고 break
                if (map[r][tc] == '#') {
                    break;
                }

                // 일반 벽을 만나면 해당 위치를 평지로 만들고 break
                if (map[r][tc] == '*') {
                    map[r][tc] = '.';
                    break;
                }
            }
        } else if (dir == 'v') {
            while (true) {
                r += 1; // 포탄을 계속 아래로 이동시킴

                // 맵 밖을 벗어나면 break
                if (r >= h) {
                    break;
                }

                // 강철 벽을 만나면 아무것도 하지 않고 break
                if (map[r][tc] == '#') {
                    break;
                }

                // 일반 벽을 만나면 해당 위치를 평지로 만들고 break
                if (map[r][tc] == '*') {
                    map[r][tc] = '.';
                    break;
                }
            }
        } else if (dir == '<') {
            while (true) {
                c -= 1; // 포탄을 계속 왼쪽으로 이동시킴

                // 맵 밖을 벗어나면 break
                if (c < 0) {
                    break;
                }

                // 강철 벽을 만나면 아무것도 하지 않고 break
                if (map[tr][c] == '#') {
                    break;
                }

                // 일반 벽을 만나면 해당 위치를 평지로 만들고 break
                if (map[tr][c] == '*') {
                    map[tr][c] = '.';
                    break;
                }
            }
        } else {
            while (true) {
                c += 1; // 포탄을 계속 오른쪽으로 이동시킴

                // 맵 밖을 벗어나면 break
                if (c >= w) {
                    break;
                }

                // 강철 벽을 만나면 아무것도 하지 않고 break
                if (map[tr][c] == '#') {
                    break;
                }

                // 일반 벽을 만나면 해당 위치를 평지로 만들고 break
                if (map[tr][c] == '*') {
                    map[tr][c] = '.';
                    break;
                }
            }
        }
    }
}
