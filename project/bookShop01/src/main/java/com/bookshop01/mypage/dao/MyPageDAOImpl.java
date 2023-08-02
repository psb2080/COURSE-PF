package com.bookshop01.mypage.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.common.log.LoggingAdvice ;
import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.order.vo.OrderVO;

// 異붽� by Dean 230627, 30
import com.bookshop01.mypage.vo.ExchangeOrderVO;
import com.bookshop01.mypage.vo.ReturnOrderVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO{
	@Autowired
	private SqlSession sqlSession;
	
	// dean // �삤瑜� 異쒕젰�쓣 �쐞�빐 異붽�
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	public List<OrderVO> selectMyOrderGoodsList(String member_id) throws DataAccessException{
		List<OrderVO> orderGoodsList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderGoodsList",member_id);
		return orderGoodsList;
	}	
	
	public List selectMyOrderInfo(String order_id) throws DataAccessException{
		
		String sql = "select * from t_shopping_order where order_id=" + order_id;
		logger.info("SQL :" + sql);
		
		List myOrderList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderInfo",order_id);
		
		logger.info("myOrderList :" + myOrderList.toString());
		return myOrderList;
	}	

	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException{
		List<OrderVO> myOrderHistList=(List)sqlSession.selectList("mapper.mypage.selectMyOrderHistoryList",dateMap);
		return myOrderHistList;
	}
	
	public void updateMyInfo(Map memberMap) throws DataAccessException{
		sqlSession.update("mapper.mypage.updateMyInfo",memberMap);
	}
	
	public MemberVO selectMyDetailInfo(String member_id) throws DataAccessException{
		MemberVO memberVO=(MemberVO)sqlSession.selectOne("mapper.mypage.selectMyDetailInfo",member_id);
		return memberVO;
		
	}
	
	public void updateMyOrderCancel(String order_id) throws DataAccessException{
		sqlSession.update("mapper.mypage.updateMyOrderCancel",order_id);
	}
	
	// 留덉씠�럹�씠吏� 異붽�
	public void delNewMember(String member_id) throws DataAccessException{
		sqlSession.update("mapper.mypage.delNewMember",member_id);
	}


	// 留덉씠�럹�씠吏�>痍⑥냼/諛섑뭹/援먰솚/�솚遺� �떊泥� 諛� 議고쉶(2023.06.19 by Dean)
	public List<OrderVO> listChangeMyOrderStatus(Map dateMap) throws DataAccessException{
		List<OrderVO> myOrderList=(List)sqlSession.selectList("mapper.mypage.listChangeMyOrderStatus",dateMap);
		return myOrderList;
	}	
	
	// 援먰솚泥섎━ by Dean 230627	  �닔�젙:230629
	public void addExChangeMyOrder(ExchangeOrderVO exchangeOrderVO ) throws Exception {	
		logger.info("exchangeOrderVO" + exchangeOrderVO.toString());
		sqlSession.insert("mapper.mypage.insertExchageMyOrder", exchangeOrderVO );
	}	

	// 諛섑뭹泥섎━ by Dean 230630	  �닔�젙:230630
	public void addReturnMyOrder(ReturnOrderVO returnMyOrder) throws Exception {	
		sqlSession.insert("mapper.mypage.insertReturnMyOrder", returnMyOrder );
	}
		
	// 援먰솚/諛섑뭹 �닔�웾�쓣 二쇰Ц�뀒�씠釉붿뿉 change_goods_qty�뿉 ���옣 by Dean 23.07.03
	public void updateChangeOrderQty(Map receiverMap)
	{		
		sqlSession.update("mapper.mypage.updateChangeOrderQty",receiverMap);
	}
	
}
