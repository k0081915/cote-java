# JavaCodingTest

자바로 코딩테스트 문제를 풀이하고, 문제별 리뷰 문서와 풀이 기록을 함께 관리하는 저장소입니다.

## 소개
- 플랫폼별 문제 풀이를 `Java`로 정리합니다.
- 각 문제 폴더에 제출 코드와 리뷰 문서를 함께 보관합니다.
- `solved_log.md`에 문제 유형, 회고, AI 태깅을 누적 기록합니다.

현재 포함된 플랫폼:
- BOJ
- PROGRAMMERS
- SWEA

## 디렉터리 구조
```text
JavaCodingTest
├─ src
│  └─ problems
│     ├─ boj
│     │  └─ p문제번호
│     │     ├─ Main.java
│     │     └─ review.md
│     ├─ programmers
│     │  └─ p문제번호
│     │     ├─ Solution.java
│     │     └─ review.md
│     └─ swea
│        └─ p문제번호
│           ├─ Solution.java
│           └─ review.md
├─ solved_log.md
├─ REVIEW_REQUEST_GUIDE.md
└─ README.md
```

## 문제 정리 방식
- `Main.java` 또는 `Solution.java`
  - 온라인 저지 제출용 정답 코드
- `review.md`
  - 문제 유형
  - 문제 접근 방법
  - 추천 정답 코드
  - 내 코드와 다른 부분
  - 문제 해결 노하우
- `solved_log.md`
  - 날짜, 문제 번호, 태그, 결과, 회고를 누적 기록

## 작성 규칙
- BOJ: `src/problems/boj/p문제번호/Main.java`
- Programmers: `src/problems/programmers/p문제번호/Solution.java`
- SWEA: `src/problems/swea/p문제번호/Solution.java`
- 패키지명은 폴더 구조와 동일하게 맞춥니다.
- 문제를 검토한 뒤에는 같은 폴더에 `review.md`를 함께 정리합니다.

## 현재 풀이 예시
- BOJ 13398
- BOJ 6549
- BOJ 3273
- BOJ 2470
- BOJ 1806
- SWEA 1204

세부 기록은 [solved_log.md](./solved_log.md)에서 확인할 수 있습니다.

## 목적
- 단순 정답 제출용 저장소가 아니라, 풀이 과정과 회고까지 남기는 개인 코딩테스트 아카이브를 목표로 합니다.
- 같은 유형을 다시 만났을 때 빠르게 복습할 수 있도록 문제별 리뷰를 함께 관리합니다.
