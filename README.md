# 🏠 부동산 중개 서비스
선택한 지하철역과 가까운 거리에 있는 부동산 매물을 제공하는 서비스

## 프로젝트 기능 및 설계
* 회원가입
    * 일반 사용자와 중개인으로 구분하여 회원가입 해야한다.
    * 일반 사용자는 아이디, 비밀번호, 이름, 전화번호를 입력받으며, 아이디는 unique 해야한다.
    * 중개인은 아이디, 비밀번호, 이름, 전화번호, 주소를 입력받으며, 아이디는 unique 해야한다.
* 로그인
    * 서비스 이용을 위해 로그인을 해야한다.
    * 회원가입시 입력한 아이디와 비밀번호가 일치해야 로그인이 가능하다.
* 매물 등록
    * 매물 등록은 중개인만 할 수 있다.
    * 중개인은 매물의 사진, 등록번호, 거래유형(전세, 월세, 매매), 전용면적, 가격, 매물 정보, 위치 등을 작성할 수 있다.
      <br> $\to$ 사진 등록은 amazon s3 이용
      <br> $\to$ 위치는 위도/경도(좌표)와 텍스트(주소) 등록, 캐싱하여 저장
      <br> $\to$ 매물 정보에는 층, 주차 가능 여부, 엘리베이터 여부, 관리비, 옵션 정보 등 작성
* 매물 수정
    * 매물 수정은 중개인만 할 수 있다.
* 매물 삭제
    * 매물 삭제는 중개인만 할 수 있다.
* 매물 목록 조회
    * 매물 목록 조회는 로그인을 한 모든 사용자가 이용할 수 있다.
    * 지하철 역 검색어에 따라 목록 조회가 가능하다. <br> $\to$ 역 좌표 확인할 수 있는 Open API 사용
    * 선택한 역과 가까운 순으로 기본 정렬된다. <br> $\to$ 좌표 이용하여 거리 계산
    * 거래유형, 전용면적, 가격에 따라 필터 조회가 가능하다.
* 매물 조회
    * 매물 조회는 로그인을 한 모든 사용자가 이용할 수 있다.
    * 메모 작성이 가능하다.
    * 관심(버튼) 누르기가 가능하다.
    * 거래 가능, 거래 완료 등 거래 현황 확인이 가능하다.
* 메모 작성 매물 조회
    * 메모 작성 매물 조회는 로그인을 한 모든 사용자가 이용할 수 있다.
    * 매물 조회에서 메모 작성한 매물의 목록 조회가 가능하다.
* 관심 매물 조회
    * 관심 매물 조회는 로그인을 한 모든 사용자가 이용할 수 있다.
    * 매물 조회에서 관심(버튼) 누른 매물의 목록 조회가 가능하다.
* 최근 본 매물 조회
    * 최근 본 매물 조회는 로그인을 한 모든 사용자가 이용할 수 있다.
    * 매물 조회에서 조회한 매물의 목록 조회가 가능하다.
    * 최근 날짜 순으로 기본 정렬된다.
* 매물 검색 조회
    * 매물 검색 조회 로그인을 한 모든 사용자가 이용할 수 있다.
    * 매물의 등록번호를 이용하여 검색 조회가 가능하다.
* 매물 등록 알람
    * 매물 등록 알람은 로그인을 한 모든 사용자가 이용할 수 있다.
    * 관심 지하철역을 설정하여, 주변 매물이 등록되면 알람을 받아볼 수 있다. <br> $\to$ Websocket 사용
* 문의
    * 로그인을 한 일반 사용자가 선택한 매물의 중개인과 채팅이 가능하다. <br> $\to$ Websocket + stomp 사용      
 
## ERD
![real-estate](https://github.com/LeeHyejin14/real-estate/assets/57497745/9195c818-a8a5-4047-bf7c-301d6a36e059)

## Tech Stack
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
