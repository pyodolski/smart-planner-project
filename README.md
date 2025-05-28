🚀 프로젝트 실행 가이드

📦 [파일 다운로드]

1️⃣ https://github.com/pyodolski/smart-planner-project 접속
2️⃣ 우측 상단 Code 버튼 클릭 → Download ZIP → 압축 해제

☕ [JDK 설치]

1️⃣ https://adoptium.net/temurin/releases/?os=any&arch=any&version=17 접속
2️⃣ Windows용 JDK 17 다운로드 → 설치

🛠 [IntelliJ 설치]

1️⃣ https://www.jetbrains.com/idea/download/?section=windows 접속
2️⃣ 다운로드 후 설치 → PATH 변수 업데이트 체크박스 선택
3️⃣ 설치 후 재부팅

🗄 [MySQL 환경 설정]

1️⃣ https://dev.mysql.com/downloads/installer/ 접속
2️⃣ MySQL Installer 다운로드 → Full (Server + Workbench) 설치
3️⃣ 설치 중 Accounts and Roles에서 root 비밀번호 설정 (예: 1234)

🏗 [MySQL DB 설정]

1️⃣ MySQL Workbench 실행 → root 비밀번호 입력
2️⃣ 좌측 중앙 Schemas → 우클릭 → Create Schema

이름: 예) planner
Charset: utf8mb4
Collation: utf8mb4_general_ci → Apply
3️⃣ Query 창에서 실행:
use planner;
💻 [프로젝트 IntelliJ에서 열기]

1️⃣ IntelliJ 실행 → 압축 해제한 smart-planner-project-main 폴더 열기
2️⃣ Gradle 프로젝트로 선택 (또는 다운로드 후 Gradle 로드 대기)

📝 [application.yml 수정]

경로: planner/src/resources/application.yml

예시:
url: jdbc:mysql://localhost:3306/planner
username: root
password: 1234
👉 여기서 planner는 생성한 스키마 이름, 1234는 MySQL root 비밀번호

▶ [앱 실행]

1️⃣ IntelliJ에서 planner/src/main/java/com/project/planner/PlannerApplication 실행
2️⃣ 크롬 등 브라우저 실행
3️⃣ 접속:

http://localhost:8080

[개발 기간] 
  25.03.18 ~ 

[history]  
  25.03.18 : 프로젝트 생성 및 yml 설정
  
  25.03.20 : 회원가입 및 로그인 기능 추가
  
  25.03.21 : 회원가입 팝업 오류 수정
  
  25.03.24 : 날씨중기예보 기능 추가

  25.04.01 : 식단 API 호출 기능 추가 

  25.05.02 : 분석 단계
