<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"  
%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="queryString2"  value="${pageContext.request.queryString}"  />
<c:set var="beginDate2"    value="${beginYear}-${beginMonth}-${beginDay}" />
<c:set var="endDate2"      value="${endYear}-${endMonth}-${endDay}" />
<c:set var="queryString"  value="beginDate=${beginDate}&endDate=${endDate}&search_type=${search_type}&search_word=${search_word}&delivery_state=${delivery_state}"  />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<c:if test="${message=='cancel_order'}">
	<script>
	window.onload=function()
	{
	  init();
	}
	
	function init(){
		alert("주문을 취소했습니다.");
	}
	</script>
</c:if>
<script>
function fn_cancel_order(order_id){
	var answer=confirm("주문을 취소하시겠습니까?");
	if(answer==true){
		var formObj=document.createElement("form");
		var i_order_id = document.createElement("input"); 
	    
	    i_order_id.name="order_id";
	    i_order_id.value=order_id;
		
	    formObj.appendChild(i_order_id);
	    document.body.appendChild(formObj); 
	    formObj.method="post";
	    formObj.action="${contextPath}/mypage/cancelMyOrder.do";
	    formObj.submit();	
	}
}
function fn_exchange_order(order_id){
	var answer=confirm("교환하시겠습니까?");
	if(answer==true){
		var formObj=document.createElement("form");
		var i_order_id = document.createElement("input"); 
	    
	    i_order_id.name="order_id";
	    i_order_id.value=order_id;
		
	    formObj.appendChild(i_order_id);
	    document.body.appendChild(formObj); 
	    formObj.method="post";
	    formObj.action="${contextPath}/mypage/exchangeMyOrder.do";
	    formObj.submit();	
	}
}
function fn_return_order(order_id){
	var answer=confirm("반품하시겠습니까?");
	if(answer==true){
		var formObj=document.createElement("form");
		var i_order_id = document.createElement("input"); 
	    
	    i_order_id.name="order_id";
	    i_order_id.value=order_id;
		
	    formObj.appendChild(i_order_id);
	    document.body.appendChild(formObj); 
	    formObj.method="post";
	    formObj.action="${contextPath}/mypage/returnMyOrder.do";
	    formObj.submit();	
	}
}

//상세조회 버튼 클릭 시 수행
function fn_detail_search(){		
	var frm=document.frm;
	
	beginYear=frm.beginYear.value;	
	beginMonth=frm.beginMonth.value;
	beginDay=frm.beginDay.value;
	beginDate = beginYear+"-"+beginMonth+"-"+beginDay;
//	alert("beginyear"+beginDate);
	
	//체크처리
	/*
	check_term = frm.check_term.checked;
	chksearch_type = frm.chksearch_type.checked;
	chkdelivery_state = frm.chkdelivery_state.checked;
	
	alert("check_term="+ check_term);
	alert("chksearch_type="+ chksearch_type);
	alert("chkdelivery_state="+ chkdelivery_state);
	*/
	
	endYear=frm.endYear.value;
	endMonth=frm.endMonth.value;
	endDay=frm.endDay.value;
	endDate = endYear+"-"+endMonth+"-"+endDay
//	alert("endDate"+endDate);
	
	search_type=frm.s_search_type.value;
	search_word=frm.t_search_word.value;
	delivery_state = frm.s_delivery_state.value;
	
//	alert("search_type="+ search_type);
//	alert("search_word="+ search_word);
//	alert("delivery_state="+ delivery_state);			

	var formObj=document.createElement("form");	
	var i_beginDate = document.createElement("input"); 
	var i_endDate = document.createElement("input");
	var i_search_type = document.createElement("input");
	var i_search_word = document.createElement("input");
	var i_delivery_state = document.createElement("input");
    
    i_beginDate.name="beginDate";
    i_endDate.name="endDate";
    i_search_type.name="search_type";
    i_search_word.name="search_word";
    i_delivery_state.name="delivery_state";
	
	i_beginDate.value=beginDate;
    i_endDate.value=endDate;
    i_search_type.value=search_type;
    i_search_word.value=search_word;
    i_delivery_state.value=delivery_state;
	
//	if ( search_type == true ) 
//	{
    	formObj.appendChild(i_beginDate);
    	formObj.appendChild(i_endDate);
//	}
	
//	if ( chksearch_type == true )
//	{
    	formObj.appendChild(i_search_type);
    	formObj.appendChild(i_search_word);
//	}
	
//	if ( chkdelivery_state == true ) 
	formObj.appendChild(i_delivery_state);
		
    document.body.appendChild(formObj);
	
    formObj.method="get";
    formObj.action="${contextPath}/mypage/listChangeMyOrderStatus.do";
    formObj.submit();
    //alert("submit");	
}

