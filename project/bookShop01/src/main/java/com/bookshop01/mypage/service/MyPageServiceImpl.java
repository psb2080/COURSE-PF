package com.bookshop01.mypage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.mypage.dao.MyPageDAO;
import com.bookshop01.mypage.vo.MyPageVO;
import com.bookshop01.order.vo.OrderVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bookshop01.common.log.LoggingAdvice;
import com.bookshop01.mypage.vo.ExchangeOrderVO;   // 異붽� by Dean 230627
import com.bookshop01.mypage.vo.ReturnOrderVO;     // 異붽� by Dean 230630

@Service("myPageService")
@Transactional(propagation=Propagation.REQUIRED)
public class MyPageServiceImpl  implements MyPageService{
	@Autowired
	private MyPageDAO myPageDAO;

	// �삤瑜� 異쒕젰�쓣 �쐞�빐 異붽�
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

	public List<OrderVO> listMyOrderGoods(String member_id) throws Exception{
		return myPageDAO.selectMyOrderGoodsList(member_id);
	}
	
	public List findMyOrderInfo(String order_id) throws Exception{
		return myPageDAO.selectMyOrderInfo(order_id);
	}
	
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception{
		return myPageDAO.selectMyOrderHistoryList(dateMap);
	}
	
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception{
		 String member_id=(String)memberMap.get("member_id");
		 myPageDAO.updateMyInfo(memberMap);
		 return myPageDAO.selectMyDetailInfo(member_id);
	}
	
	public void cancelOrder(String order_id) throws Exception{
		myPageDAO.updateMyOrderCancel(order_id);
	}
	public MemberVO myDetailInfo(String member_id) throws Exception{
		return myPageDAO.selectMyDetailInfo(member_id);
	}
	// 留덉씠�럹�씠吏� 異붽�
	public void delMember(String member_id) throws Exception{
		myPageDAO.delNewMember(member_id);
	}
	
	// 마이페이지> 취소/반품/교환/환불 신청 및 조회(메인) 23.06.20 by Dean   23.07.04 수정
	public List<OrderVO> listChangeMyOrderStatus(Map dateMap) throws Exception{
		return myPageDAO.listChangeMyOrderStatus(dateMap);
	}	
	
	// 마이페이지> 취소/반품/교환/환불 신청 및 조회(메인) > 교환처리(insert) 23.06.29 수정 by Dean	
	public void addExChangeMyOrder(ExchangeOrderVO exchangeMyOrder) throws Exception {
		myPageDAO.addExChangeMyOrder(exchangeMyOrder);
	}
	// 마이페이지> 취소/반품/교환/환불 신청 및 조회(메인) > 반품처리(insert) 23.06.30 수정 by Dean	
	public void addReturnMyOrder(ReturnOrderVO returnMyOrder) throws Exception {
			myPageDAO.addReturnMyOrder(returnMyOrder);
	}
	
	// 교환/반품 수량을 주문테이블에 change_goods_qty에 저장 by Dean 23.07.03
	public void updateChangeOrderQty(Map receiverMap) throws Exception
	{		
		myPageDAO.updateChangeOrderQty(receiverMap);
	}
}
