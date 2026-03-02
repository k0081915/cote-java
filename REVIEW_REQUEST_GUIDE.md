# 문제 검토 요청 가이드

이 문서는 내가 문제 번호를 빠르게 말하면, Codex가 자동으로 코드 검토/정리 문서를 생성하도록 하기 위한 기준입니다.

## 내가 입력할 명령 형식
아래처럼 짧게 말하면 됩니다.

- `BOJ 13398 검토해줘`
- `lv2_example 검토해줘`
- `BOJ 13398 문서화해줘`

## Codex가 수행할 작업
요청을 받으면 다음 순서로 진행합니다.

1. 문제 패키지 위치 탐색  
   - 예: `src/problems/boj/p13398/Main.java`, `src/problems/programmers/lv2_example/Solution.java`
2. 제출 코드 확인 및 로직 검토
3. (가능하면) 컴파일/실행으로 기본 동작 점검
4. 해당 패키지에 리뷰 문서 생성
5. `solved_log.md`에 AI 태깅 채우기

## 생성되는 문서 위치/이름
- 위치: 해당 문제 패키지 내부
- 파일명: `review.md`  
  - 예: `src/problems/boj/p13398/review.md`

## review.md 구성 항목
문서는 아래 5개 섹션을 고정으로 포함합니다.

1. 문제 유형
2. 문제 접근 방법
3. 정답 코드
4. 내가 실수한 부분
5. 문제 해결 노하우

## 정확도 체크를 위해 필요한 것
- 코드가 `Main.java` 기준으로 컴파일 가능해야 합니다.
- 입력 예시가 있으면 함께 주면 검토 품질이 더 좋아집니다.
  - 예: `입력예시는 1 2 3 ...`

## 참고
- 패키지 규칙은 `src/problems/<platform>/<problemId>/Main.java` 기준으로 해석합니다.
- `<platform>` 매핑: `BOJ -> boj`, `Programmers -> programmers`
