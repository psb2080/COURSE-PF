<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${contextPath}/resources/css/subPage.css" rel="stylesheet" type="text/css" media="screen">
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
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
<div class="text-center">
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
    <c:forEach  var="article" items="${articlesList }" >
     <tr align="center">
	<td width="5%">${article.articleNO}</td>
	<td width="10%">${article.member_id }</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
	   <c:choose>
	      <c:when test='${article.level > 1 }'>  
	         <c:forEach begin="1" end="${article.level }" step="1">
	              <span style="padding-left:20px"></span>
	         </c:forEach>
	         <span style="font-size:12px;">[답변]</span>
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?num=${select}&articleNO=${article.articleNO}${page.searchTypeKeyword}">${article.title}</a>
	          </c:when>
	          <c:otherwise>
	            <a class='cls1' href="${contextPath}/board/viewArticle.do?num=${select}&articleNO=${article.articleNO}${page.searchTypeKeyword}">${article.title }</a>
	          </c:otherwise>
	        </c:choose>
	  </td>
	  <td  width="10%">${article.writeDate}</td> 
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>


<div>
	<c:if test="${page.prev}">
		<span>[ <a href="/board/listArticles.do?num=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]</span>
	</c:if>
	
	<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
		<span>
			<c:if test="${select != num}">
				<a href="/board/listArticles.do?num=${num}${page.searchTypeKeyword}">${num}</a> 
			</c:if>
			
			<c:if test="${select == num}">
		   		<b>${num}</b>
		    </c:if>
		</span>
	</c:forEach>
	
	<c:if test="${page.next}">
		<span>[ <a href="/board/listArticles.do?num=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]</span>
	</c:if>
</div><br>


<div>
 <select name="searchType">
     <option value="title" <c:if test="${searchType eq 'title'}">selected</c:if>>제목</option>
        <option value="content" <c:if test="${searchType eq 'content'}">selected</c:if>>내용</option>
     <option value="title_content" <c:if test="${searchType eq 'title_content'}">selected</c:if>>제목+내용</option>
     <option value="writer" <c:if test="${searchType eq 'writer'}">selected</c:if>>작성자</option>
 </select>
 
 <input type="text" name="keyword" value="${keyword}"/>
 
 <button class="btn-sm btn-outline-secondary" type="button" id="searchBtn">검색</button>
</div>
</div>

<script>

//클릭 이벤트와 엔터 키 이벤트를 모두 처리하는 함수
function handleSearch() {
	let searchType = document.getElementsByName("searchType")[0].value;
	let keyword =  document.getElementsByName("keyword")[0].value;

	location.href = "/board/listArticles.do?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
}

// 클릭 이벤트 처리
document.getElementById("searchBtn").onclick = handleSearch;

// 엔터 키 이벤트 처리
document.addEventListener("keydown", function(event) {
	if (event.key === "Enter") {
			handleSearch();
  	}
});
</script>

<a  class="cls1 btn btn-primary float-right" role="button" href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/articleForm.do?num=${select}', 
                                                    '${contextPath}/member/loginForm.do')">글 게시</a>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>