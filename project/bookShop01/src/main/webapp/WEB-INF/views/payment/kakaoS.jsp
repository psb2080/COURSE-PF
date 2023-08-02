<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
</head>
<BODY>

  
	<H1>카카오성공</H1>
	
	<table>
		<TBODY>
			<TR class="dot_line">
				<TD class="fixed_join">결제금액</TD>
				<TD>
				   <fmt:formatNumber value="${kakaoData.amount }" pattern="#,###" />원
			    </TD>
			</TR>
			<TR class="dot_line">
				<TD class="fixed_join">승인일시</TD>
				<TD>
				    <fmt:parseDate value="${kakaoData.authDateTime}" var="dateValue" pattern="yyyyMMddHHmmss"/>
				    <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm"/>
			    </TD>
			</TR>
			<TR class="dot_line">
				<TD class="fixed_join">결제타입</TD>
				<TD>
					<c:if test="${kakaoData.type eq 'KAKAO_MONEY'}">
						카카오 머니
					</c:if>
					<c:if test="${kakaoData.type eq 'KAKAO_CARD'}">
						카카오 카드
					</c:if>
			    </TD>
			</TR>
		</TBODY>
	</table>
		
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</BODY>
<DIV class="clear"></DIV>		
