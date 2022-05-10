# lavraStroe
2021-SSD

## 2021_1학기 소프트웨어 시스템 개발 : 웹 개발 팀 프로젝트 

💙 기간: 2021.03.04 ~ 2021.06.28

💙 주제: Spring MVC를 기반으로 개발한 액세서리 쇼핑몰(일반 쇼핑, 개인거래, 공동구매)_Lavra

### Use Case

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbKTOAV%2FbtrBLVBn9My%2FdX712of6KNhIrKOxNv31F0%2Fimg.jpg">


### Database Schema

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcBFA5g%2FbtrBIge0zS6%2FZU9Z3bHfXLsuFMw5XKT5i1%2Fimg.jpg">


### 실행 화면

<img src="https://blog.kakaocdn.net/dn/N1mZF/btrBLGdCoU3/nDC26ukhV8KFlW0xtuOTqk/img.gif" >

### 핵심 기능 설명 

:cherry_blossom: **회원가입 및 로그인**

로그인 페이지에서 회원가입 페이지로의 이동 및 로그인이 가능하다. 

회원가입시 사용자 정보가 DB에 저장되며, 이후 로그인이 가능하다. 

회원가입시 작성했던 사용자 정보 및 비밀번호는 mypage에서 수정이 가능하다. 

:cherry_blossom: **일반 쇼핑(액세서리)**

일반적인 쇼핑몰과 같이 항시 판매하는 상품들을 보고, 구매할 수 있다. 

각 상품 종류에 따라 목록을 가지며, 페이징 기능을 가지고 있다. 

상품 목록은 각 카테고리별로 인기순, 높은 가격순, 낮은 가격순의 정렬이 가능하다. 또한 상품 상세 페이지로 들어가지 않고 상품 목록 페이지에서 비동기적으로 위시리스트 추가가 가능하다. 

상품 목록 페이지에서 상품 상세 페이지로 이동하여 상품 정보를 조회하고, 구매 및 위시/카트에 저장이 가능하다. 또한 품절된 상품의 경우 카트에 담기지 않으며, 구매 버튼이 비활성화 된다. 

:cherry_blossom: **개인 거리**

개인 거래는 사용자가 등록한 상품을 다른 사용자가 구매할 수 있는 페이지이다. 
각 상품 종류에 따라 목록을 가지며, 페이징 기능을 가지고 있다. 

상품 목록에서 상품 상세 페이지로 이동하여 정보를 조회하고 구매 및 위시에 저장이 가능하다. 

또한 개인 거래는 크게 개인거래 상품 등록과 구매로 기능이 나뉜다. 
상품 등록과 관리는 햄버거 메뉴의 Sell Item에서 가능하며, 타 사용자가 개인 거래 상품을 구매할 시, 구매 버튼이 비활성화 된다. 

:cherry_blossom: **공동 구매**

공동 구매는 관리자가 상품을 등록할 시 목표 금액과 마감일을 설정해두면, 사용자들은 각 상품을 구매 신청할 수 있다. 마감일 날 목표금액을 달성할 경우 구매가 완료된다. 

공동 구매 탭에서 상품 목록은 기본순, 높은 가격순, 낮은 가격 순으로 정렬이 가능하고, 상품 목록에서 상품 상세 페이지로 들어가 정보를 조회하고 구매 및 위시에 저장이 가능하다.

:cherry_blossom: **위시리스트/카트**

각 상품 목록 페이지(액세서리/개인거래/공동구매)에서 상품 이미지의 하트 마크를 클릭함으로써 위시리스트에 추가되고, 하트 마크를 해제할 경우 위시리스트에서 제거된다. 또한 각 상품 상세페이지를 통해서도 위시리스트에 추가할 수 있다. 전체 위시리스트 관리는 햄버거 메뉴의 Wishlist에서 가능하다. 

카트 기능의 경우 액세서리 탭인 일반 쇼핑에서만 이용 가능한 기능으로서, 상품 상세 페이지에서 상품의 개수를 지정한 후 카트에 담을 수 있다. 카트 관리는 햄버거 메뉴의 Cart에서 가능하다. Cart 페이지에서 비동기적으로 수량 수정이 가능하며, 체크 박스를 이용하여 여러 물건을 한번에 구매할 수 있다. 

:cherry_blossom: **리뷰**

일반 쇼핑과 공동 구매에 한해서 이용할 수 있는 기능이다. 리뷰는 모든 이가 볼 수 있지만, 작성은 해당 상품을 구매한 이력이 있는 사용자로 제한한다. 

자신이 작성한 리뷰는 언제든지 삭제가 가능하다. 

### 사용 스택

Spring Boot, Spring Data JPA, Spring Sheduler,

MyBatis, Oracle DB, ERWIN, Java 1.8

Bootstrap, Rest API, AJAX

⭐ 저는 spring sheduler를 제외한 나머지 스택을 사용했습니다. 

### 협업 방식

git을 통해서 코드를 올려 관리했으며, Discord를 통해 매주 회의를 하였습니다.

매주 회의에서는 다음주의 개발 목표를 설정하고, 이전에 개발한 것에 대한 결과 공유 및 피드백을 받고, 어려운 점에 대한 조언을 구하였습니다. 

### 프로젝트 구조 

src / main / java

- com.example.lavrastore
- com.example.lavrastore.controller
- com.example.lavrastore.dao
- com.example.lavrastore.dao.mybatis
- com.example.lavrastore.dao.mybatis.mapper
- com.example.lavrastore.data.jpa
- com.example.lavrastore.domain
- com.example.lavrastore.service

src / main / resources

* com / example / lavrastore / dao / mybatis / mapper (.xml)
* db / oracle
* static
  * images
  * js
  * style
* application.properties

src / main

* webapp
  * images
    * upload
  * WEB-INF
    * jsp
    * lib

pom.xml

### 역할 
* [강민주](https://github.com/Minjoo-kang123) : 위시리스트, 로그인, 회원가입, 마이페이지, 필요 이미지 업로드
* [안시현](https://github.com/ash0520) : 개인거래( 목록, 상세페이지, 등록, 구매)
* [윤예진](https://github.com/yejin-yun) : 일반 쇼핑( 목록, 상세페이지, 구매 ), 위시리스트(+하트 버튼), 카트 , 메인페이지 등
* [이유정](https://github.com/You-jeong136) : 공동구매(목록, 상세페이지, 구매), 리뷰, 주문 내역 조회 , 개인 거래 ( 구매, 등록 ) 등 


