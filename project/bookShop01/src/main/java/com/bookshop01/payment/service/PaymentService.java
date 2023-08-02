package com.bookshop01.payment.service;

import java.security.MessageDigest ;
import java.security.NoSuchAlgorithmException ;
import java.util.HashMap ;
import java.util.Map ;

import javax.print.attribute.standard.JobOriginatingUserName ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service ;
import org.springframework.web.bind.annotation.RequestParam ;

import com.bookshop01.api.service.ApiService ;

@Service
public class PaymentService {
	
	@Autowired
	private ApiService apiService;
	
	public Map<String, String> keyin(Map<String, String> receiverMap){
		
		//Ctrl + Shift + O(영어) 자동완성
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		//API 연동 소스 작성 예정
		
		//rest api 연동을 할 예정
		//라이브러리를 사용해서 연동
		
		String orderNumber = ""; //주문번호
		String cardNo = "";  //카드번호
		String expireMonth = "";  //유효기간(월) 2자리
		String expireYear = "";  //유효기간(년) 2자리
		String birthday = "";  //생년월일
		String cardPw = "";
		String amount = "";
		String quota = "";
		String itemName = "";
		String userName = "";
		String signature = "";
		String timestamp = "";
		String certKey = "ac805b30517f4fd08e3e80490e559f8e";
		
		// 보내야 되는 값 세팅 예정
		//receiverMap.get( "cardNo" );
		orderNumber = "TEST1234";
		cardNo = receiverMap.get( "cardNo" );
		expireMonth = receiverMap.get( "expireMonth" );
		expireYear = receiverMap.get( "expireYear" );
		birthday = receiverMap.get( "birthday" );
		cardPw = receiverMap.get( "cardPw" );
		amount = "1000";
		quota = "0";
		itemName = "아이템";
		userName = "테스터";
		timestamp = "202305311010112";
		
		//암호화해서 행성해야 함
		try {
			
			signature = encrypt("himedia|"+orderNumber+"|"+amount+"|"+certKey+"|"+timestamp);
			
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace( );
			
		}
		
		
		
		//
		
		//paramMap은 요청값들이 담길 맵
		Map < String, String > paramMap = new HashMap < String, String >( );
		//요청 주소
		String url = "https://api.testpayup.co.kr/v2/api/payment/himedia/keyin2";
		
		paramMap.put("orderNumber",orderNumber);
		paramMap.put("cardNo",cardNo);
		paramMap.put("expireMonth",expireMonth);
		paramMap.put("expireYear",expireYear);
		paramMap.put("birthday",birthday);
		paramMap.put("cardPw",cardPw);
		paramMap.put("amount",amount);
		paramMap.put("quota",quota);
		paramMap.put("itemName",itemName);
		paramMap.put("userName",userName);
		paramMap.put("signature",signature);
		paramMap.put("timestamp",timestamp);
		
		resultMap = apiService.restApi( paramMap, url );
		
		
		//연동 결과
		System.out.println( "paymentservice(keyin) : 결재 승인 API 통신 결과(resultMap) =" + resultMap.toString( ) ) ;
		
		return resultMap;
	}
	
