package com.bookshop01.goods.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.goods.vo.ReplyVO;

public interface GoodsDAO {
	// 메인에서 사용했던 검색기능(goods_status 통해 검색)
	public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException;
	
	// 일반적인 검색기능(goods_sort 통해 검색)
	public List<GoodsVO> selectGoodsList_bysort(String goodsSort ) throws DataAccessException;
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException;
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException;
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;
	
	// 상품 댓글 추가
	public void addReply(ReplyVO reply) throws DataAccessException;
	// 상품 댓글 리스트
	public List<ReplyVO> replyList(String goods_id) throws DataAccessException;
	// 상품 댓글 삭제
	public void deleteReply(ReplyVO reply) throws DataAccessException;
	// 아이디 체크
	public String idCheck(int repNum) throws DataAccessException;
	// 상품 댓글 수정
	public void modifyReply(ReplyVO reply) throws DataAccessException;
	
}
