package com.bookshop01.board.service;

import java.util.List;
import java.util.Map;

import com.bookshop01.board.vo.ArticleVO;
import com.bookshop01.board.vo.ReplyVO;

public interface BoardService {
	public List<ArticleVO> listArticles() throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public ArticleVO viewArticle(int articleNO) throws Exception;
	//public Map viewArticle(int articleNO) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int articleNO) throws Exception;
	public void addReply(Map replyMap) throws Exception;
	//reply로 저장하기 위해
	
	public int count() throws Exception;
	
	public List<ArticleVO> listPage(int displayPost, int postNum, String searchType, String keyword) throws Exception;
	
	// 게시물 총 개수 + 검색 적용
	public int searchCount(String searchType, String keyword) throws Exception;

	public List<ReplyVO> replyList(int articleNO) throws Exception;
}