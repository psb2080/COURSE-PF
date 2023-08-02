<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
 <script>
 <c:choose>
 <c:when test='${modified_personal_info==true }'>

 </script>
 </c:when>
 <c:otherwise>
 <script>
 window.onload=function()
 {
   test();
 }

 function test(){
 	init();
 	//alert("회원 정보가 수정되었습니다.");
	// 	init();
 }
 function init(){
 	var frm_mod_member=document.frm_mod_member;
 	var h_tel1=frm_mod_member.h_tel1;
 	var h_hp1=frm_mod_member.h_hp1;
 	var tel1=h_tel1.value;
 	var hp1=h_hp1.value;
 	
 	var select_tel1=frm_mod_member.tel1;
 	var select_hp1=frm_mod_member.hp1;
 	select_tel1.value=tel1;
 	select_hp1.value=hp1;
 }
 </script>
 </c:otherwise>
 </c:choose>
  <script>
     function execDaumPostcode() {
         new daum.Postcode({
             oncomplete: function(data) {
                 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                 // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                 // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                 var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                 var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                 // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                 // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                 if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                     extraRoadAddr += data.bname;
                 }
                 // 건물명이 있고, 공동주택일 경우 추가한다.
                 if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                 }
                 // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                 if(extraRoadAddr !== ''){
                     extraRoadAddr = ' (' + extraRoadAddr + ')';
                 }
                 // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                 if(fullRoadAddr !== ''){
                     fullRoadAddr += extraRoadAddr;
                 }

                 // 우편번호와 주소 정보를 해당 필드에 넣는다.
                 document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                 document.getElementById('roadAddress').value = fullRoadAddr;
                 document.getElementById('jibunAddress').value = data.jibunAddress;

                 // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                 if(data.autoRoadAddress) {
                     //예상되는 도로명 주소에 조합형 주소를 추가한다.
                     var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                     document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                 } else if(data.autoJibunAddress) {
                     var expJibunAddr = data.autoJibunAddress;
                     document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                 } else {
                     document.getElementById('guide').innerHTML = '';
                 }
             }
         }).open();
     }
 </script>
 <script>
 function fn_modify_member_info(member_id,mod_type){
 	var value;
 	// alert(member_id);
 	// alert("mod_type:"+mod_type);
 		var frm_mod_member=document.frm_mod_member;
 		if(mod_type=='member_pw'){
 			value=frm_mod_member.member_pw.value;
 			//alert("member_pw:"+value);
 		}else if(mod_type=='member_gender'){
 			var member_gender=frm_mod_member.member_gender;
 			for(var i=0; member_gender.length;i++){
 			 	if(member_gender[i].checked){
 					value=member_gender[i].value;
 					break;
 				} 
 			}
 			//alert("member_gender111:"+value);
 			
 		}else if(mod_type=='member_birth'){
 			var member_birth_y=frm_mod_member.member_birth_y;
 			var member_birth_m=frm_mod_member.member_birth_m;
 			var member_birth_d=frm_mod_member.member_birth_d;
 			var member_birth_gn=frm_mod_member.member_birth_gn;
 			
 			for(var i=0; member_birth_y.length;i++){
 			 	if(member_birth_y[i].selected){
 					value_y=member_birth_y[i].value;
 					break;
 				} 
 			}
 			for(var i=0; member_birth_m.length;i++){
 			 	if(member_birth_m[i].selected){
 					value_m=member_birth_m[i].value;
 					break;
 				} 
 			}
 			
 			for(var i=0; member_birth_d.length;i++){
 			 	if(member_birth_d[i].selected){
 					value_d=member_birth_d[i].value;
 					break;
 				} 
 			}
 			
 			//alert("수정 년:"+value_y+","+value_m+","+value_d);
 			for(var i=0; member_birth_gn.length;i++){
 			 	if(member_birth_gn[i].checked){
 					value_gn=member_birth_gn[i].value;
 					break;
 				} 
 			}
 			//alert("생년 양음년 "+value_gn);
 			value=+value_y+","+value_m+","+value_d+","+value_gn;
 		}else if(mod_type=='tel'){
 			var tel1=frm_mod_member.tel1;
 			var tel2=frm_mod_member.tel2;
 			var tel3=frm_mod_member.tel3;
 			
 			for(var i=0; tel1.length;i++){
 			 	if(tel1[i].selected){
 					value_tel1=tel1[i].value;
 					break;
 				} 
 			}
 			value_tel2=tel2.value;
 			value_tel3=tel3.value;
 			
 			value=value_tel1+","+value_tel2+", "+value_tel3;
 		}else if(mod_type=='hp'){
 			var hp1=frm_mod_member.hp1;
 			var hp2=frm_mod_member.hp2;
 			var hp3=frm_mod_member.hp3;
 			var smssts_yn=frm_mod_member.smssts_yn;
 			
 			for(var i=0; hp1.length;i++){
 			 	if(hp1[i].selected){
 					value_hp1=hp1[i].value;
 					break;
 				} 
 			}
 			value_hp2=hp2.value;
 			value_hp3=hp3.value;
 			value_smssts_yn=smssts_yn.checked;
 			
 			value=value_hp1+","+value_hp2+", "+value_hp3+","+value_smssts_yn;
 			
 		}else if(mod_type=='email'){
 			var email1=frm_mod_member.email1;
 			var email2=frm_mod_member.email2;
 			var emailsts_yn=frm_mod_member.emailsts_yn;
 			
 			value_email1=email1.value;
 			value_email2=email2.value;
 			value_emailsts_yn=emailsts_yn.checked;
 			
 			value=value_email1+","+value_email2+","+value_emailsts_yn;
 			//alert(value);
 		}else if(mod_type=='address'){
 			var zipcode=frm_mod_member.zipcode;
 			var roadAddress=frm_mod_member.roadAddress;
 			var jibunAddress=frm_mod_member.jibunAddress;
 			var namujiAddress=frm_mod_member.namujiAddress;
 			
 			value_zipcode=zipcode.value;
 			value_roadAddress=roadAddress.value;
 			value_jibunAddress=jibunAddress.value;
 			value_namujiAddress=namujiAddress.value;
 			
 			value=value_zipcode+","+value_roadAddress+","+value_jibunAddress+","+value_namujiAddress;
 		}
 	 
 		$.ajax({
 			type : "post",
 			async : false, //false인 경우 동기식으로 처리한다.
 			url : "http://localhost:8090/bookshop01/admin/member/modifyMemberInfo.do",
 			data : {
 				member_id:member_id,
 				mod_type:mod_type,
 				value:value
 			},
 			success : function(data, textStatus) {
 				if(data.trim()=='mod_success'){
 					alert("회원 정보를 수정했습니다.");
 				}else if(data.trim()=='failed'){
 					alert("다시 시도해 주세요.");	
 				}
 				
 			},
 			error : function(data, textStatus) {
 				alert("에러가 발생했습니다."+data);
 			},
 			complete : function(data, textStatus) {
 				//alert("작업을완료 했습니다");
 				
 			}
 		}); //end ajax
 }

 function fn_delete_member(member_id ,del_yn){
 	var frm_mod_member=document.frm_mod_member;
 	var i_member_id = document.createElement("input");
 	var i_del_yn = document.createElement("input");
     
 	
     i_member_id.name="member_id";
     i_del_yn.name="del_yn";
     i_member_id.value=member_id;
     i_del_yn.value=del_yn;
     
     frm_mod_member.appendChild(i_member_id);
     frm_mod_member.appendChild(i_del_yn);
     frm_mod_member.method="post";
     frm_mod_member.action="/bookshop01/member/deleteMember.do";
     frm_mod_member.submit();
 }

 function fn_overlapped(){
	 
	    var _email1=$("#_email1").val();
	    var _member_id=$("#_member_id").val();
	    
	    if(_email1==''){
	   	 alert("email를 입력하세요");
	   	 return;
	    }
	    
	    $.ajax({
	       type:"post",
	       async:false,  
	       url:"${contextPath}/member/search_id.do",
	       dataType:"text",
	       data: {email1:_email1},
	       success:function (data,textStatus){
	 
	        	  alert( "찾으시는 아이디는 " + data + "입니다." ) ;
	        	  	
		       	    $('#btnSearch_id').prop("disabled", true);
		       	    $('#_email1').prop("disabled", true);
		       	    $('#email1').val(_email1);
		       	    $('#_email2').prop("disabled", true);
		       	    $('#email2').val(_email2);
	          
	       },
	       error:function(data,textStatus){
	          alert("아디디가 없습니다.");
	       },
	       complete:function(data,textStatus){
	          //alert("작업을완료 했습니다");
	       }
	    });  //end ajax	 
	 }	
 

</script>
</head>


<body>
	<H3>회원 아이디 찾기</H3>
	<DIV id="detail_table">
	<form action="${contextPath}/member/search_id.do" method="post">
		<TABLE>
			<TBODY>
				<tr class="dot_line">
					<td class="fixed_join">이메일<br>(e-mail)</td>
					<td>
					<input size="10px" type="text" name="_email1"  id="_email1" /> 
					
					  
				@ <input  size="10px"  type="text" name="_email2" id="_email2" />
						
					  
						  <select name="_email2" onChange=""	title="직접입력" id="_email2">
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
									<option value="freechal.com">freechal.com</option> 
									
				
				  </td>
				</tr>
			</TBODY>
		</TABLE>
		<br><br>
	

		<input type="button"  value="아이디 찾기" onClick="fn_overlapped()" />

	</form>		
</body>
</html>