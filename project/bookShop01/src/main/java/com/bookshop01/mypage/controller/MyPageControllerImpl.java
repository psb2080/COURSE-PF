package com.bookshop01.mypage.controller;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bookshop01.admin.order.service.AdminOrderService;
import com.bookshop01.common.base.BaseController;
import com.bookshop01.common.log.LoggingAdvice ;
import com.bookshop01.member.vo.MemberVO;
import com.bookshop01.mypage.service.MyPageService;
import com.bookshop01.order.vo.OrderVO;

import com.bookshop01.mypage.vo.ExchangeOrderVO;
import com.bookshop01.mypage.vo.ReturnOrderVO;


@Controller("myPageController")
@RequestMapping(value="/mypage")
public class MyPageControllerImpl extends BaseController  implements MyPageController{
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private AdminOrderService adminOrderService;	
	
	// 援먰솚泥섎━�쐞�빐 異붽� by Dean 230627
	@Autowired
	private OrderVO orderVO;
	
	// 교환VO by Dean 230627
	@Autowired
	private ExchangeOrderVO exchangeOrderVO;
	
	// 반품VO by Dean 230630
	@Autowired
	private ReturnOrderVO returnOrderVO;
	
	// 로그출력을 위한 선언 ( By Dean )
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

	private static final int List = 0;
	
	@Override
	@RequestMapping(value="/myPageMain.do" ,method = RequestMethod.GET)
	public ModelAndView myPageMain(@RequestParam(required = false,value="message")  String message,   // 二쇰Ц痍⑥냼�떆 寃곌낵 硫붿떆吏� 諛쏆쓬
			   HttpServletRequest request, HttpServletResponse response)  throws Exception {
		HttpSession session=request.getSession();
		session=request.getSession();
		session.setAttribute("side_menu", "my_page"); // 타일즈 메뉴에 추가되는 위치
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id=memberVO.getMember_id();
		
		List<OrderVO> myOrderList=myPageService.listMyOrderGoods(member_id);  // �쉶�썝ID瑜� �씠�슜�빐�꽌 二쇰Ц �긽�뭹 議고쉶
		
		mav.addObject("message", message);          // 二쇰Ц痍⑥냼�떆 寃곌낵 硫붿떆吏�瑜� JSP濡� �쟾�떖
		mav.addObject("myOrderList", myOrderList); // jsp파일에서 보내지는 List<OrderVo> myOrderList

		return mav;
	}
	
	@Override
	@RequestMapping(value="/myOrderDetail.do" ,method = RequestMethod.GET)
	public ModelAndView myOrderDetail(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		MemberVO orderer=(MemberVO)session.getAttribute("memberInfo");
		
		List<OrderVO> myOrderList=myPageService.findMyOrderInfo(order_id);
		mav.addObject("orderer", orderer);
		mav.addObject("myOrderList",myOrderList);
		return mav;
	}

