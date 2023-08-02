<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${contextPath}/resources/css/subPage.css" rel="stylesheet" type="text/css" media="screen">

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<c:if test='${not empty message }'>
<script>
window.onload=function()
{
  result();
}

function result(){
	alert("아이디나  비밀번호가 틀립니다. 다시 로그인해주세요");
}
</script>
</c:if>
</head>
<body>
<div class="pageWrap">
                <form method="POST" action="${contextPath}/member/login.do">
                    <input type="hidden" name="_token" value="oM00jfHYsNLRFmpJ2NirOyWTyEUIJOBRfSCoThnw">
                    <BR>
                    <div class="shadow p-3 mb-5 bg-white rounded text-secondary text-center display-4 font-italic">STEPLANT</div>
					    <br><br>
                        <div class="form-group col-md-6">
                            <input id="userid" type="text"
                                   class="form-control"
                                   name="member_id" value="" required autocomplete="userid" autofocus
                                   placeholder="아이디">
                        </div>

                        <div class="form-group col-md-6">
                            <input id="password" type="password"
                                   class="form-control "
                                   name="member_pw" required autocomplete="current-password"
                                   placeholder="패스워드">
                        </div><br>
                        <div class="row login-options">
                            <div class="col-6 text-left">
                                <div class="custom-control custom-checkbox">
                                    <input class="custom-control-input" type="checkbox" name="remember" id="remember" >
                                    <label class="custom-control-label text-dark" for="remember" >아이디 저장</label>
                                </div>
                            </div>
                            <div class="col-6 text-right">
                                <a href="${contextPath}/member/search_id.do" class="text-success" >아이디찾기</a> |
		   						<a href="${contextPath}/member/search_pw.do" class="text-success" >비밀번호찾기</a> 
                            </div>
                        </div>
                        <br>

                    <div class="row">
                        <div class="form-group col-md-3">
                            <button type="submit" class="form-control btn btn-success">로그인</button>
                        </div>
                        <div class="form-group col-md-3">
                        </div>
                        <div class="form-group col-md-3">
                        </div>
                        <div class="form-group col-md-3">
                            <a href="${contextPath}/member/memberForm.do" class="btn btn-success  active" role="button" >회원가입</a>
					</div>



					   
	</form>		
</body>
</html>