package com.bookshop01.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam ;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.service.GoodsService;
import com.bookshop01.goods.vo.GoodsVO;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {
	@Autowired
	private GoodsService goodsService;

	// 메이페이지 원본
	@RequestMapping(value= "/main/main.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}
	
	// 메이페이지2 
	@RequestMapping(value= "/main/main2.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지3
	// 메이페이지3 : 중형식물	
	@RequestMapping(value= "/main/main3.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main3(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지4

	@RequestMapping(value= "/main/main4.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main4(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지5 
	// 메이페이지5 : 화환	
	@RequestMapping(value= "/main/main5.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main5(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지6 
	@RequestMapping(value= "/main/main6.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main6(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지7 
	@RequestMapping(value= "/main/main7.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main7(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지8 
	@RequestMapping(value= "/main/main8.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main8(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지9 
	@RequestMapping(value= "/main/main9.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main9(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지10 
	@RequestMapping(value= "/main/main10.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main10(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	
	
	// 메이페이지11 
	@RequestMapping(value= "/main/main11.do" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main11(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session;
		ModelAndView mav=new ModelAndView();
		String viewName=(String)request.getAttribute("viewName");
		mav.setViewName(viewName);
		
		session=request.getSession();
		session.setAttribute("side_menu", "user");
		Map<String,List<GoodsVO>> goodsMap=goodsService.listGoods_bysort();
		mav.addObject("goodsMap", goodsMap);
		return mav;
	}	

}