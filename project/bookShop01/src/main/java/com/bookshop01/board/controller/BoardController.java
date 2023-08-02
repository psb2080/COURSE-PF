package com.bookshop01.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


public interface BoardController {
	
	public ModelAndView listArticles(@RequestParam("num") int num,
									 @RequestParam("searchType") String searchType, 
									 @RequestParam("keyword") String keyword, 
									 HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
	public ModelAndView viewArticle(@RequestParam("num") int num,
									@RequestParam("articleNO") int articleNO,
									@RequestParam("searchType") String searchType, 
									@RequestParam("keyword") String keyword, 
			                        HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ResponseEntity modArticle(@RequestParam("num") int num,
									 MultipartHttpServletRequest multipartRequest,  
									 HttpServletResponse response) throws Exception;
	
	public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
										HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ResponseEntity addReply(@RequestParam("articleNO") int articleNO,
								   MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
//reply add function
}