function  calcPeriod(search_period){
	var dt = new Date();
	var beginYear,endYear;
	var beginMonth,endMonth;
	var beginDay,endDay;
	var beginDate,endDate;
	
	endYear = dt.getFullYear();
	endMonth = dt.getMonth()+1;
	endDay = dt.getDate();	
	
	if(beginMonth <10){
		beginMonth='0'+beginMonth;
		if(beginDay<10){
			beginDay='0'+beginDay;
		}
	}
	if(endMonth <10){
		endMonth='0'+endMonth;
		if(endDay<10){
			endDay='0'+endDay;
		}
	}
	endDate=endYear+'-'+endMonth +'-'+endDay;
	beginDate=beginYear+'-'+beginMonth +'-'+beginDay;
	//alert(beginDate+","+endDate);
	return beginDate+","+endDate;
}

</script>
</head>
<body>
<table class="list_view table table-hover table-bordered">
<h3>취소/반품/교환/환불 내역 조회
    <A href="#"> <IMG  src="${contextPath}/resources/image/btn_more_see.jpg">  </A> 
</h3>
<!--검색기능 시작-->	
	<form  method="post" name="frm">
	<table>	
            <tr>
              <td>
                  <!--<input type="checkbox" name="check_term"  checked>-->주문기간:                         
                                       
                    <select name="beginYear">
					     <c:forEach   var="i" begin="0" end="5">
					      <c:choose>
					        <c:when test="${beginYear==beginYear-i}">
					          <option value="${beginYear }" selected>${beginYear  }</option>
					        </c:when>
					        <c:otherwise>
					          <option value="${beginYear-i }">${beginYear-i }</option>
					        </c:otherwise>
					      </c:choose>
					    </c:forEach>
					</select>년 <select name="beginMonth" >
						  <c:forEach   var="mm" begin="1" end="12">
                         	<c:if test="${mm == beginMonth}">
					      		<option value="<fmt:formatNumber value='${mm}' pattern='00'/>" selected><fmt:formatNumber value='${mm}' pattern='00'/></option>
                           	</c:if>       
					       	<c:if test="${mm != beginMonth}">
                           	 <option value="<fmt:formatNumber value='${mm}' pattern='00'/>"><fmt:formatNumber value='${mm}' pattern='00'/></option>
                        	</c:if>
					    </c:forEach>					
					</select>월
					
					 <select name="beginDay">					 
                      	<c:forEach   var="dd" begin="1" end="31">
                         	<c:if test="${dd == beginDay}">
					      		<option value="<fmt:formatNumber value='${dd}' pattern='00'/>" selected><fmt:formatNumber value='${dd}' pattern='00'/></option>
                           	</c:if>       
					       	<c:if test="${dd != beginDay}">
                           	 <option value="<fmt:formatNumber value='${dd}' pattern='00'/>"><fmt:formatNumber value='${dd}' pattern='00'/></option>
                        	</c:if>
					    </c:forEach>		                                            					     
					</select>
					 일 ~ 
                     <select name="endYear">
					     <c:forEach   var="i" begin="0" end="5">
					      <c:choose>
					        <c:when test="${endYear==endYear-i}">
					          <option value="${endYear }" selected>${endYear  }</option>
					        </c:when>
					        <c:otherwise>
					          <option value="${endYear-i }">${endYear-i }</option>
					        </c:otherwise>
					      </c:choose>
					    </c:forEach>
					</select>년 <select name="endMonth" >
						  <c:forEach   var="mm" begin="1" end="12">
                         	<c:if test="${mm == endMonth}">
					      		<option value="<fmt:formatNumber value='${mm}' pattern='00'/>" selected><fmt:formatNumber value='${mm}' pattern='00'/></option>
                           	</c:if>       
					       	<c:if test="${mm != endMonth}">
                           	 <option value="<fmt:formatNumber value='${mm}' pattern='00'/>"><fmt:formatNumber value='${mm}' pattern='00'/></option>
                        	</c:if>
					    </c:forEach>							
					</select>월					
                    <select name="endDay">
                     	<c:forEach   var="dd" begin="1" end="31">
                         	<c:if test="${dd == endDay}">
					      		<option value="<fmt:formatNumber value='${dd}' pattern='00'/>" selected><fmt:formatNumber value='${dd}' pattern='00'/></option>
                           	</c:if>       
					       	<c:if test="${dd != endDay}">
                           	 <option value="<fmt:formatNumber value='${dd}' pattern='00'/>"><fmt:formatNumber value='${dd}' pattern='00'/></option>
                        	</c:if>
					    </c:forEach>	
					</select>                    				 
					 일</td>
              <td rowspan="3">&nbsp;<input class="btn-sm btn-outline-secondary" type="button" id="btnSearch" name="btnSearch"   value=" 조회 " onClick="fn_detail_search()"/></td>
             </tr>
             <tr>
               <td>						               
                  <!--<input type="checkbox" name="chksearch_type"  checked>-->주문내역:
                  <select name="s_search_type">
                  	 <option value="" <c:if test="${'' eq search_type}">selected</c:if>>전체</option>
                     <option value="goods_title" <c:if test="${'goods_title' eq search_type}">selected</c:if>>주문상품명</option>
                     <option value="orderer_name" <c:if test="${'orderer_name' eq search_type}">selected</c:if>>주문자명</option>
				<!--	 <option value="member_id" <c:if test="${'member_id' eq search_type}">selected</c:if>>주문자아이디</option> --> <!--관리자모드일 경우 해제-->
					 <option value="orderer_hp" <c:if test="${'orderer_hp' eq search_type}">selected</c:if>>주문자휴대폰번호</option>
					 
                  </select>
                  <input name="t_search_word"  type="text" id="t_search_word"  size="20" maxlength="20" value="${search_word}"/>  
               </td>
              </tr>
              <tr>
               <td>
               	  <!--<input type="checkbox" name="chkdelivery_state"  checked>-->주문상태:	 
                   <select name="s_delivery_state"  id="s_delivery_state">				 
				     <option  value=""  <c:if test="${'' eq delivery_state}">selected</c:if>>전체</option>                 
				     <option  value="delivery_prepared" <c:if test="${'delivery_prepared' eq delivery_state}">selected</c:if>>배송준비중</option>
				     <option  value="delivering" <c:if test="${'delivering' eq delivery_state}">selected</c:if>>배송중</option>
				     <option  value="finished_delivering" <c:if test="${'finished_delivering' eq delivery_state}">selected</c:if>>배송완료</option>
				     <option  value="cancel_order" <c:if test="${'cancel_order' eq delivery_state}">selected</c:if>>주문취소</option>                     
				     <option  value="return_req" <c:if test="${'return_req' eq delivery_state}">selected</c:if>>반품신청</option>
                     <option  value="return_done" <c:if test="${'return_done' eq delivery_state}">selected</c:if>>반품완료</option>
                     <option  value="exchange_req" <c:if test="${'exchange_req' eq delivery_state }">selected</c:if>>교환신청</option>				  
                     <option  value="exchange_done" <c:if test="${'exchange_done' eq delivery_state}">selected</c:if>>교환완료</option>	
                     <option  value="refund_done" <c:if test="${'refund_done' eq delivery_state}">selected</c:if>>환불완료</option>	  
				 </select>               
              </td>
            </tr>				       
    </table>

