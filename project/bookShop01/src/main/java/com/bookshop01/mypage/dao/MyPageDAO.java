package com.bookshop01.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.mypage.vo.ExchangeOrderVO;
import com.bookshop01.mypage.vo.ReturnOrderVO;
import com.bookshop01.order.vo.OrderVO;

public interface MyPageDAO {
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException;
	public List selectMyOrderInfo(String order_id) throws DataAccessException;
	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException;
	public void updateMyInfo(Map memberMap) throws DataAccessException;
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException;
	public void updateMyOrderCancel(String order_id) throws DataAccessException;
	// 留덉씠�럹�씠吏� 異붽�
	public void delNewMember(String member_id) throws DataAccessException;
	
	// 留덉씠�럹�씠吏� > 痍⑥냼/諛섑뭹/援먰솚/�솚遺� �궡�뿭議고쉶 by Dean
	public List<OrderVO> listChangeMyOrderStatus(Map dateMap) throws DataAccessException;	
	// 留덉씠�럹�씠吏� > 痍⑥냼/諛섑뭹/援먰솚/�솚遺� �궡�뿭議고쉶 > 援먰솚泥섎━ by Dean		
	public void addExChangeMyOrder(ExchangeOrderVO addExChangeMyOrder) throws Exception;	
	// 留덉씠�럹�씠吏� > 痍⑥냼/諛섑뭹/援먰솚/�솚遺� �궡�뿭議고쉶 > 援먰솚泥섎━ by Dean		
	public void addReturnMyOrder(ReturnOrderVO addReturnMyOrder) throws Exception;	
	
	// 援먰솚/諛섑뭹 �닔�웾�쓣 二쇰Ц�뀒�씠釉붿뿉 change_goods_qty�뿉 ���옣 by Dean 23.07.03	
	public void updateChangeOrderQty(Map<String,String> receiverMap);
	
}
	