	//카카오 주문 하는 기능
	public Map<String,String> kakaoOrder(Map<String,String> receiverMap){
		
		Map<String,String> resultMap = new HashMap<String, String>();
		
		
		//API 통신
		//카카오 3.1 주문요청
		
		//요청 데이터 설정
		String orderNumber = "";
		String userAgent = "";
		String amount = "";
		String itemName = "";
		String userName = "";
		String returnUrl = "";
		String signature = "";
		String timestamp = "";
		String certKey = "ac805b30517f4fd08e3e80490e559f8e";
		
		orderNumber = "TEST_ORDER";
		userAgent = "WP";
		amount = "100"; //실결제 됩니다... 금액 크게하면 안되요
		itemName = "강의테스트";
		userName = "choi";
		returnUrl = "test"; //이거는 아무값이나 넣어도됩니다..
		timestamp = "20230601111111";
		//암호화해서 생성해야함.
		try {
			signature = encrypt("himedia|"+orderNumber+"|"+amount+"|"+certKey+"|"+timestamp);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//paramMap은 요청값들이 담길 맵
		Map<String,String> paramMap = new HashMap<String, String>();
		//요청 주소
		String url = "https://api.testpayup.co.kr/ep/api/kakao/himedia/order";
		paramMap.put("orderNumber",orderNumber);
		paramMap.put("userAgent",userAgent);
		paramMap.put("amount",amount);
		paramMap.put("itemName",itemName);
		paramMap.put("userName",userName);
		paramMap.put("returnUrl",returnUrl);
		paramMap.put("amount",amount);
		paramMap.put("timestamp",timestamp);
		paramMap.put("signature",signature);
		
		resultMap = apiService.restApi(paramMap, url);
		// map 변환된 값 = {good_mny=100, site_cd=A8QOB, Ret_URL=test, affiliaterCode=0005, buyr_name=choi, ordr_idxx=20230623110620KK0163, good_name=강의테스트, responseCode=0000, responseMsg=성공}
		
		
		//연동 결과
		System.out.println("PaymentService(kakaoOrder) : 카카오 주문 API 통신 결과(resultMap) = " + resultMap.toString());
		
		return resultMap;
	}
	
	//카카오 승인 요청
	public Map<String,String> kakaoPay(Map<String,String> receiverMap){
		
		Map<String,String> resultMap = new HashMap<String, String>();
		
		
		//3.3 승인요청 전문 보고 아래 소스코드 완성하기
		
		//인증 데이터 확인 = {ordr_idxx=20230601144727KK0615, good_name=�����׽�Ʈ, good_mny=100, buyr_name=choi, site_cd=A8QOB, 
		//req_tx=pay, pay_method=100000000000, currency=410, kakaopay_direct=Y, module_type=01, 
		//ordr_chk=20230601144727KK0615|100, param_opt_1=, param_opt_2=, param_opt_3=, res_cd=0000, res_msg=����, 
		//enc_info=3tFysq4qevjd2mvWfGF8YVma3.14orker7xpxHTdFPwQgjXok2aaRpEM71p2En2vJBCXp0V1ZhzbnfKuEGHGIMFtdqFYC1y3JTwiaAhNwTsv8z26Jcmcugv40X912pEw78P-XSRXItmo1izm4vgl3-Tynav0v8Xh0aBOhMMiUrSx6dg4pokNuhjoCg1ZpicebqXIT7YaxWY__, 
		//enc_data=40.6GONZW-itNra.KAjyMxkBs6qhpPZPPXKeqOam88tYOPGI878TpoHY0wBXDXs5Kf3l9Cd0Em8Ug19mCXFB3cfdP0GPy5gFIgmKOchFqBaGtUgiTxjD.mowJ7UZd2d5M6XWtqU0w5-ksW981CLyb0Dj5kyr-IgSHjD.E69QQiluXayQF6ZqJgqgA7P5PPJpsAxXZOxXkVIXVFaECn8nT0AAuSmmk23oFvRxQutLRdtimbx9DZbVGbfRAW5PnpG7VbP9VND4TmzosLDfX0ATe4jvCkXxWPSScNiOTV5whfXKnPXzVTaDAw9FZGjHSmQgRf2FhhitwLZSG3zChzaIJACSlmgevsPFN-.1XlgebFmv21BY-BLjieQns6f6by95Lz0DsooD66ZW.dfglbqDLTG0oFFD-DPGus4epxo6io--8NWcwbyzMCHDMN-5U3f47i0EZ632nJRNc93uqDsF2Wb4ZY.1jTL6tQWB4v7s5ryw0RYbm-9g7cMdCpfVJKTR6hPDKnV4wl3YvWYF3OstWgB-x3IwftN8KL5c074BQ7bEQIU-q.vRz.BvjGnKKdsPi62Ds2roofs7MQheOX3NlGyY-FuReMhr8HqjfE5vIfrR7pXATUVhSQ.Dlkl29c6qbs2zU.-MHfxe5JyGzJrp2jrOMZDiXh0ucb0IoMEg5Zud_, 
		//ret_pay_method=CARD, tran_cd=00100000, use_pay_method=100000000000, card_pay_method=KAKAO_MONEY}
		//receiverMap 에 인증데이터 있음
		//요청 데이터 설정
		
		String res_cd = receiverMap.get( "res_cd" );
		String enc_info = receiverMap.get( "enc_info" );
		String enc_data = receiverMap.get( "enc_data" );
		String card_pay_method = receiverMap.get( "card_pay_method" );
		String ordr_idxx = receiverMap.get( "ordr_idxx" );
		String tran_cd = receiverMap.get( "tran_cd" );
		
		/*
		String returnUrl = "";
		String signature = "";
		String timestamp = "";
		String certKey = "ac805b30517f4fd08e3e80490e559f8e";
		
		orderNumber = "TEST_ORDER";
		userAgent = "WP";
		amount = "100"; //실결제 됩니다... 금액 크게하면 안되요
		itemName = "강의테스트";
		userName = "choi";
		returnUrl = "test"; //이거는 아무값이나 넣어도됩니다..
		timestamp = "20230601111111";
		
		//암호화해서 생성해야함.
		try {
			signature = encrypt("himedia|"+orderNumber+"|"+amount+"|"+certKey+"|"+timestamp);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//paramMap은 요청값들이 담길 맵
		Map<String,String> paramMap = new HashMap<String, String>();
		//요청 주소
		String url = "https://api.testpayup.co.kr/ep/api/kakao/himedia/pay";
		paramMap.put("res_cd",res_cd);
		paramMap.put("enc_info",enc_info);
		paramMap.put("enc_data",enc_data);
		paramMap.put("card_pay_method",card_pay_method);
		paramMap.put("ordr_idxx",ordr_idxx);
		paramMap.put("tran_cd",tran_cd);
		
		//paramMap.put("returnUrl",returnUrl);
		//paramMap.put("amount",amount);
		//paramMap.put("timestamp",timestamp);
		//paramMap.put("signature",signature);
		
		resultMap = apiService.restApi(paramMap, url);
		
		//연동 결과
		System.out.println("paymentservice(kakaoPay) : 카카오 PAY API 통신 결과 = " + resultMap.toString());		
		//카카오 PAY API 통신 결과 = {amount=100, orderNumber=TEST_ORDER, type=KAKAO_MONEY, authDateTime=20230601165000, 
		//cashReceipt=100, transactionId=20230601164936KK0759, responseCode=0000, responseMsg=정상처리}
		
		return resultMap;
	}
	
	//네이버 주문요청 추가
	public Map<String,String> naverOrder(Map<String,String> receiverMap){
		
		Map<String,String> resultMap = new HashMap<String, String>();
		
		
		//API 통신
		//네이버 3.1 주문요청
		
		//요청 데이터 설정
		String orderNumber = "";
		String userAgent = "";
		
		//네이버 전용
		String payType = "";
		
		String amount = "";
		String itemName = "";
		String userName = "";
		String returnUrl = "";
		
		//네이버 전용
		String userEmail = "";
		String mobileNumber = "";
		String cashbillYn = "";
		
		String signature = "";
		String timestamp = "";
		String certKey = "ac805b30517f4fd08e3e80490e559f8e";
		
		orderNumber = "TEST_ORDER";
		userAgent = "WP";
		// 결재 타입이 여러가이므로 주석처리
		// payType = "CARD";
		
		amount = "100"; //실결제 됩니다... 금액 크게하면 안되요
		itemName = "강의테스트";
		userName = "choi";
		returnUrl = "test"; //이거는 아무값이나 넣어도됩니다..
		
		userEmail = "abc@abc.com";
		mobileNumber = "01011112222";
		cashbillYn = "N";
		
		timestamp = "20230601111111";
		//암호화해서 생성해야함.
		try {
			signature = encrypt("himedia|"+orderNumber+"|"+amount+"|"+certKey+"|"+timestamp);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//paramMap은 요청값들이 담길 맵
		Map<String,String> paramMap = new HashMap<String, String>();
		//요청 주소
		String url = "https://api.testpayup.co.kr/ep/api/naver/himedia/order";
		paramMap.put("orderNumber",orderNumber);
		paramMap.put("userAgent",userAgent);
		
		//네이버 전용
		paramMap.put("payType",payType);
		paramMap.put("amount",amount);
		paramMap.put("itemName",itemName);
		paramMap.put("userName",userName);
		paramMap.put("returnUrl",returnUrl);
		
		//네이버 전용
		paramMap.put("userEmail",userEmail);
		paramMap.put("mobileNumber",mobileNumber);
		paramMap.put("cashbillYn",cashbillYn);
				
		paramMap.put("timestamp",timestamp);
		paramMap.put("signature",signature);
		resultMap = apiService.restApi(paramMap, url);
		
		//연동 결과
		System.out.println("paymentservice(naverOrder) : 네이버 주문 API 통신 결과 = " + resultMap.toString());
		return resultMap;
	}
	
	//네이버 승인 요청
	public Map<String,String> naverPay(Map<String,String> receiverMap){
		
		Map<String,String> resultMap = new HashMap<String, String>();
		
		
		//3.3 승인요청 전문 보고 아래 소스코드 완성하기
		

		//receiverMap 에 인증데이터 있음
		//요청 데이터 설정
		
		/*
		String res_cd = receiverMap.get( "res_cd" );
		String enc_info = receiverMap.get( "enc_info" );
		String enc_data = receiverMap.get( "enc_data" );
		String ordr_idxx = receiverMap.get( "ordr_idxx" );
		String tran_cd = receiverMap.get( "tran_cd" );
		*/
		
		/*
		String card_pay_method = receiverMap.get( "card_pay_method" );
		String res_msg = receiverMap.get( "res_msg" );
		String good_mny = receiverMap.get( "good_mny" );
		String good_name = receiverMap.get( "good_name" );
		String site_cd = receiverMap.get( "site_cd" );
		String buyr_name = receiverMap.get( "buyr_name" );
		String naverpay_direct = receiverMap.get( "naverpay_direct" );
		String naverpay_point_direct = receiverMap.get( "naverpay_point_direct" );
		String ret_pay_method = receiverMap.get( "ret_pay_method" );
		*/
		
		
		/*
		String returnUrl = "";
		String signature = "";
		String timestamp = "";
		String certKey = "ac805b30517f4fd08e3e80490e559f8e";
		
		orderNumber = "TEST_ORDER";
		userAgent = "WP";
		amount = "100"; //실결제 됩니다... 금액 크게하면 안되요
		itemName = "강의테스트";
		userName = "choi";
		returnUrl = "test"; //이거는 아무값이나 넣어도됩니다..
		timestamp = "20230601111111";
		
		//암호화해서 생성해야함.
		try {
			signature = encrypt("himedia|"+orderNumber+"|"+amount+"|"+certKey+"|"+timestamp);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//paramMap은 요청값들이 담길 맵
		//Map<String,String> paramMap = new HashMap<String, String>();
		//요청 주소
		
		/*
		paramMap.put("res_cd",res_cd);
		paramMap.put("enc_info",enc_info);
		paramMap.put("enc_data",enc_data);
		paramMap.put("ordr_idxx",ordr_idxx);
		paramMap.put("tran_cd",tran_cd);
		*/
		
		String url = "https://api.testpayup.co.kr/ep/api/naver/himedia/pay";
		//resultMap = apiService.restApi(paramMap, url);
		resultMap = apiService.restApi(receiverMap, url);

		
		//연동 결과
		System.out.println("paymentservice(naverPay) : 네이버 PAY API 통신 결과 = " + resultMap.toString());		
		
		return resultMap;
	}	
	
	
	public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());
        return bytesToHex(md.digest());
    }
	
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
	
}
