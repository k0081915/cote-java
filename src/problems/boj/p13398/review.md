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

        int noRemove = arr[0];
        int remove = arr[0];
        int max = arr[0];

        for (int i = 1; i < n; i++) {
            int cur = arr[i];

            int nextRemove = Math.max(noRemove, remove + cur);
            int nextNoRemove = Math.max(noRemove + cur, cur);

            remove = nextRemove;
            noRemove = nextNoRemove;

            max = Math.max(max, Math.max(remove, noRemove));
        }

        System.out.println(max);
    }
}
```

## 4) 내가 실수한 부분
- 상태 갱신 순서를 바꾸면 오답 가능성이 큼.
- 특히 `nextRemove`는 "이전 noRemove"가 필요하므로 먼저 계산해야 안전함.
- `n=1` 같은 최소 입력에서 초기값(`arr[0]`) 처리 누락 시 바로 틀리기 쉬움.

## 5) 문제 해결 노하우
- "원소 1개 제거 가능" 유형은 보통 상태를 2개로 쪼개면 깔끔해짐.
- 삭제 상태를 별도 배열로 두지 않고 변수 2개로 굴리면 구현이 단순해짐.
- 검증은 아래 3종 세트로 빠르게:
  - 일반 샘플
  - 전부 음수
  - 길이 1 입력
