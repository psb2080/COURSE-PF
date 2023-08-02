package com.bookshop01.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ReplyVO;

public interface GoodsService {
	
	// 메인페이지 검색기능(goods_status 통해 검색)
	public Map<String,List<GoodsVO>> listGoods() throws Exception;
	
	// 일반적인 검색기능(goods_sort 통해 검색)
	public Map<String,List<GoodsVO>> listGoods_bysort() throws Exception;
	
	public Map goodsDetail(String _goods_id) throws Exception;
	
	public List<String> keywordSearch(String keyword) throws Exception;
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;
	
	// 상품 댓글 작성
	public void addReply(ReplyVO reply) throws Exception;
	// 상품 댓글 리스트
	public List<ReplyVO> replyList(String goods_id) throws Exception;
	// 상품 댓글 삭제
	public void deleteReply(ReplyVO reply) throws Exception;
	// 아이디 체크
	public String idCheck(int repNum) throws Exception;
	// 상품 댓글 수정
	public void modifyReply(ReplyVO reply) throws Exception;
}
