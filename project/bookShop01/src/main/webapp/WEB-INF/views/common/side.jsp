<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${contextPath}/resources/css/main_page.css" rel="stylesheet" type="text/css" media="screen">

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<style>
.dropdown-menu.show {
  /* show 상태일 때의 스타일 지정 */
  left: 1.5rem;
  text-align: center;
  /* 추가적인 스타일링 등 */
}
</style>

<c:choose>
<c:when test="${side_menu=='admin_mode' }">

<div class="shadow p-3 mb-5 bg-white rounded">
  <div class="row text-center">
    <div class="col-sm">
      <a class="nav-link text-dark" href="${contextPath}/admin/goods/adminGoodsMain.do">상품관리</a>
    </div>
    <div class="col-sm">
      <a class="nav-link text-dark" href="${contextPath}/admin/order/adminOrderMain.do">주문관리</a>
    </div>
    <div class="col-sm">
      <a class="nav-link text-dark" href="${contextPath}/admin/member/adminMemberMain.do">회원관리</a>
    </div>
    <div class="col-sm">
      <a class="nav-link text-dark" href="#">배송관리</a>
    </div>
    <div class="col-sm">
      <a class="nav-link text-dark" href="/admin/goods/adminBoardMain.do">게시판관리</a>
    </div>
  </div>
</div>
</c:when>
<c:when test="${side_menu=='my_page' }">
<div class="shadow p-3 mb-5 bg-white rounded">
  <div class="row text-center">
    <div class="col-sm">
      <a class="nav-link text-dark" href="${contextPath}/mypage/listMyOrderHistory.do">주문내역/배송조회</a>
    </div>
    <div class="col-sm">
      <a class="nav-link  text-dark" href="${contextPath}/mypage/listChangeMyOrderStatus.do">반품/교환 신청 및 조회</a>
    </div>
    <div class="col-sm">
      <a class="nav-link text-dark" href="${contextPath}/mypage/myDetailInfo.do">회원 정보 관리</a>
    </div>
    <div class="col-sm">
      <a class="nav-link text-dark" href="${contextPath}/mypage/myWithDrawal.do">회원 탈퇴</a>
    </div>
  </div>
</div>
</c:when>
<c:when test="${side_menu=='board_mode' }">
	<nav class="navbar navbar-expand-lg navbar-light row shadow p-3 mb-5 bg-white rounded">

    	<!-- 화환 -->
    	<div class="btn-group col">
    	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    	    화환
    	  </button>
    	  <div class="dropdown-menu" style="left:30px;">
    	    <a class="dropdown-item" href="${contextPath}/main/main2.do">근조화환</a>
    	    <a class="dropdown-item" href="${contextPath}/main/main3.do">축하화환</a>
    	  </div>
    	</div>

    	<!-- 관상식물 -->
    	<div class="btn-group col">
    	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    	    관상식물
    	  </button>
    	  <div class="dropdown-menu">
    	    <a class="dropdown-item" href="${contextPath}/main/main4.do">다육이</a>
    	    <a class="dropdown-item" href="${contextPath}/main/main5.do">동양란</a>
    	    <a class="dropdown-item" href="${contextPath}/main/main6.do">서양란</a>
    	  </div>
    	</div>

    	<!-- 기능성식물 -->
    	<div class="btn-group col">
    	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    	    기능성식물
    	  </button>
    	  <div class="dropdown-menu">
    	    <a class="dropdown-item" href="${contextPath}/main/main7.do">공기정화식물</a>
    	    <a class="dropdown-item" href="${contextPath}/main/main8.do">관엽</a>
    	  </div>
    	</div>

    	<!-- 꽃배달서비스 -->
    	<div class="btn-group col">
    	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    	    꽃배달서비스
    	  </button>
    	  <div class="dropdown-menu">
    	    <a class="dropdown-item" href="${contextPath}/main/main9.do">꽃다발</a>
    	  </div>
    	</div>

    	<!-- 부가제품 -->
    	<div class="btn-group col">
    	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    	    부가제품
    	  </button>
    	  <div class="dropdown-menu">
    	    <a class="dropdown-item" href="${contextPath}/main/main10.do">분재</a>
    	    <a class="dropdown-item" href="${contextPath}/main/main11.do">꽃박스</a>
    	  </div>
    	</div>


    	</nav>

</c:when>
<c:otherwise>
	<nav class="navbar navbar-expand-lg navbar-light row shadow p-3 mb-5 bg-white rounded">
	
	<!-- 화환 -->
	<div class="btn-group col">
	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	    화환
	  </button>
	  <div class="dropdown-menu">
	    <a class="dropdown-item" href="${contextPath}/main/main2.do">근조화환</a>
	    <a class="dropdown-item" href="${contextPath}/main/main3.do">축하화환</a>
	  </div>
	</div>
	
	<!-- 관상식물 -->
	<div class="btn-group col">
	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	    관상식물
	  </button>
	  <div class="dropdown-menu">
	    <a class="dropdown-item" href="${contextPath}/main/main4.do">다육이</a>
	    <a class="dropdown-item" href="${contextPath}/main/main5.do">동양란</a>
	    <a class="dropdown-item" href="${contextPath}/main/main6.do">서양란</a>
	  </div>	  
	</div>
          
	<!-- 기능성식물 -->
	<div class="btn-group col">
	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	    기능성식물
	  </button>
	  <div class="dropdown-menu">
	    <a class="dropdown-item" href="${contextPath}/main/main7.do">공기정화식물</a>
	    <a class="dropdown-item" href="${contextPath}/main/main8.do">관엽</a>
	  </div>	  
	</div>
	
	<!-- 꽃배달서비스 -->
	<div class="btn-group col">
	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	    꽃배달서비스
	  </button>
	  <div class="dropdown-menu">
	    <a class="dropdown-item" href="${contextPath}/main/main9.do">꽃다발</a>
	  </div>	  
	</div>
	
	<!-- 부가제품 -->
	<div class="btn-group col">
	  <button type="button" class="btn  dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	    부가제품
	  </button>
	  <div class="dropdown-menu">
	    <a class="dropdown-item" href="${contextPath}/main/main10.do">분재</a>
	    <a class="dropdown-item" href="${contextPath}/main/main11.do">꽃박스</a>
	  </div>	  
	</div>

	
			          
	</nav>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	
 </c:otherwise>
</c:choose>	

<div class="clear"></div>
</html>