<!--검색기능 끝-->

<table class="list_view">
		<tbody align=center >
			 <tr style="background:lightgray" >
				<td>주문번호</td>
				<td>주문일자</td>
				<td>주문상품</td>
				<td>주문상태</td>
				<td>주문변경</td>
			</tr>
      <c:choose>
         <c:when test="${ empty myOrderList  }">
		  <tr>
		    <td colspan=5 class="fixed">
				  <strong>주문한 상품이 없습니다.</strong>
		    </td>
		  </tr>
        </c:when>
        <c:otherwise>
	      <c:forEach var="item" items="${myOrderList }"  varStatus="i">
	       <c:choose> 
              <c:when test="${ pre_order_id != item.order_id}">
                <c:choose>
	              <c:when test="${item.delivery_state=='delivery_prepared' }">
	                <tr  bgcolor="lightgreen">    
	              </c:when>
	              <c:when test="${item.delivery_state=='finished_delivering' }">
	                <tr  bgcolor="lightgray">    
	              </c:when>
	              <c:otherwise>
	                <tr  bgcolor="orange">   
	              </c:otherwise>
	            </c:choose> 
            <tr>
             <td>
		       <a href="${contextPath}/mypage/myOrderDetail.do?order_id=${item.order_id }"><span>${item.order_id }</span>  </a>
		     </td>
		    <td><span>${item.pay_order_time }</span></td>
			<td align="left">
			   <strong>
			      <c:forEach var="item2" items="${myOrderList}" varStatus="j">
			          <c:if  test="${item.order_id ==item2.order_id}" >
			            <a href="${contextPath}/goods/goodsDetail.do?goods_id=${item2.goods_id }">${item2.goods_title }[${item2.order_goods_qty }개]</a>
                        <c:choose>
                            <c:when test="${item.delivery_state == 'exchange_req'}" >
                                 [교환수량:${item2.change_goods_qty }개]