	@Override
	@RequestMapping(value="/listMyOrderHistory.do" ,method = RequestMethod.GET)
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap,
			                               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		String beginDate=null,endDate=null;
		
		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);
		dateMap.put("member_id", member_id);
		List<OrderVO> myOrderHistList=myPageService.listMyOrderHistory(dateMap);
		
		String beginDate1[]=beginDate.split("-"); //寃��깋�씪�옄瑜� �뀈,�썡,�씪濡� 遺꾨━�빐�꽌 �솕硫댁뿉 �쟾�떖�빀�땲�떎.
		String endDate1[]=endDate.split("-");
		mav.addObject("beginYear",beginDate1[0]);
		mav.addObject("beginMonth",beginDate1[1]);
		mav.addObject("beginDay",beginDate1[2]);
		mav.addObject("endYear",endDate1[0]);
		mav.addObject("endMonth",endDate1[1]);
		mav.addObject("endDay",endDate1[2]);
		mav.addObject("myOrderHistList", myOrderHistList);
		return mav;
	}	
	
	@Override
	@RequestMapping(value="/cancelMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		myPageService.cancelOrder(order_id);
		mav.addObject("message", "cancel_order");
		mav.setViewName("redirect:/mypage/myPageMain.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/myDetailInfo.do" ,method = RequestMethod.GET)
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}	
	
	@Override
	@RequestMapping(value="/modifyMyInfo.do" ,method = RequestMethod.POST)
	public ResponseEntity modifyMyInfo(@RequestParam("attribute")  String attribute,
			                 @RequestParam("value")  String value,
			               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		Map<String,String> memberMap=new HashMap<String,String>();
		String val[]=null;
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		if(attribute.equals("member_birth")){
			val=value.split(",");
			memberMap.put("member_birth_y",val[0]);
			memberMap.put("member_birth_m",val[1]);
			memberMap.put("member_birth_d",val[2]);
			memberMap.put("member_birth_gn",val[3]);
		}else if(attribute.equals("tel")){
			val=value.split(",");
			memberMap.put("tel1",val[0]);
			memberMap.put("tel2",val[1]);
			memberMap.put("tel3",val[2]);
		}else if(attribute.equals("hp")){
			val=value.split(",");
			memberMap.put("hp1",val[0]);
			memberMap.put("hp2",val[1]);
			memberMap.put("hp3",val[2]);
			memberMap.put("smssts_yn", val[3]);
		}else if(attribute.equals("email")){
			val=value.split(",");
			memberMap.put("email1",val[0]);
			memberMap.put("email2",val[1]);
			memberMap.put("emailsts_yn", val[2]);
		}else if(attribute.equals("address")){
			val=value.split(",");
			memberMap.put("zipcode",val[0]);
			memberMap.put("roadAddress",val[1]);
			memberMap.put("jibunAddress", val[2]);
			memberMap.put("namujiAddress", val[3]);
		}else {
			memberMap.put(attribute,value);	
		}
		
		memberMap.put("member_id", member_id);
		
		//�닔�젙�맂 �쉶�썝 �젙蹂대�� �떎�떆 �꽭�뀡�뿉 ���옣�븳�떎.
		memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		message  = "mod_success";
		resEntity =new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}	
	
	// 留덉씠�럹�씠吏� 異붽�
	@Override
	@RequestMapping(value="/delMember.do" ,method = RequestMethod.POST)
	public ModelAndView delMember(@RequestParam(required = false,value="message")  String message,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		HttpSession session=request.getSession();
		session=request.getSession();
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id=memberVO.getMember_id();
		myPageService.delMember(member_id);
		
		mav.addObject("member_id", member_id);
		//mav.addObject("message", "cancel_order");
		
		session.removeAttribute("memberInfo");
		
		mav.setViewName("redirect:/main/main.do");
		return mav;
		
	}

	// 留덉씠�럹�씠吏� > 二쇰Ц 由ъ뒪�듃 ( 23.06.22 by Dean listChangeMyOrderStatus.jsp�뿉�꽌 �샇異�)	
	@Override
	@RequestMapping(value="/listChangeMyOrderStatus" ,method = RequestMethod.GET)	
	public ModelAndView listChangeMyOrderStatus(@RequestParam Map<String, String> dateMap,
            HttpServletRequest request, HttpServletResponse response)  throws Exception {	
		HttpSession session=request.getSession();
		session=request.getSession();
		session.setAttribute("side_menu", "my_page"); //留덉씠�럹�씠吏� �쇊履� 硫붾돱濡� �꽕�젙.
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();
		
		// �럹�씠吏� 蹂��닔
		String section = dateMap.get("section");
		String pageNum = dateMap.get("pageNum");
		int    totPageCnt  = 0;
		
		String beginDate=dateMap.get("beginDate");
		String endDate=dateMap.get("endDate");		
		String beginYear=null;
		String beginMonth=null;
		String beginDay=null;
		String endYear=null;
		String endMonth=null;
		String endDay=null;
		String search_type = dateMap.get("search_type");
		String search_word = dateMap.get("search_word");
		String delivery_state = dateMap.get("delivery_state");
		DecimalFormat df = new DecimalFormat("00");
		
		dateMap.put("member_id", member_id);
		
		if ( "".equals(beginDate) ||  beginDate == null )
		{			
			Calendar cal=Calendar.getInstance();
			
			beginYear   = Integer.toString(cal.get(Calendar.YEAR));			
			beginMonth  = df.format(cal.get(Calendar.MONTH) - 1);		
			beginDay   = df.format(cal.get(Calendar.DATE));			
			beginDate = beginYear +"-"+ beginMonth +"-"+beginDay;
			
			endYear   = Integer.toString(cal.get(Calendar.YEAR));			
			endMonth  = df.format(cal.get(Calendar.MONTH) + 1);			
			endDay   = df.format(cal.get(Calendar.DATE));			
			endDate = endYear +"-"+ endMonth +"-"+endDay;	
			
			dateMap.put("beginDate", beginDate);
			dateMap.put("endDate", endDate);			
		}
			
		logger.info(">>>>>1.dateMap="  + dateMap.toString());	
			
		// �럹�씠吏� 愿��젴
		Map<String,Object> condMap=new HashMap<String,Object>();
		if(section== null) {
			section = "1";
		}
		dateMap.put("section",section);
		
		if(pageNum== null) {
			pageNum = "1";
		}
		dateMap.put("pageNum",pageNum);				
		
		List<OrderVO> myOrderList=myPageService.listChangeMyOrderStatus(dateMap);		
		
		String beginDate1[]=beginDate.split("-"); //野껓옙占쎄퉳占쎌뵬占쎌쁽�몴占� 占쎈��,占쎌뜞,占쎌뵬嚥∽옙 �겫袁ⓥ봺占쎈퉸占쎄퐣 占쎌넅筌롫똻肉� 占쎌읈占쎈뼎占쎈�占쎈빍占쎈뼄.
		String endDate1[]=endDate.split("-");
		
		beginYear = beginDate1[0];
		beginMonth = beginDate1[1]; //  df.format(Integer.parseInt(beginDate1[1]));				
		beginDay = beginDate1[2];   //  df.format(Integer.parseInt(beginDate1[2]));	
		
		endYear = endDate1[0];
		endMonth = endDate1[1];     // df.format(Integer.parseInt(endDate1[1]));			
		endDay =  endDate1[2];      // df.format(Integer.parseInt(endDate1[2]));		
				
		mav.addObject("beginYear",beginYear);
		mav.addObject("beginMonth",beginMonth);		
		mav.addObject("beginDay",beginDay);
		mav.addObject("beginDate",beginDate);
		
		mav.addObject("endYear",endYear);
		mav.addObject("endMonth",endMonth);
		mav.addObject("endDay",endDay);			
		mav.addObject("endDate",endDate);
		
		mav.addObject("search_type",search_type);
		mav.addObject("search_word",search_word);
		mav.addObject("delivery_state",delivery_state);
		
		// �럹�씠吏� �씤�옄媛� 29 30
		if ( myOrderList.size() > 0 )
			totPageCnt = myOrderList.get(0).getTotPageCnt();		
			
		System.out.println("totPageCnt="+totPageCnt+ ", myOrderList.size()="+ myOrderList.size());
		mav.addObject("section", section);
		mav.addObject("pageNum", pageNum);
		mav.addObject("totCnt", totPageCnt);
		
		mav.addObject("myOrderList", myOrderList);
	
		return mav;
	}
	
    // 留덉씠�럹�씠吏� > 援먰솚 �떊泥� 由ъ뒪�듃 ( 23.06.22 by Dean )	
	@Override 
	@RequestMapping(value="/exchangeMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView exchangeMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		MemberVO orderer=(MemberVO)session.getAttribute("memberInfo");		
		String  member_id=orderer.getMember_id();
		
		logger.info("�쉶�썝ID:" + member_id);
		logger.info("viewName:" + viewName);
		logger.info("order_id:" + order_id);
		List<OrderVO> myOrderList=myPageService.findMyOrderInfo(order_id);	
		
		mav.addObject("orderer", orderer);
		mav.addObject("message", "exchange_req");
		mav.addObject("myOrderList",myOrderList);
		return mav;
	}	
	
	// 留덉씠�럹�씠吏� > 援먰솚 �떊泥�泥섎━ ( 23.06.27 by Dean )
	@Override
	@RequestMapping(value="/addExChangeMyOrder.do" ,method={RequestMethod.POST})	
	public ModelAndView addExChangeMyOrder(@RequestParam Map<String, String>  receiverMap,
			                       HttpServletRequest request, HttpServletResponse response)  throws Exception{	
		
		request.setCharacterEncoding("utf-8");	
		
		String viewName=(String)request.getAttribute("viewName");		
		ModelAndView mav = new ModelAndView(viewName);
		
		/*
		logger.info("receiverMap = "  + receiverMap.toString());		
		
		private int exchange_seq_num;
		private int order_id;	--
		private String member_id; --	
		private int goods_id;	--
		private int exchange_goods_qty;		    // 援먰솚�긽�뭹�닔
		private String exchange_request_date;     // 援먰솚�떊泥��씪
		private String exchagne_reception_date;	// 援먰솚�젒�닔�씪
		private String exchange_cancel_date;      // 援먰솚痍⑥냼�씪
		private String exchange_reason_code;    // 援먰솚肄붾뱶 �떒�닚蹂��떖( 10,11 ), �긽�뭹臾몄젣( 20,21,22,23 ), 諛곗넚臾몄젣( 30 )
		private String exchange_status_code;    // 援먰솚 泥섎━ �긽�깭
		*/
		
		HttpSession session=request.getSession();
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String  member_id=memberVO.getMember_id();				
		String order_id = receiverMap.get("order_id");
				
		String[] goods_id = request.getParameterValues("goods_id");	    	
		String[] exchange_goods_qty = request.getParameterValues("order_goods_qty");
		String exchange_reason_code = receiverMap.get("returnReason");
		String exchange_status_code = receiverMap.get("exchange_status_code");
		String exchange_memo = receiverMap.get("exchange_memo");
		
		if ( exchange_memo == null ) exchange_memo = "";	
		
		ExchangeOrderVO exchangeOrderVO = new ExchangeOrderVO();	
		
		for(int i = 0; i < goods_id.length; i++ )
		{
			exchangeOrderVO.setOrder_id(Integer.parseInt(order_id));
			exchangeOrderVO.setMember_id(member_id);				
			exchangeOrderVO.setGoods_id(Integer.parseInt(goods_id[i]));
			exchangeOrderVO.setExchange_goods_qty(Integer.parseInt(exchange_goods_qty[i]));
			exchangeOrderVO.setExchange_reason_code(exchange_reason_code);
			exchangeOrderVO.setExchange_status_code(exchange_status_code);
			exchangeOrderVO.setExchange_memo(exchange_memo);
			
			myPageService.addExChangeMyOrder(exchangeOrderVO);	
			
			Map<String,String> resultMap = new HashMap<String, String>();							
			resultMap.put("order_id",order_id);
			resultMap.put("goods_id",goods_id[i]);
			resultMap.put("change_goods_qty",exchange_goods_qty[i]);
			
			logger.info("resultMap = "  + resultMap.toString());		
			
			myPageService.updateChangeOrderQty(resultMap); 
		}		
		
		// 二쇰Ц�뀒�씠釉붿뿉 二쇰Ц�긽�깭瑜� 援먰솚/諛섑뭹濡� 蹂�寃� 異붽� by Dean 230630
		Map<String,String> deliveryMap = new HashMap<String,String>();
		deliveryMap.put("order_id",order_id);
		deliveryMap.put("delivery_state",exchange_status_code);
		adminOrderService.modifyDeliveryState(deliveryMap);	
		
		mav.addObject("message", "exchange_req");
		mav.setViewName("redirect:/mypage/listChangeMyOrderStatus.do");
		return mav;
	}		
		
	// 留덉씠�럹�씠吏� > 諛섑뭹 �떊泥� 由ъ뒪�듃 ( 23.07.03 by Dean listChangeMyOrderStatus.jsp�뿉�꽌 �샇異�)	
	@Override 
	@RequestMapping(value="/returnMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView returnMyOrder(@RequestParam("order_id")  String order_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		MemberVO orderer=(MemberVO)session.getAttribute("memberInfo");		
		String  member_id=orderer.getMember_id();
		
		logger.info("memberID:" + member_id);
		logger.info("viewName:" + viewName);
		logger.info("order_id:" + order_id);
		List<OrderVO> myOrderList=myPageService.findMyOrderInfo(order_id);	
		
		mav.addObject("orderer", orderer);
		mav.addObject("message", "return_req");
		mav.addObject("myOrderList",myOrderList);
		return mav;
	}	
		
	// 留덉씠�럹�씠吏� > 諛섑솚 �떊泥�泥섎━ ( 23.06.30 by Dean )
	@Override
	@RequestMapping(value="/addReturnMyOrder.do" ,method={RequestMethod.POST})	
	public ModelAndView addReturnMyOrder(@RequestParam Map<String, String>  receiverMap,
			                       HttpServletRequest request, HttpServletResponse response)  throws Exception	
		{				
			request.setCharacterEncoding("utf-8");	
			
			String viewName=(String)request.getAttribute("viewName");		
			ModelAndView mav = new ModelAndView(viewName);	
					
			HttpSession session=request.getSession();
			memberVO=(MemberVO)session.getAttribute("memberInfo");
			String  member_id=memberVO.getMember_id();				
			String order_id = receiverMap.get("order_id");
								
			String[] goods_id = request.getParameterValues("goods_id");	     	
			String[] return_goods_qty = request.getParameterValues("order_goods_qty");
			String return_reason_code = receiverMap.get("returnReason");
			String return_status_code = receiverMap.get("return_status_code");
			String return_tracking_number = receiverMap.get("return_tracking_number");
			if ( return_tracking_number == null ) return_tracking_number = "";
			
			String return_memo = receiverMap.get("return_memo");			
			if ( return_memo == null ) return_memo = "";				
			
		//	logger.info("receiverMap = "  + receiverMap.toString());			
						
			ReturnOrderVO returnOrderVO = new ReturnOrderVO();	
			
			for(int i = 0; i < goods_id.length; i++ )
			{
				returnOrderVO.setOrder_id(Integer.parseInt(order_id));
				returnOrderVO.setMember_id(member_id);				
				returnOrderVO.setGoods_id(Integer.parseInt(goods_id[i]));
				returnOrderVO.setReturn_goods_qty(Integer.parseInt(return_goods_qty[i]));
				returnOrderVO.setReturn_reason_code(return_reason_code);
				returnOrderVO.setReturn_status_code(return_status_code);
				returnOrderVO.setReturn_tracking_number(return_tracking_number);
				returnOrderVO.setReturn_memo(return_memo);				
				
				myPageService.addReturnMyOrder(returnOrderVO);		
				
				Map<String,String> resultMap = new HashMap<String, String>();							
				resultMap.put("order_id",order_id);
				resultMap.put("goods_id",goods_id[i]);
				resultMap.put("change_goods_qty",return_goods_qty[i]);
				
				logger.info("resultMap = "  + resultMap.toString());		
				
				myPageService.updateChangeOrderQty(resultMap); 
			}		
			
			// 二쇰Ц�뀒�씠釉붿뿉 二쇰Ц�긽�깭瑜� 諛섑뭹�떊泥��쑝濡� 蹂�寃� 異붽� by Dean 230630
			Map<String,String> deliveryMap = new HashMap<String,String>();
			deliveryMap.put("order_id",order_id);
			deliveryMap.put("delivery_state",return_status_code);
			adminOrderService.modifyDeliveryState(deliveryMap);	
			
			mav.addObject("message", "return_req");
			mav.setViewName("redirect:/mypage/listChangeMyOrderStatus.do");
			return mav;
	}	
	
	
}
