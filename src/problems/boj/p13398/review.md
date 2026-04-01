# BOJ 13398 리뷰

## 1) 문제 유형
- DP (Kadane 변형)
- 연속 부분합
- 상태 분리: 삭제 미사용 / 삭제 1회 사용

## 2) 문제 접근 방법
- `noRemove[i]`: i에서 끝나는, 원소 삭제 없이 가능한 최대 연속합
- `remove[i]`: i에서 끝나는, 원소를 1번 삭제한 상태의 최대 연속합
- 점화식(공간 최적화):
  - `nextNoRemove = max(noRemove + cur, cur)`
  - `nextRemove = max(noRemove, remove + cur)`
- 매 스텝마다 `max`를 갱신해 전체 최댓값을 유지.

현재 `Main.java`는 위 점화식을 정확히 구현하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.boj.p13398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int first = Integer.parseInt(st.nextToken());
        int keep = first;
        int drop = first;
        int answer = first;

        for (int i = 1; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());

            int nextDrop = Math.max(keep, drop + cur);
            int nextKeep = Math.max(keep + cur, cur);

            keep = nextKeep;
            drop = nextDrop;
            answer = Math.max(answer, Math.max(keep, drop));
        }

        System.out.println(answer);
    }
}
```

## 4) 내 코드와 다른 부분
- 배열 전체를 저장하지 않고, 입력을 읽으면서 상태 2개만 굴려도 충분하다.
- 변수 이름을 `keep`, `drop`처럼 상태 의미가 드러나게 두면 점화식 해석이 더 빨라진다.
- 핵심 차이는 "공간 최적화"보다 "상태 의미가 바로 읽히는 구현"에 있다.

## 5) 문제 해결 노하우
- "원소 1개 제거 가능" 유형은 보통 상태를 2개로 쪼개면 깔끔해짐.
- 삭제 상태를 별도 배열로 두지 않고 변수 2개로 굴리면 구현이 단순해짐.
- 검증은 아래 3종 세트로 빠르게:
  - 일반 샘플
  - 전부 음수
  - 길이 1 입력
