<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${contextPath}/resources/css/subPage.css" rel="stylesheet" type="text/css" media="screen">
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>	
<!DOCTYPE html>
<meta charset="utf-8">
  
<head>

</script>    
</head>
<BODY>

<form action="${contextPath}/admin/goods/addNewGoods.do" method="post"  enctype="multipart/form-data">
		<h1>관리자 게시판 관리</h1>

<div class="tab_container">

	<div class="tab_container" id="container">
		<ul class="tabs">
			<li><a href="#tab1">고객센터 관리</a></li>
			<li><a href="#tab2">공지사항 관리</a></li>
			<li><a href="#tab3">QnA 관리</a></li>
		</ul>
		
		<!-- 첫번째 탭 -->
		<div class="tab_container">
			<div class="tab_content" id="tab1">
				
				<!-- listArticles.jsp 따온내용 -->
				
				 <style>
				   .cls1 {text-decoration:none;}
				   .cls2{text-align:center; font-size:30px;}
				  </style>
				  <meta charset="UTF-8">
				  <title>글목록</title>
				</head>
				<script>
					function fn_articleForm(isLogOn,articleForm,loginForm){
					  if(isLogOn != '' && isLogOn != 'false'){
					    location.href=articleForm;
					  }else{
					    alert("로그인 후 글쓰기가 가능합니다.")
					    location.href=loginForm+'?action=/board/articleForm.do';
					  }
					}
				</script>
				<body>
				
				<table class="table table-hover">
				  <thead>
				    <tr>
				      <th scope="col">Number</th>
				      <th scope="col">WRITER</th>
				      <th scope="col">TITLE</th>
				      <th scope="col">DATE</th>
				    </tr>
				  </thead>
				  <tbody>
				<c:choose>
				  <c:when test="${articlesList ==null }" >
				    <tr  height="10">
				      <td colspan="4">
				         <p align="center">
				            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
				        </p>
				      </td>  
				    </tr>
				  </c:when>
				  <c:when test="${articlesList !=null }" >
				    <c:forEach  var="article" items="${articlesList }" varStatus="articleNum" >
				     <tr align="center">
					<td width="5%">${articleNum.count}</td>
					<td width="10%">${article.member_id }</td>
					<td align='left'  width="35%">
					  <span style="padding-right:30px"></span>
					   <c:choose>
					      <c:when test='${article.level > 1 }'>  
					         <c:forEach begin="1" end="${article.level }" step="1">
					              <span style="padding-left:20px"></span>
					         </c:forEach>
					         <span style="font-size:12px;">[답변]</span>
				                   <a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
					          </c:when>
					          <c:otherwise>
					            <a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
					          </c:otherwise>
					        </c:choose>
					  </td>
					  <td  width="10%">${article.writeDate}</td> 
					</tr>
				    </c:forEach>
				     </c:when>
				    </c:choose>
				</table>
	
				
				<!-- listArticles.jsp 따온내용 -->			
			
			</div>
		</div>
		
		<!-- 두번째 탭 -->
		<div class="tab_container">
			<div class="tab_content" id="tab2">
			</div>
		</div>
		
		<!-- 세번째 탭 -->
		<div class="tab_container">
			<div class="tab_content" id="tab3">
			</div>
		</div>
		
	</div>
</div>

</form>	 