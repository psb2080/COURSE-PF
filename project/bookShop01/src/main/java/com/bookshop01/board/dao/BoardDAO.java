package com.bookshop01.board.dao;

import java.util.List;
import java.util.Map;

import com.bookshop01.board.vo.ReplyVO;
import org.springframework.dao.DataAccessException;

import com.bookshop01.board.vo.ArticleVO;


public interface BoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	//public void insertNewImage(Map articleMap) throws DataAccessException;
	
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	public List selectImageFileList(int articleNO) throws DataAccessException;

	public void insertNewReply(Map replyMap) throws DataAccessException;
	// reply add function
	public List<ReplyVO> selectReply(int articleNO) throws DataAccessException;
	
	public int count() throws DataAccessException;
	public List<ArticleVO> listPage(int displayPost, int postNum, String searchType, String keyword) throws DataAccessException;
	
	public int searchCount(String searchType, String keyword) throws DataAccessException;
}