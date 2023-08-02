package com.bookshop01.mypage.vo;

import org.springframework.stereotype.Component;

@Component("returnMyOrder")   // @Component 스프링 컨테이너가 component-scan에 의해 지정한 클래스를 빈으로 자동 변환
public class ReturnOrderVO {	
	private int return_seq_num;	
	private int order_id;	
	private String member_id;	
	private int goods_id;	
	private int return_goods_qty;		         // 반품상품수
	private String return_request_date = "";     // 반품신청일
	private String return_reception_date = "";	// 반품접수일
	private String return_cancel_date = "";      // 반품취소일
	private String return_reason_code = "";    // 반품코드 단순변심( 10,11 ), 상품문제( 20,21,22,23 ), 배송문제( 30 )
	private String return_status_code = "";    // 반품 처리 상태
	private String return_tracking_number = ""; // 반품 운송장 번호
	private String return_memo = "";           // 메모
	
	public ReturnOrderVO() {}
	
	public int getReturn_seq_num() {
		return return_seq_num;
	}
	public void setReturn_seq_num(int return_seq_num) {
		this.return_seq_num = return_seq_num;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getReturn_goods_qty() {
		return return_goods_qty;
	}
	public void setReturn_goods_qty(int return_goods_qty) {
		this.return_goods_qty = return_goods_qty;
	}
	public String getReturn_request_date() {
		return return_request_date;
	}
	public void setReturn_request_date(String return_request_date) {
		this.return_request_date = return_request_date;
	}
	public String getReturn_reception_date() {
		return return_reception_date;
	}
	public void setReturn_reception_date(String return_reception_date) {
		this.return_reception_date = return_reception_date;
	}
	public String getReturn_cancel_date() {
		return return_cancel_date;
	}
	public void setReturn_cancel_date(String return_cancel_date) {
		this.return_cancel_date = return_cancel_date;
	}
	public String getReturn_reason_code() {
		return return_reason_code;
	}
	public void setReturn_reason_code(String return_reason_code) {
		this.return_reason_code = return_reason_code;
	}
	public String getReturn_status_code() {
		return return_status_code;
	}
	public void setReturn_status_code(String return_status_code) {
		this.return_status_code = return_status_code;
	}
	public String getReturn_tracking_number() {
		return return_tracking_number;
	}
	public void setReturn_tracking_number(String return_tracking_number) {
		this.return_tracking_number = return_tracking_number;
	}
	public String getReturn_memo() {
		return return_memo;
	}
	public void setReturn_memo(String return_memo) {
		this.return_memo = return_memo;
	}
	
}
