package com.bookshop01.mypage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface MyPageController {
	public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message,HttpServletRequest request, HttpServletResponse response)  throws Exception ;
	public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
					            @RequestParam("value")  String value,
					            HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 회원탈퇴
	public ModelAndView delMember(@RequestParam(required = false,value="message")  String message,HttpServletRequest request, HttpServletResponse response)  throws Exception ;
	
	// 교환/반품 > 리스트 2023.6.19 by Dean	
	public ModelAndView listChangeMyOrderStatus(@RequestParam Map<String, String> dateMap,
            HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 교환/반품 > 교환 등록 전처리  
	public ModelAndView exchangeMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 교환/반품 > 교환등록	
	public ModelAndView addExChangeMyOrder(@RequestParam Map<String, String> receiverMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	// 교환/반품 > 반품 등록 전처리  by Dean 23.07.03
	public ModelAndView returnMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception;
	// 교환/반품 > 반품등록  by Dean 23.06.30
	public ModelAndView addReturnMyOrder(@RequestParam Map<String, String> receiverMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;			
}