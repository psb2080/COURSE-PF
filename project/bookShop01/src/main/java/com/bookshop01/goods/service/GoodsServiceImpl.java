package com.bookshop01.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bookshop01.goods.dao.GoodsDAO;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.goods.vo.ReplyVO;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDAO goodsDAO;
	
	// 메인페이지 검색기능(goods_status 통해 검색)
	public Map<String,List<GoodsVO>> listGoods() throws Exception {
		Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
		List<GoodsVO> goodsList=goodsDAO.selectGoodsList("bestseller");
		goodsMap.put("bestseller",goodsList);
		goodsList=goodsDAO.selectGoodsList("newbook");
		goodsMap.put("newbook",goodsList);
		
		goodsList=goodsDAO.selectGoodsList("steadyseller");
		goodsMap.put("steadyseller",goodsList);
		return goodsMap;
	}
	
	// 일반적인 검색기능(goods_sort 통해 검색)
		public Map<String,List<GoodsVO>> listGoods_bysort() throws Exception {
			Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
			List<GoodsVO> goodsList=goodsDAO.selectGoodsList_bysort("과실수");
			goodsMap.put("과실수",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("공기정화식물");
			goodsMap.put("공기정화식물",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("꽃다발");
			goodsMap.put("꽃다발",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("근조화환");
			goodsMap.put("근조화환",goodsList);		
			goodsList=goodsDAO.selectGoodsList_bysort("서양란");
			goodsMap.put("서양란",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("축하화환");
			goodsMap.put("축하화환",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("꽃박스");
			goodsMap.put("꽃박스",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("관엽");
			goodsMap.put("관엽",goodsList);		
			goodsList=goodsDAO.selectGoodsList_bysort("동양란");
			goodsMap.put("동양란",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("분재");
			goodsMap.put("분재",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("다육이");
			goodsMap.put("다육이",goodsList);
			goodsList=goodsDAO.selectGoodsList_bysort("관상수");
			goodsMap.put("관상수",goodsList);
			
			return goodsMap;
		}
	
	public Map goodsDetail(String _goods_id) throws Exception {
		Map goodsMap=new HashMap();
		GoodsVO goodsVO = goodsDAO.selectGoodsDetail(_goods_id);
		goodsMap.put("goodsVO", goodsVO);
		List<ImageFileVO> imageList =goodsDAO.selectGoodsDetailImage(_goods_id);
		goodsMap.put("imageList", imageList);
		return goodsMap;
	}
	
	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list=goodsDAO.selectKeywordSearch(keyword);
		return list;
	}
	
	public List<GoodsVO> searchGoods(String searchWord) throws Exception{
		List goodsList=goodsDAO.selectGoodsBySearchWord(searchWord);
		return goodsList;
	}
	
	@Override
	public void addReply(ReplyVO reply) throws Exception{
		goodsDAO.addReply(reply);
	}
	
	@Override
	public List<ReplyVO> replyList(String goods_id) throws Exception{
		return goodsDAO.replyList(goods_id);
	}
	
	@Override
	public void deleteReply(ReplyVO reply) throws Exception{
		goodsDAO.deleteReply(reply);
	}
	
	@Override
	public String idCheck(int repNum) throws Exception{
		return goodsDAO.idCheck(repNum);
	}
	
	@Override
	public void modifyReply(ReplyVO reply) throws DataAccessException{
		goodsDAO.modifyReply(reply);
	}
	
	
}
