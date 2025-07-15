# 📈 Java Stock Simulation Game

> Java 기반 콘솔 프로그램으로 구현된 **주식 시뮬레이션 게임**
> 
> MVC 패턴 아키텍처에 기반하며, Oracle 데이터베이스와 JDBC를 활용한 **데이터 연동**,
> 주식 정보 및 퀴즈 데이터에 대한 **CRUD 기능**도 포함
> 
> 본 프로젝트는 단순한 로컬 오라클 설치가 아닌, **Ubuntu 서버에 설치된 Oracle DB**와의 연동을 전제로 구현되었습니다.
---

<br>

## 👨‍👩‍👧‍👦 팀원 소개

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Hyunsoo1998">
        <img src="https://avatars.githubusercontent.com/Hyunsoo1998" width="150px;" alt="Hyunsoo"/><br />
        <sub><b>김현수</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Minkyoungg0">
        <img src="https://avatars.githubusercontent.com/Minkyoungg0" width="150px;" alt="Minkyoungg0"/><br />
        <sub><b>문민경</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/yeomyeoung">
        <img src="https://avatars.githubusercontent.com/yeomyeoung" width="150px;" alt="yeomyeoung"/><br />
        <sub><b>박여명</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/bbo9866">
        <img src="https://avatars.githubusercontent.com/bbo9866" width="150px;" alt="bbo9866"/><br />
        <sub><b>박지원</b></sub>
      </a>
    </td>
  </tr>
</table>

---

## 🎯 프로젝트 목표

- Java 언어에 대한 이해 및 객체지향 설계 연습
- MVC 아키텍처 기반 구조 설계 및 적용
- JDBC를 활용한 Oracle DB 연동 경험
- 주식/퀴즈에 대한 CRUD 기능 구현 실습

---

## 🛠️ 기술 스택

| 항목        | 내용                                 |
|-------------|--------------------------------------|
| Language    | Java 17                              |
| 서버OS      | Ubuntu 20.04 LTS                     |
| DB          | Oracle 11g XE                        |
| DB 연동     | JDBC + `DBUtil` 커스텀 유틸 클래스   |
| 아키텍처    | MVC (Model-View-Controller) 패턴     |
| 데이터 처리 | SQL, PreparedStatement, ResultSet    |
| 실행 방식   | 콘솔 기반 CLI 입력 시스템            |
| 접근 방식   | SSH 기반 원격 접속 (`22번 포트`)     |
| 포트포워딩   | 로컬포트 ↔ 서버 Oracle Listener 포트(1521) 연결       |

---

## 📌 주요 기능

### 🎮 게임 흐름
- 사용자 이름 입력 및 환영 메시지
- 주식 관련 퀴즈 5문제 랜덤 출제
- 현재 주식 시세 안내
- 5턴 동안 모의 주식 매수/매도 진행
- 수익률 및 종합 점수 평가

### 🧠 퀴즈 기능
- DB에서 퀴즈 문제 10개 중 **랜덤 5개** 출제
- Oracle DB에서 실시간 문제 불러오기
- 정답 점수 집계 후 투자 점수와 합산 평가

### 💹 주식 기능
- DB에서 주식 정보 불러오기
- **주식 시세 자동 변동 시뮬레이션**
- 주식 **매수 / 매도 / 자산 조회** 기능
- ✅ CRUD 기능
  - 종목 추가 (중복 확인)
  - 종목 삭제
  - 종목 전체 조회

---

## 📂 프로젝트 구조 (MVC)

```
📁 model
├── Player.java
├── Stock.java
├── Quiz.java
├── QuizDAO.java
└── StockDAO.java

📁 view
└── GameView.java

📁 controller
└── MainController.java

📁 util
└── DBUtil.java

📄 dbinfo.properties
```

## 🗃️ DB 테이블 스키마

### `Stock` 테이블

```sql
CREATE TABLE Stock (
    id NUMBER PRIMARY KEY,
    stock_name VARCHAR2(100),
    stock_price NUMBER
);
```

### `Quiz` 테이블
```sql
CREATE TABLE quiz (
    id NUMBER PRIMARY KEY,
    question VARCHAR2(200),
    opt1 VARCHAR2(100),
    opt2 VARCHAR2(100),
    opt3 VARCHAR2(100),
    opt4 VARCHAR2(100),
    answer NUMBER
);
```

### 🧪 실행 예시
```Markdown
이름을 입력해주세요:
> 지원

지원님, 환영합니다!
...

[1번 문제] 주식을 매수한다는 뜻은?
1. 판다 2. 산다 3. 인출 4. 이자 받는다
> 2
→ 정답: 2
...

[현재 주식 시세 안내]
1. 삼성전자 65,000 (+1.2%)
2. SK하이닉스 120,000 (-0.8%)
...

→ 5턴 동안 매수/매도 시뮬레이션 진행
→ 최종 수익률 및 점수 계산
```

### ✅ 실행 조건
- Java JDK 17 이상 설치

- Oracle DB 및 Listener 실행

- dbinfo.properties 파일 설정 (JDBC 정보 포함)

``` properties
jdbc.driver=oracle.jdbc.driver.OracleDriver
jdbc.url=jdbc:oracle:thin:@localhost:1521:xe
jdbc.id=scott
jdbc.pw=tiger
```

## 💡 프로젝트 인사이트

- **MVC 패턴 학습의 실전 적용**  
  각 클래스의 역할을 명확히 구분하며 `Model`, `View`, `Controller`를 분리

- **DB 설계와 연동 흐름 이해**  
  주식 정보와 퀴즈 데이터를 Oracle DB에 테이블로 설계하고 JDBC로 연동하면서,  
  SQL 작성과 JDBC의 자원 관리(`Connection`, `ResultSet`, `PreparedStatement`)에 대한 이해도 증가

- **CRUD 구현 경험**  
  단순 조회 뿐만 아니라, 주식 추가/삭제와 같은 기능까지 직접 구현하면서  
  DB와의 상호작용을 체계적으로 구성하는 경험
  
---

## 🧯 트러블슈팅 경험

| 문제 상황 | 해결 방법 |
|-----------|-----------|
| 🔻 Quiz table 중복키 설정 | 기존 quiz id, option id 2개의 기본키 설정 -> quiz id로 기본키 설정하여 table 재구성 후 로직 수 |
| 🔻 `ClassNotFoundException` 발생 | `ojdbc` 드라이버를 프로젝트에 정상적으로 추가하지 않은 것이 원인이었으며, 라이브러리 추가로 해결 |
| 🔻 퀴즈 중복 랜덤 출제 실패 | `DBMS_RANDOM.VALUE`를 활용한 쿼리로 문제를 무작위로 정렬하고 `ROWNUM <= 5`로 상위 5개 추출하는 방식으로 개선 |
