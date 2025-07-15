# 📈 Java Stock Simulation Game

> Java 기반 콘솔 프로그램으로 구현된 **주식 시뮬레이션 게임**
> MVC 패턴 아키텍처에 기반하며, Oracle 데이터베이스와 JDBC를 활용한 **실제 데이터 연동**, 주식 정보 및 퀴즈 데이터에 대한 **CRUD 기능**도 포함

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
| DB          | Oracle 11g XE                        |
| DB 연동     | JDBC + `DBUtil` 커스텀 유틸 클래스   |
| 아키텍처    | MVC (Model-View-Controller) 패턴     |
| 데이터 처리 | SQL, PreparedStatement, ResultSet    |
| 실행 방식   | 콘솔 기반 CLI 입력 시스템            |

---

## 📌 주요 기능

### 🎮 게임 흐름
- 사용자 이름 입력 및 환영 메시지
- 주식 관련 퀴즈 5문제 랜덤 출제
- 현재 주식 시세 안내
- 5턴 동안 모의 주식 매수/매도 진행
- 수익률 및 종합 점수 평가

### 🧠 퀴즈 기능 (QuizDAO)
- 퀴즈 문제 10개 중 **랜덤 5개** 출제
- Oracle DB에서 실시간 문제 불러오기
- 정답 점수 집계 후 투자 점수와 합산 평가

### 💹 주식 기능 (StockDAO)
- DB에서 주식 정보 불러오기
- **주식 시세 자동 변동 시뮬레이션**
- 주식 **매수 / 매도 / 자산 조회** 기능
- ✅ CRUD 기능
  - 종목 추가 (중복 확인)
  - 종목 삭제
  - 종목 전체 조회

---

## 📂 프로젝트 구조 (MVC)
##### 📁 model/
##### ┣ 📄 Player.java
##### ┣ 📄 Stock.java
##### ┣ 📄 Quiz.java
##### ┣ 📄 QuizDAO.java
##### ┣ 📄 StockDAO.java
##### 📁 view/
##### ┗ 📄 GameView.java
##### 📁 controller/
##### ┗ 📄 MainController.java
##### 📁 util/
##### ┗ 📄 DBUtil.java
##### 📄 dbinfo.properties

---

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
