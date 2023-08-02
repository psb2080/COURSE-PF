<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
<meta charset="UTF-8">
 <script src="//code.jquery.com/jquery-3.3.1.js"></script> 
<script type="text/javascript">

 function backToList(obj){
 obj.action="${contextPath}/board/listArticles.do";
 obj.submit();
 }

</script>
<title>답글쓰기 페이지</title>
</head>

<body>
 <h1>답글쓰기</h1>
  <form name="frmReply" method="post"  action="${contextPath}/board/addReply.do" enctype="multipart/form-data">
    <div class="form-group">
      <label>답글 작성자</label>
      <input type="text" class="form-control" value="${memberInfo.member_id }" aria-describedby="writer" readonly>

    </div>
    <div class="form-group">
      <label>답글을 작성하려는 본문의 번호</label>
      <input type="text" name="articleNO" value="${article.articleNO }" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly>
    </div>

    <div class="form-group">
          <label>답글의 내용</label>
          <textarea name="content" rows="10" cols="65" maxlength="4000"> </textarea>
       </div>
    <div class="form-group">
     <button class="btn btn-success" type="submit">답글쓰기</button>
     <button class="btn btn-success" type="button"  onClick="backToList(this.form)">취소</button>
    </div>
  </form>

</body>
</html>