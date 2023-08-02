package com.bookshop01.mypage.vo;

import org.springframework.stereotype.Component;

@Component("exchangeOrderVO")   // @Component 스프링 컨테이너가 component-scan에 의해 지정한 클래스를 빈으로 자동 변환
public class ExchangeOrderVO {	
	private int exchange_seq_num;	
	private int order_id;	
	private String member_id;	
	private int goods_id;	
	private int exchange_goods_qty;		           // 교환상품수
	private String exchange_request_date = "";          // 교환신청일  default sysdate
	private String exchange_reception_date = "";  // 교환접수일   디폴트 값이 없고 값이 전달안되어 초기값이 안주면 오류가 남 
	private String exchange_cancel_date = "";     // 교환취소일
	private String exchange_reason_code;     // 교환코드 단순변심( 10,11 ), 상품문제( 20,21,22,23 ), 배송문제( 30 )
	private String exchange_status_code;     // 교환 처리 상태
	private String exchange_memo = "";            // 메모 #10

	public ExchangeOrderVO() {}	
	
	public String getExchange_memo() {
		return exchange_memo;
	}

	public void setExchange_memo(String exchange_memo) {
		this.exchange_memo = exchange_memo;
	}	
	
	public String getExchange_request_date() {
		return exchange_request_date;
	}
	public void setExchange_request_date(String exchange_request_date) {
		this.exchange_request_date = exchange_request_date;
	}
	public String getExchange_reception_date() {
		return exchange_reception_date;
	}
	public void setExchange_reception_date(String exchange_reception_date) {
		this.exchange_reception_date = exchange_reception_date;
	}
	public String getExchange_cancel_date() {
		return exchange_cancel_date;
	}
	public void setExchange_cancel_date(String exchange_cancel_date) {
		this.exchange_cancel_date = exchange_cancel_date;
	}
	public String getExchange_status_code() {
		return exchange_status_code;
	}
	public void setExchange_status_code(String exchange_status_code) {
		this.exchange_status_code = exchange_status_code;
	}
	public int getExchange_seq_num() {
		return exchange_seq_num;
	}
	public void setExchange_seq_num(int exchange_seq_num) {
		this.exchange_seq_num = exchange_seq_num;
	}
		
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public int getExchange_goods_qty() {
		return exchange_goods_qty;
	}
	public void setExchange_goods_qty(int exchange_goods_qty) {
		this.exchange_goods_qty = exchange_goods_qty;
	}
	
	public void setExchange_reason_code(String exchange_reason_code) {
		this.exchange_reason_code = exchange_reason_code;
	}
	
	public String getExchange_reason_code() {
		return exchange_reason_code;
	}	
}
