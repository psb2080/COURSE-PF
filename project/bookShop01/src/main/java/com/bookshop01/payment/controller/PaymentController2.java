package com.bookshop01.payment.controller;

import java.util.HashMap ;
import java.util.Map ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Controller ;
import org.springframework.web.bind.annotation.RequestMapping ;
import org.springframework.web.bind.annotation.RequestParam ;
import org.springframework.web.servlet.ModelAndView ;

import com.bookshop01.payment.service.PaymentService ;

@Controller
public class PaymentController2
{
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value="/payment/kakao/pay.do")
	public ModelAndView kakaoPay(@RequestParam Map < String, String > receiverMap) throws Exception{
		ModelAndView mav = new ModelAndView( );
		
		// 테스트해보기
		System.out.println( "PaymentController2(kakao/pay.do) : 인증 데이터 확인 = " + receiverMap.toString( ) ) ;
		
		// 인증 데이터 확인 = {ordr_idxx=20230601144727KK0615, good_name=�����׽�Ʈ, good_mny=100, buyr_name=choi, site_cd=A8QOB, req_tx=pay, pay_method=100000000000, currency=410, kakaopay_direct=Y, module_type=01, ordr_chk=20230601144727KK0615|100, param_opt_1=, param_opt_2=, param_opt_3=, res_cd=0000, res_msg=����, enc_info=3tFysq4qevjd2mvWfGF8YVma3.14orker7xpxHTdFPwQgjXok2aaRpEM71p2En2vJBCXp0V1ZhzbnfKuEGHGIMFtdqFYC1y3JTwiaAhNwTsv8z26Jcmcugv40X912pEw78P-XSRXItmo1izm4vgl3-Tynav0v8Xh0aBOhMMiUrSx6dg4pokNuhjoCg1ZpicebqXIT7YaxWY__, enc_data=40.6GONZW-itNra.KAjyMxkBs6qhpPZPPXKeqOam88tYOPGI878TpoHY0wBXDXs5Kf3l9Cd0Em8Ug19mCXFB3cfdP0GPy5gFIgmKOchFqBaGtUgiTxjD.mowJ7UZd2d5M6XWtqU0w5-ksW981CLyb0Dj5kyr-IgSHjD.E69QQiluXayQF6ZqJgqgA7P5PPJpsAxXZOxXkVIXVFaECn8nT0AAuSmmk23oFvRxQutLRdtimbx9DZbVGbfRAW5PnpG7VbP9VND4TmzosLDfX0ATe4jvCkXxWPSScNiOTV5whfXKnPXzVTaDAw9FZGjHSmQgRf2FhhitwLZSG3zChzaIJACSlmgevsPFN-.1XlgebFmv21BY-BLjieQns6f6by95Lz0DsooD66ZW.dfglbqDLTG0oFFD-DPGus4epxo6io--8NWcwbyzMCHDMN-5U3f47i0EZ632nJRNc93uqDsF2Wb4ZY.1jTL6tQWB4v7s5ryw0RYbm-9g7cMdCpfVJKTR6hPDKnV4wl3YvWYF3OstWgB-x3IwftN8KL5c074BQ7bEQIU-q.vRz.BvjGnKKdsPi62Ds2roofs7MQheOX3NlGyY-FuReMhr8HqjfE5vIfrR7pXATUVhSQ.Dlkl29c6qbs2zU.-MHfxe5JyGzJrp2jrOMZDiXh0ucb0IoMEg5Zud_, ret_pay_method=CARD, tran_cd=00100000, use_pay_method=100000000000, card_pay_method=KAKAO_MONEY}
		Map<String,String> resultMap = new HashMap<String, String>();
		
		resultMap = paymentService.kakaoPay( receiverMap );
		
		//resultMap에 승인 결과 내용이 담겨있음
		
		String responseCode = resultMap.get( "responseCode" );
		//String responseCode = "0000";  //화면 테스트코드
		
		if ("0000".equals( responseCode )) {
			//승인완료
			
			//어떤 페이지로 갈지 설정
			mav.setViewName( "/payment/kakaoS" );
			
			//결제성공 관련 데이터 넣어서
			//System.out.printf( "resultMap 데이터 확인 : ", resultMap.toString( ) ) ;
			//mav.addObject( "amount", resultMap.get( "amount" ));
			
			//결과값 통으로 넣기
			mav.addObject( "kakaoData", resultMap);
					
		}else {
			//승인실패
			mav.setViewName( "/payment/kakaoF" );
		}
		
		
		
		return mav;
	}
	
	@RequestMapping(value="/payment/naver/pay.do")
	public ModelAndView naverPay(@RequestParam Map < String, String > receiverMap) throws Exception{
		ModelAndView mav = new ModelAndView( );
		
		// 테스트해보기
		System.out.println( "PaymentController2(naver/pay.do)인증 데이터 확인(receiverMap) = " + receiverMap.toString( ) ) ;
		
		// 인증 데이터 확인 = {ordr_idxx=20230622173120NV0144, good_name=�����׽�Ʈ, good_mny=100, buyr_name=choi, site_cd=A8QOB, req_tx=pay, pay_method=100000000000, currency=410, module_type=01, kakaopay_direct=, naverpay_direct=Y, naverpay_point_direct=, ordr_chk=20230622173120NV0144|100, param_opt_1=, param_opt_2=, param_opt_3=, res_cd=0000, res_msg=����, enc_info=0-gedUtOlu76nSZg0LwDZdf5krqwVw1x89BedbUs9fN.xEpRa0vgDNLZZuWxrRaLNsJz9nsSzpg4CT8iP1oVLT9fsGZfYM2orB6S2mbiCZWxCSaMFmpeScQ4wRbnPuWH2SmrD4zYUl141U-w1hYSyYTQocgPm8l52HFqwvbq5.va4OPI03SyZ-Lu6fk0EmMk.o.20u144iG__, enc_data=1A6tkmsPdqPCa5UtJtqUsAOrH524zU8q7Ng-yf65HmHN-pok3yyZE8cFRnDJcnlyEtPZgfdcbGDghmOf5KoL173qLi8l.BoMuWY6xeWYM3bre.18gQLwoIYulbkQ2sdW00cblRbL29n1Lfx9BattyvJL0BGtWqkjJXD6S-5T-n7fJm42GDMk5IdICiXhwZOVFkwNE25f2QJL5mfzBO1pjG1nILWnxIuZ8czj1wfspspf5PwzHczT7kZiqrnaHPAXYjzX.AAQi8YhApo5FDeyvTjv173qVInFGAwqnwybj.c0J0VF2H6hm-dVyKykXAFGmIAv9mQhURgiafYdw8LLECR4WEa2Fg0-pAPMCOmqPMJjym1tMhNxG-LT-TWi-ZNnIpV18k.4n9QB5h60lY0rpKxa7wIWtiXO78xBiWXnkeshIxafFeTrbr15S1GafiZ29gmanL4Gedo6hbddydbCXDC1eQ5d.bGNzNORahE1kbpcu.fk7gu5usXUN3429y4jXTKC2qpwl5B1BTE5o438BC2a90D8g8s2vwHpjg7iXUDcOUsVS2-CV2lTETrqs6E-14mVMh47MxngRiTE9Wo.OzNsVGSkkNz8lDPpkIAJd0Fy_, ret_pay_method=CARD, tran_cd=00100000, use_pay_method=100000000000, card_pay_method=NAVERPAY_CARD}
		Map<String,String> resultMap = new HashMap<String, String>();
		
		resultMap = paymentService.naverPay( receiverMap );
		
		//resultMap에 승인 결과 내용이 담겨있음
		
		String responseCode = resultMap.get( "responseCode" );
		//String responseCode = "0000";  //화면 테스트코드
		
		System.out.println( "PaymentController2(naver/pay.do)승인결과 데이터 확인(resultMap) = " + resultMap.toString( )) ;
		
		if ("0000".equals( responseCode )) {
			//승인완료
			
			//어떤 페이지로 갈지 설정
			mav.setViewName( "/payment/naverS" );
			
			//결제성공 관련 데이터 넣어서
			//System.out.printf( "resultMap 데이터 확인 : ", resultMap.toString( ) ) ;
			//mav.addObject( "amount", resultMap.get( "amount" ));
			
			//결과값 통으로 넣기
			mav.addObject( "naverData", resultMap);
					
		}else {
			//승인실패
			mav.setViewName( "/payment/naverF" );
		}
		
		
		
		return mav;
	}

}