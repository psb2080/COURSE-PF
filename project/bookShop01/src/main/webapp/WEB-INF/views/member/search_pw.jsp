<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	 isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html >
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<c:if test='${not empty message }'>
		<script>


		</script>
		</c:if>
	</head>
	<body>
	<H3>비밀번호 찾기</H3>
	<DIV id="detail_table">
		<form action="${contextPath}/member/search_pw.do" method="post">
	
		<TABLE>
			<TBODY>
				<TR class="dot_line">
					<TD class="fixed_join">아이디</TD>
					<TD><input name="member_id" type="text" size="20" /></TD>
				</TR>
				<TR class="solid_line">
					<TD class="fixed_join">이메일</TD>
					<TD><input name="emil1" type="text" size="20" id="email1" />
					
									@ <input  size="10px"  type="text" name="email2" id="email2" />
						
					  
						  <select name="email2" onChange=""	title="직접입력" id="email2">
									<option value="non">직접입력</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="naver.com">naver.com</option>
									<option value="yahoo.co.kr">yahoo.co.kr</option>
									<option value="hotmail.com">hotmail.com</option>
									<option value="paran.com">paran.com</option>
									<option value="nate.com">nate.com</option>
									<option value="google.com">google.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="empal.com">empal.com</option>
									<option value="korea.com">korea.com</option>
									<option value="freechal.com">freechal.com</option>  </TD>
				</TR>
			</TBODY>
		</TABLE>
			<br><br>
				<div class="form-group row">
					<button type="submit" id="submit" class="btn btn-primary btn-lg">비밀번호 찾기</button>
				</div>		
			
		</form>
	</div>
	</body>
</html>