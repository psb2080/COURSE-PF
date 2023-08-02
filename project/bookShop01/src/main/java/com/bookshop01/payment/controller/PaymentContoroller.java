package com.bookshop01.payment.controller;

import java.util.HashMap ;
import java.util.Map ;

import org.apache.commons.collections.map.HashedMap ;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RequestMethod ;
import org.springframework.web.bind.annotation.RequestParam ;
import org.springframework.web.bind.annotation.RestController ;

import com.bookshop01.payment.service.PaymentService ;

//@controller랑 @RestController는 다릅니다.
@RestController
public class PaymentContoroller
{
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value="/payment/kakao/order.do")
	public Map < String, String > kakaoOrder(@RequestParam Map < String, String > receiverMap) throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		
		//제대로 들어오는지 확인 -> 이거 많이씀
		System.out.println("PaymentContorller(kakao/order.do) : kakaoOrder 확인");
		
		//주문 API
		//resultMap 주문결과를 넣음
		
		resultMap = paymentService.kakaoOrder(receiverMap);
		
		System.out.println("PaymentContorller(kakao/order.do) : resultMap = " + resultMap);
		
		//결과가 제대로 나가는지 테스트 데이터
		//{good_mny=100, site_cd=A8QOB, Ret_URL=test, affiliaterCode=0005, buyr_name=choi, ordr_idxx=20230601111104KK0292, good_name=강의테스트, responseCode=0000, responseMsg=성공}
		//resultMap.put("test", "test값");
				
		return resultMap;   //json 형태로 데이터가 나가게 됨
	}
	
	@RequestMapping(value="/payment/naver/order.do")
	public Map < String, String > naverOrder(@RequestParam Map < String, String > receiverMap) throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		
		//제대로 들어오는지 확인 -> 이거 많이씀
		System.out.println("PaymentContorller(naver/order.do) : naverOrder 확인");
		
		//주문 API
		//resultMap 주문결과를 넣음
		
		resultMap = paymentService.naverOrder(receiverMap);
		
		//결과가 제대로 나가는지 테스트 데이터
		//{good_mny=100, site_cd=A8QOB, Ret_URL=test, affiliaterCode=0005, buyr_name=choi, ordr_idxx=20230601111104KK0292, good_name=강의테스트, responseCode=0000, responseMsg=성공}
		//resultMap.put("test", "test값");
				
		return resultMap;   //json 형태로 데이터가 나가게 됨
	}

}