</c:when>
                            <c:when test="${item.delivery_state == 'return_req'}" >
                                 [반품수량:${item2.change_goods_qty }개]
                          </c:when>                           
                        </c:choose>                        	
                        <br>
			         </c:if>   
				 </c:forEach>
				</strong></td>
			<td>
			  <c:choose>
			    <c:when test="${item.delivery_state=='delivery_prepared' }">
			       배송준비중
			    </c:when>
			    <c:when test="${item.delivery_state=='delivering' }">
			       배송중
			    </c:when>
			    <c:when test="${item.delivery_state=='finished_delivering' }">
			       배송완료
			    </c:when>
			    <c:when test="${item.delivery_state=='cancel_order' }">
			       주문취소
			    </c:when>
                 <c:when test="${item.delivery_state=='return_req' }">
			       반품신청
			    </c:when>
			    <c:when test="${item.delivery_state=='return_end' }">
			       반품완료
			    </c:when>
                <c:when test="${item.delivery_state=='exchange_req' }">
			       교환신청
			    </c:when>                
			  </c:choose>
			</td>
			<td>
               <c:choose>
			     <c:when test="${item.delivery_state=='delivery_prepared'}">
			       <input class="btn-sm btn-danger" type="button" onClick="fn_cancel_order('${item.order_id}')" value="주문취소"  />
			     </c:when>
                 <c:when test="${item.delivery_state=='delivering'}">
                    <p><input  class="btn-sm btn-info" type="button" onClick="fn_exchange_order('${item.order_id}')" value="교환신청"  /></p>
                    <p><input  class="btn-sm btn-secondary" type="button" onClick="fn_return_order('${item.order_id}')" value="반품신청" /></p>
                 </c:when>   
                  <c:when test="${item.delivery_state=='finished_delivering'}">
                    <p><input  class="btn-sm btn-info" type="button" onClick="fn_exchange_order('${item.order_id}')" value="교환신청"  /></p>
                    <p><input  class="btn-sm btn-secondary" type="button" onClick="fn_return_order('${item.order_id}')" value="반품신청" /></p>
                 </c:when>              
			   </c:choose>			               	  
			</td>
			</tr>
             <c:set  var="pre_order_id" value="${item.order_id}" />
           </c:when>
      </c:choose>
	   </c:forEach>
	  </c:otherwise>
    </c:choose>     
