package com.bookshop01.mypage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.mypage.vo.ExchangeOrderVO;
import com.bookshop01.mypage.vo.ReturnOrderVO;
import com.bookshop01.order.vo.OrderVO;

public interface MyPageService{
	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;
	public List findMyOrderInfo(String order_id) throws Exception;
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception;
	public void cancelOrder(String order_id) throws Exception;
	public MemberVO myDetailInfo(String member_id) throws Exception;

	// 留덉씠�럹�씠吏� 異붽�
	public void delMember(String member_id) throws Exception;
	
	// 취소/반품/교환/환불 신청 및 조회 메인( 2023.06.19 by Dean ) 7.4 수정
	public List<OrderVO> listChangeMyOrderStatus(Map dateMap) throws Exception;
	// 취소/반품/교환/환불 신청 및 조회 메인> 교환처리 ( 2023.06.27 by Dean )  29수정	
	 
	public void addExChangeMyOrder(ExchangeOrderVO exchangeMyOrder) throws Exception;
	// 취소/반품/교환/환불 신청 및 조회 메인> 반품처리 ( 2023.06.30 by Dean )  	
	public void addReturnMyOrder(ReturnOrderVO returnMyOrder) throws Exception;
	
	// 교환/반품 수량을 주문테이블에 change_goods_qty에 저장 by Dean 23.07.03
	public void updateChangeOrderQty(Map receiverMap) throws Exception;

}
