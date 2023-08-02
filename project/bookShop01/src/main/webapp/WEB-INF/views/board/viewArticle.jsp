<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%-- 
<c:set var="article"  value="${articleMap.article}"  />
<c:set var="imageFileList"  value="${articleMap.imageFileList}"  />
 --%>
<%
  request.setCharacterEncoding("UTF-8");
%> 

<head>
   <meta charset="UTF-8">
   <title>글보기</title>
   <style>
     #tr_file_upload{
       display:none;
     }
     #tr_btn_modify{
       display:none;
     }
   
   </style>
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
   
    
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


   <script type="text/javascript" >
     function backToList(obj){
	    obj.action="${contextPath}/board/listArticles.do";
	    obj.submit();
     }
 
	 function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_imageFileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_file_upload").style.display="block";
	 }
	 
	 function fn_modify_article(obj){
		 obj.action="${contextPath}/board/modArticle.do?num=${num}";
		 obj.submit();
	 }
	 
	 function fn_remove_article(url,articleNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","articleNO");
	     articleNOInput.setAttribute("value", articleNO);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
	     form.submit();
	 
	 }
	 
	 function fn_reply_form(url, articleNO){
		 var form = document.createElement("form");
		 form.setAttribute("method", "post");
		 form.setAttribute("action", url);
	     var articleNOInput = document.createElement("input");
	     articleNOInput.setAttribute("type","hidden");
	     articleNOInput.setAttribute("name","articleNO");
	     articleNOInput.setAttribute("value", articleNO);
		 
	     form.appendChild(articleNOInput);
	     document.body.appendChild(form);
		 form.submit();
	 }
	 
	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }  
 </script>
</head>
<body>

<div style="margin: 0 auto;	max-width:700px;">
 <form name="frmArticle" method="post"  action="${contextPath}" enctype="multipart/form-data">
	<div class="input-group input-group-lg">
	  <div class="input-group-prepend">
	    <span class="input-group-text" style="width:100px">Number</span>
	  </div>
	  <input type="text" value="${article.articleNO }" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled>
	  <input type="hidden" name="articleNO" value="${article.articleNO}">
	</div><br>
    <div class="input-group input-group-lg">
	  <div class="input-group-prepend">
	    <span class="input-group-text" style="width:100px">Writer</span>
	  </div>
	  <input type="text" class="form-control" value="${article.member_id }" name="writer" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled >
	</div>
	<br>
	<div class="input-group input-group-lg">
	  <div class="input-group-prepend">
	    <span class="input-group-text" style="width:100px">Title</span>
	  </div>
	  <input type="text" value="${article.title }"  name="title"  id="i_title" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled>
	</div>
	<br>
	<div class="input-group input-group-lg">
	  <div class="input-group-prepend">
	    <span class="input-group-text"  style="width:100px">content</span>
	  </div>
	  <textarea class="form-control" aria-label="With textarea" name="content"  id="i_content" rows="10" cols="30" maxlength="800" disabled>${article.content }</textarea>
	</div><br>
	<div class="input-group input-group-lg">
	  <div class="input-group-prepend">
	    <span class="input-group-text" style="width:100px">Date</span>
	  </div>
	  <input type="text" value="<fmt:formatDate value="${article.writeDate}" />" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled>
	</div>
	<div class="shadow p-3 mb-5 bg-white rounded">

                <label>REPLY    :   </label>
                <div class="input-group input-group-lg">
    	            <div class="input-group-prepend">
    		            <input style="width: 100px" type="text" name="member_id" value="작성자" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled>
    		            <input type="text" name="content"  class="form-control" value="내용" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled/>
    	            </div>
                </div><br>

                <div id="replyContainer">
                    <%-- 기존에 저장된 reply.content를 보여주는 input 태그들 --%>
                    <c:choose>
                      <c:when test="${empty reply}">
                        <p>아직 댓글이 없습니다.</p>
                      </c:when>
                      <c:otherwise>
                        <c:forEach var="reply" items="${reply}">
                        <div class="input-group input-group-lg">
                        	  <div class="input-group-prepend">
                        	   <input style="width: 100px" type="text" name="member_id" value="${reply.member_id}" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled>
                        	   <input type="text" name="content" value="${reply.content}" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" disabled/>

                        	</div>
                        	</div>
                        	<br>

                        </c:forEach>
                      </c:otherwise>
                    </c:choose>
                  </div>
              </div>
               <br>



	<table style="width:700px">
	<c:choose> 
	  <c:when test="${not empty article.imageFileName && article.imageFileName!='null' }">
	   	<tr>
		   <td>
		   	<div class="input-group mb-3">
           	  <div class="input-group-prepend">
           	    <span class="input-group-text" id="inputGroupFileAddon01" style="width:100px">Upload</span>
           	  </div>
           	  <div class="custom-file">
           	    <input type="file" class="custom-file-input" value="${article.imageFileName }" aria-describedby="inputGroupFileAddon01" name="imageFileName " id="i_imageFileName">
           	    <label class="custom-file-label" for="inputGroupFile01"　   disabled   onchange="readURL(this);"　>click to Choose file</label>

           	   </div>
           	</div><br>
				<img src="${contextPath}/downloadBoard.do?articleNO=${article.articleNO}&imageFileName=${article.imageFileName}" id="preview" style="width:300px; height:200px"><br>
				<br>
           	</td>
		  </tr>
		 </c:when>
		 <c:otherwise>
		    <tr id="tr_file_upload">
			   <td>
			    <div class="input-group mb-3">
	              <div class="input-group-prepend">
	                <span class="input-group-text" id="inputGroupFileAddon01" style="width:100px">Upload</span>
	              </div>
	              <div class="custom-file">
	                <input type="file" class="custom-file-input"  value="${article.imageFileName }" aria-describedby="inputGroupFileAddon01">
	                <label class="custom-file-label" for="inputGroupFile01"　name="imageFileName " id="i_imageFileName"   disabled   onchange="readURL(this);"　>click to Choose file</label>
	              </div>
	            </div><br>
                <div>
                    <img id="preview" src="#"/>
                </div><br>
			   </td>
		  </tr>
		 </c:otherwise>
	 </c:choose>
	  <tr id="tr_btn_modify"  align="center" class="float-right">
	   <td colspan="2">
	       <input class="btn btn-success" type=button value="수정반영하기" onClick="fn_modify_article(frmArticle)"  >
           <input class="btn btn-success" type=button value="취소"  onClick="backToList(frmArticle)"><br><br>
	   </td>
	  </tr>
	  <br>
	  <tr>
	   <td colspan="4" align="center">
	       <c:if test="${memberInfo.member_id == article.member_id }">
		      <input class="btn btn-success" type=button value="수정하기" onClick="fn_enable(this.form)">
		      <input class="btn btn-success" type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
		    </c:if>
		    <input class="btn btn-success" type=button value="리스트로 돌아가기"  onClick="location.href='${contextPath}/board/listArticles.do?num=${num }&articleNO=${article.articleNO}${page.searchTypeKeyword}'">
		    <input class="btn btn-success" type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
		     
	   </td>
	  </tr>
	</table>
　</form>
</div>
</body>
</html>