</tbody>
</table>
<div align="center">
    <table >
        <!--페이징 시작-->        
          <tr>
             <td  class="fixed">
                 <c:forEach   var="page" begin="1" end="${(totCnt/10)+1}" step="1" >                 
			         <c:if test="${section >1 && page==1 }">
			          <a href="${contextPath}/mypage/listChangeMyOrderStatus.do?${queryString}&section=${section-1}&pageNum=${(section-1)*10 +1 }">&nbsp;&nbsp;</a>
			         </c:if>			         
			         <a href="${contextPath}/mypage/listChangeMyOrderStatus.do?${queryString}&section=${section}&pageNum=${page}"><c:choose><c:when test="${page==pageNum}"><span style="color:red">${(section-1)*10 +page }</span></c:when><c:otherwise>${(section-1)*10 +page }</c:otherwise></c:choose></a>
			         <c:if test="${page ==10 }">
			          <a href="${contextPath}/mypage/listChangeMyOrderStatus.do?${queryString}&section=${section+1}&pageNum=${section*10+1}">&nbsp; next</a>
			         </c:if> 
	      		</c:forEach> 
           </td>
        </tr>  		
         <!--페이징 끝-->      	  
            
    </table>
</div>

<!--
<br><br><br>	
<h1>계좌내역
    <a href="#"> <img  src="${contextPath}/resources/image/btn_more_see.jpg" />  </a>
</h1>
<table border=0 width=100%  cellpadding=10 cellspacing=10>
  <tr>
    <td>
	   예치금 &nbsp;&nbsp;  <strong>10000원</strong>
   </td>
    <td>
	   쇼핑머니 &nbsp;&nbsp; <strong>9000원</strong></td>
   </tr>
   <tr>
    <td>
	   쿠폰 &nbsp;&nbsp;  <strong>6000원</strong>
   </td>
    <td>
	   포인트 &nbsp;&nbsp; <strong>2000원</strong>
   </td>
   </tr>
   <tr>
    <td>
	   상품권 &nbsp;&nbsp;  <strong>4000원</strong>
   </td>
    <td>
		디지털머니 &nbsp;&nbsp; <strong>9000원</strong>
   </td>
   </tr>
</table>

<br><br><br>	
<h1>나의 정보
    <a href="#"> <img  src="${contextPath}/resources/image/btn_more_see.jpg" />  </a>
</h1>
<table border=0 width=100% cellpadding=10 cellspacing=10>
  <tr>
    <td>
	   이메일:
   </td>
    <td>
	   <strong>${memberInfo.email1 }@${memberInfo.email2 }</strong>
   </td>
   </tr>
   <tr>
    <td>
	   전화번호 
   </td>
    <td>
	   <strong>${memberInfo.hp1 }-${memberInfo.hp2}-${memberInfo.hp3 }</strong>
   </td>
   </tr>
   <tr>
    <td>
	  주소 
   </td>
    <td>
		도로명:  &nbsp;&nbsp; <strong>${memberInfo.roadAddress }</strong>  <br>
		지번:   &nbsp;&nbsp; <strong>${memberInfo.jibunAddress }</strong> 
   </td>
   </tr>
</table>
//-->
</form>	
</body>
</html>
