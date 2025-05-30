🚀 프로젝트 실행 가이드

📦 [파일 다운로드]

1️⃣ https://github.com/pyodolski/smart-planner-project 접속<br>
2️⃣ 우측 상단 Code 버튼 클릭 → Download ZIP → 압축 해제

☕ [JDK 설치]

1️⃣ https://adoptium.net/temurin/releases/?os=any&arch=any&version=17 접속<br>
2️⃣ Windows용 JDK 17 다운로드 → 설치

🛠 [IntelliJ 설치]

1️⃣ https://www.jetbrains.com/idea/download/?section=windows 접속<br>
2️⃣ 다운로드 후 설치 → PATH 변수 업데이트 체크박스 선택<br>
3️⃣ 설치 후 재부팅

🗄 [MySQL 환경 설정]

1️⃣ https://dev.mysql.com/downloads/installer/ 접속<br>
2️⃣ MySQL Installer 다운로드 → Full (Server + Workbench) 설치<br>
3️⃣ 설치 중 Accounts and Roles에서 root 비밀번호 설정 (예: 1234)

🏗 [MySQL DB 설정]

1️⃣ MySQL Workbench 실행 → root 비밀번호 입력<br>
2️⃣ 좌측 중앙 Schemas → 우클릭 → Create Schema

이름: 예) planner<br>
Charset: utf8mb4<br>
Collation: utf8mb4_general_ci → Apply<br>
3️⃣ Query 창에서 실행:<br>
use planner;<br>
💻 [프로젝트 IntelliJ에서 열기]

1️⃣ IntelliJ 실행 → 압축 해제한 smart-planner-project-main/planner 폴더 열기<br>
2️⃣ Gradle 프로젝트로 선택 (다운로드 혹은 선택 후 Gradle 로드 대기)<br>

📝 [application.yml 수정]

경로: planner/src/resources/application.yml

예시:<br>
url: jdbc:mysql://localhost:3306/planner<br>
username: root<br>
password: 1234<br>
👉 여기서 planner는 생성한 스키마 이름, 1234는 MySQL root 비밀번호

▶ [앱 실행]

1️⃣ IntelliJ에서 planner/src/main/java/com/project/planner/PlannerApplication 실행<br>
2️⃣ 크롬 등 브라우저 실행<br>
3️⃣ 접속:<br>

http://localhost:8080

[사용 공공 API 목록]<br>
1. 기상청-날씨중기예보 : https://www.data.go.kr/iim/api/selectAPIAcountView.do<br>
2. 카카오 Map : https://apis.map.kakao.com/<br>
3. 식품의약품안전처_식품영양성분 : https://www.data.go.kr/iim/api/selectAPIAcountView.do<br>
4. 구글 캘린더 API (공휴일) : https://console.cloud.google.com/apis/library/calendar-json.googleapis.com?hl=ko&pli=1&inv=1&invt=AbynaQ

[개발 기간] 
  25.03.18 ~ 25.05.20 (약 2개월)

[history]  
  25.03.18 : 프로젝트 생성 및 yml 설정
  
  25.03.20 : 회원가입 및 로그인 기능 추가
  
  25.03.21 : 회원가입 팝업 오류 수정
  
  25.03.24 : 날씨중기예보 기능 추가

  25.04.01 : 식단 API 호출 기능 추가 

  20.04.06 : 일정 관리 기능 추가
  
  25.04.10 : 캘린더 및 공휴일 API 호출 기능 추가

  20.04.25 : 카카오 map API 호출 기능 추가

  20.04.27 : 즐겨찾기(위치) 추가 기능 추가

  25.05.02 : 분석 단계 문서 작성

  25.05.07 : 오류 수정

  25.05.20 : 프로젝트 구현 완료

  25.05.21 : 설계 단계 문서 작성
