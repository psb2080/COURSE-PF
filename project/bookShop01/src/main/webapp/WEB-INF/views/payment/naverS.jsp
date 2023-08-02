<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
</head>
<BODY>
	<H1>네이버성공</H1>
	
	<table>
		<TBODY>
			<TR class="dot_line">
				<TD class="fixed_join">결제금액</TD>
				<TD>
				   <fmt:formatNumber value="${naverData.amount }" pattern="#,###" />원
			    </TD>
			</TR>
			<TR class="dot_line">
				<TD class="fixed_join">승인일시</TD>
				<TD>
				    <fmt:parseDate value="${naverData.authDateTime}" var="dateValue" pattern="yyyyMMddHHmmss"/>
				    <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm"/>
			    </TD>
			</TR>
			<TR class="dot_line">
				<TD class="fixed_join">결제타입</TD>
				<TD>
					<c:if test="${naverData.type eq 'NAVER_POINT'}">
						네이버 포인트
					</c:if>
					<c:if test="${naverData.type eq 'NAVER_CARD'}">
						네이버 카드
					</c:if>
			    </TD>
			</TR>
		</TBODY>
	</table>
</BODY>