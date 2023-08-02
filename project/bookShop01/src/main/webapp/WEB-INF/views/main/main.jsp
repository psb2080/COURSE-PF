<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"	isELIgnored="false"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${contextPath}/resources/css/main_page.css" rel="stylesheet" type="text/css" media="screen">

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>
<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner" style="border-radius: 7rem; width: 100%">
    <div class="carousel-item active">
      <video class="d-block w-100" src="../resources/image/video3.mp4" autoplay muted></video>
    </div>
    <div class="carousel-item">
      <video class="d-block w-100" src="../resources/image/video2.mp4" autoplay muted></video>
    </div>
    <div class="carousel-item">
      <video class="d-block w-100" src="../resources/image/video1.mp4" autoplay muted></video>
    </div>
    <div class="carousel-item">
          <video class="d-block w-100" src="../resources/image/video4.mp4" autoplay muted></video>
    </div>
    <div class="carousel-item">
      <video class="d-block w-100" src="../resources/image/video5.mp4" autoplay muted></video>
    </div>
  </div>
   <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
     <span class="carousel-control-prev-icon" aria-hidden="true"></span>
     <span class="sr-only">Previous</span>
   </a>
   <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
     <span class="carousel-control-next-icon" aria-hidden="true"></span>
     <span class="sr-only">Next</span>
   </a>
</div>

<script>
  document.addEventListener("DOMContentLoaded", function() {
    var carouselNextBtn = document.getElementById("carouselNextBtn");
    var carouselVideos = document.querySelectorAll("#carouselExampleControls video");
    var currentVideoIndex = 0;

    function playNextVideo() {
      if (currentVideoIndex === carouselVideos.length - 1) {
        // 마지막 동영상 재생 완료 시 다음 버튼 클릭
        carouselNextBtn.click();
      } else {
        // 다음 동영상 재생
        carouselVideos[currentVideoIndex + 1].play();
      }
    }

    // 동영상 재생 완료 이벤트 감지
    carouselVideos.forEach(function(video) {
      video.addEventListener("ended", playNextVideo);
    });
  });
</script>



<br><br>
<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
    <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="${contextPath}/resources/image/banner2.png" class="d-block w-100" style="height:440px">
     <!--  <div class="carousel-caption d-none d-md-block">
        <h5>First slide label</h5>
        <p>Some representative placeholder content for the first slide.</p>
      </div> -->
    </div>
    <div class="carousel-item">
      <img src="${contextPath}/resources/image/banner1.png" class="d-block w-100" style="height:440px">
      <!-- <div class="carousel-caption d-none d-md-block">
        <h5>Second slide label</h5>
        <p>Some representative placeholder content for the second slide.</p>
      </div> -->
    </div>
    <div class="carousel-item">
      <img src="${contextPath}/resources/image/banner3.png" class="d-block w-100" style="height:440px">
      <!-- <div class="carousel-caption d-none d-md-block">
        <h5>Third slide label</h5>
        <p>Some representative placeholder content for the third slide.</p>
      </div>  사진 설명 추가 부분-->
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-target="#carouselExampleCaptions" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-target="#carouselExampleCaptions" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </button>
</div>
<br>



<br>
<div class="main_book">
   <c:set  var="goods_count" value="0" />
	<h3 class="font-italic">best-seller</h3>
	<c:forEach var="item" items="${goodsMap.bestseller }">
	   <c:set  var="goods_count" value="${goods_count+1 }" />
		<div class="book">
			<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
                <img width="150" height="200"
				     src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
			</a> 

			<div class="title">${item.goods_title }</div>
			<div class="price">
		  	   <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
		          ${goods_price}원
			</div>
		</div>
  </c:forEach>
</div>
<div class="clear"></div>



<div class="main_book" >
<c:set  var="goods_count" value="0" />
	<h3 class="font-italic">new things</h3>
	<c:forEach var="item" items="${goodsMap.newbook }" >
	   <c:set  var="goods_count" value="${goods_count+1 }" />
		<div class="book">
		  <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
            <img width="150" height="200"
				src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
	      </a>

		<div class="title">${item.goods_title }</div>
		<div class="price">
		    <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
		       ${goods_price}원
		  </div>
	</div>
	</c:forEach>
</div>

<div class="clear"></div>

<div class="main_book" >
<c:set  var="goods_count" value="0" />
	<h3 class="font-italic">steady-seller</h3>
	<c:forEach var="item" items="${goodsMap.steadyseller }" >
	   <c:set  var="goods_count" value="${goods_count+1 }" />
		<div class="book">
		  <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
	        <img width="121" height="154"
            				src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
	      </a>

		<div class="title">${item.goods_title }</div>
		<div class="price">
		    <fmt:formatNumber  value="${item.goods_price}" type="number" var="goods_price" />
		       ${goods_price}원
		  </div>
	</div>
	</c:forEach>
	</div>


   
   