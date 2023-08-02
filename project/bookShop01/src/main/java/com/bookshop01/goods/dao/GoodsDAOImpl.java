package com.bookshop01.goods.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.goods.vo.ReplyVO;

@Repository("goodsDAO")
public class GoodsDAOImpl  implements GoodsDAO{
	@Autowired
	private SqlSession sqlSession;

	// 메인에서 사용했던 검색기능(goods_status 통해 검색)
	@Override
	public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException {
		List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);
	   return goodsList;	
     
	}
	
	// 일반적인 검색기능(goods_sort 통해 검색)
	@Override
	public List<GoodsVO> selectGoodsList_bysort(String goodsSort ) throws DataAccessException {
		List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList_bysort",goodsSort);
	   return goodsList;	
     
	}
	
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
	   List<String> list=(ArrayList)sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);
	   return list;
	}
	
	@Override
	public ArrayList selectGoodsBySearchWord(String searchWord) throws DataAccessException{
		ArrayList list=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",searchWord);
		 return list;
	}
	
	@Override
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException{
		GoodsVO goodsVO=(GoodsVO)sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
		return goodsVO;
	}
	
	@Override
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException{
		List<ImageFileVO> imageList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
		return imageList;
	}
	
	@Override
	public void addReply(ReplyVO reply) throws DataAccessException{
		sqlSession.insert("mapper.goods.addReply", reply);
	}
	
	@Override
	public List<ReplyVO> replyList(String goods_id) throws DataAccessException{
		return sqlSession.selectList("mapper.goods.replyList", goods_id);
	}
	
	@Override
	public void deleteReply(ReplyVO reply) throws DataAccessException{
		sqlSession.delete("mapper.goods.deleteReply", reply);
	}
	
	@Override
	public String idCheck(int repNum) throws DataAccessException{
		return sqlSession.selectOne("mapper.goods.replyIdCheck", repNum);
	}
	
	@Override
	public void modifyReply(ReplyVO reply) throws DataAccessException{
		sqlSession.update("mapper.goods.modifyReply", reply);
	}
	
}
