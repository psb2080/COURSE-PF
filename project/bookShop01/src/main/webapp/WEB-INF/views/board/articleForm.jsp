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
<title>글쓰기창</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function readURL(input) {
      if (input.files && input.files[0]) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#preview').attr('src', e.target.result);
          }
         reader.readAsDataURL(input.files[0]);
      }
  }  
  function backToList(obj){
    obj.action="${contextPath}/board/listArticles.do?num=1";
    obj.submit();
  }
  
  var cnt=1;
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
  }  
</script>
 <title>글쓰기창</title>
</head>
<body>

<h1 style="text-align:center">고객센터</h1><br><br>
<div style="margin: 0 auto;	max-width:700px;">
  <form name="articleForm" method="post"   action="${contextPath}/board/addNewArticle.do"   enctype="multipart/form-data">
  <input type="hidden" name="num" value="1">
    <div class="input-group input-group-lg text-center">
	  <div class="input-group-prepend">
	    <span class="input-group-text" style="width:100px">Writer</span>
	  </div>
	  <input type="text" class="form-control" value="${memberInfo.member_id }" 	aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" readonly >
	</div>
	<br>
	<div class="input-group input-group-lg">
	  <div class="input-group-prepend">
	    <span class="input-group-text" style="width:100px">Title</span>
	  </div>
	  <input type="text" name="title" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
	</div>
	<br>
	<div class="input-group">
	  <div class="input-group-prepend">
	    <span class="input-group-text"  style="width:100px">content</span>
	  </div>
	  <textarea class="form-control" aria-label="With textarea" name="content" rows="10" cols="65" maxlength="4000"></textarea>
	</div><br><br>
	
	<div class="input-group mb-3">
	  <div class="input-group-prepend">
	    <span class="input-group-text" id="inputGroupFileAddon01" style="width:100px">Upload</span>
	  </div>
	  <div class="custom-file">
	    <input type="file" class="custom-file-input" name="imageFileName" onchange="readURL(this);" aria-describedby="inputGroupFileAddon01">
	    <label class="custom-file-label" for="inputGroupFile01">click to Choose file</label>
	  </div>
	</div><br>
	<div>
		<img id="preview" src="#"   width=300 height=200/>
	</div><br>
	
	<div>
	       <input class="btn btn-primary float-left" type= button value="목록보기" onClick="backToList(this.form)" />
		   <input class="btn btn-primary float-right" type="submit" value="글 쓰기">
	       <div id="d_file">
	       </div>
	</div><br><br>
	
  </form>
  	</div>
</body>
</html>