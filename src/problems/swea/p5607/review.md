# SWEA 5607 리뷰

## 1) 문제 유형
- 조합론
- 모듈러 연산
- 페르마의 소정리
- 빠른 거듭제곱

## 2) 문제 접근 방법
- 구해야 하는 값은 `nCr = n! / (r! * (n-r)!)`이다.
- 모듈러 연산에서는 나눗셈을 직접 할 수 없으므로 분모의 모듈러 역원을 곱해야 한다.
- `MOD = 1234567891`은 소수이므로 페르마의 소정리를 사용할 수 있다.
- 어떤 수 `x`의 역원은 `x^(MOD-2) % MOD`로 구할 수 있다.
- 여러 테스트케이스를 빠르게 처리하려면 `factorial`과 `inverseFactorial`을 미리 구해두고, 각 조합을 `O(1)`에 계산하면 된다.

현재 `Solution.java`는 팩토리얼을 전처리하고 분모의 역원을 빠른 거듭제곱으로 구하고 있어 정답으로 판단됩니다.

## 3) 정답 코드
```java
package problems.swea.p5607;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int MAX_N = 1_000_000;
    static final long MOD = 1_234_567_891L;

    static long[] factorial = new long[MAX_N + 1];
    static long[] inverseFactorial = new long[MAX_N + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        preprocess();

        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            long answer = combination(n, r);
            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void preprocess() {
        factorial[0] = 1;
        for (int i = 1; i <= MAX_N; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        // (MAX_N!)의 역원을 한 번만 구한 뒤, 역팩토리얼을 뒤에서부터 채운다.
        inverseFactorial[MAX_N] = pow(factorial[MAX_N], MOD - 2);
        for (int i = MAX_N; i >= 1; i--) {
            inverseFactorial[i - 1] = inverseFactorial[i] * i % MOD;
        }
    }

    static long combination(int n, int r) {
        if (r < 0 || r > n) {
            return 0;
        }

        // nCr = n! * (r!)^-1 * ((n-r)!)^-1 mod MOD
        return factorial[n] * inverseFactorial[r] % MOD * inverseFactorial[n - r] % MOD;
    }

    static long pow(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }
}
```

## 4) 내 코드와 다른 부분
- 핵심 수학 아이디어는 같습니다. 현재 코드도 `fact[n] * pow(fact[r] * fact[n-r], MOD-2)`로 조합을 계산합니다.
- 현재 코드는 테스트케이스마다 분모 전체의 역원을 빠른 거듭제곱으로 구합니다.
- 추천 코드는 `inverseFactorial`까지 전처리해서 각 테스트케이스의 조합 계산을 `O(1)`로 줄입니다.
- 현재 재귀 `pow`도 깊이가 작아 문제는 없지만, 추천 코드는 반복문 기반 빠른 거듭제곱으로 스택 사용을 없앴습니다.
- 현재 풀이도 정답이고, 추천 코드는 테스트케이스가 많을 때 더 효율적인 버전입니다.

## 5) 문제 해결 노하우
- 모듈러에서 `/`는 사용할 수 없고, 역원을 곱해야 한다.
- `MOD`가 소수이면 페르마의 소정리로 `a^(MOD-2)`를 역원으로 쓸 수 있다.
- 조합 문제에서 `N`의 최댓값이 크고 테스트케이스가 여러 개면 팩토리얼 전처리를 먼저 떠올리면 좋다.
- `long`을 사용해야 곱셈 중간값이 `int` 범위를 넘는 문제를 피할 수 있다.
- 검증은 아래 케이스를 보면 좋다:
  - `5C2 = 10`
  - `10C0 = 1`
  - `10C10 = 1`
  - `6C3 = 20`
