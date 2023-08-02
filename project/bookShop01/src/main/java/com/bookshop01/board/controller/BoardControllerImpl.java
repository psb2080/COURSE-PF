package com.bookshop01.board.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookshop01.board.vo.ReplyVO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.board.service.BoardService;
import com.bookshop01.board.vo.ArticleVO;
import com.bookshop01.board.vo.Page;
import com.bookshop01.board.vo.ImageVO;
import com.bookshop01.member.vo.MemberVO;


@Controller("boardController")
public class BoardControllerImpl  implements BoardController{
	private static final String ARTICLE_IMAGE_REPO = "C:\\shopping\\file_repo2";
	@Autowired
	private BoardService boardService;
	@Autowired
	private ArticleVO articleVO;
	private ReplyVO replyVO;
	
	
	@Override
	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listArticles(@RequestParam("num") int num,
									 @RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType, 
									 @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
									HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");

		HttpSession session = request.getSession();
		session.setAttribute("side_menu", "board_mode");
		/*listArticle에서 board_mode 사용*/
		
		Page page = new Page();
		
		page.setNum(num);
		//page.setCount(boardService.count());  
		page.setCount(boardService.searchCount(searchType, keyword));
		
		// 검색 타입과 검색어
		page.setSearchTypeKeyword(searchType, keyword);

		List<ArticleVO> articlesList = null; 
		articlesList = boardService.listPage(page.getDisplayPost(), page.getPostNum(), searchType, keyword);
		ModelAndView mav = new ModelAndView(viewName);

		mav.addObject("articlesList", articlesList);   
		mav.addObject("page", page); 

		mav.addObject("select", num);
		
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		
		
		//페이징 원본
//		// 개시물 총 개수
//		int count = boardService.count();
//		
//		// 한번에 출력할 게시물 개수
//		int postNum = 10*num;
//		
//		// 하단 페이징 번호( 10 / 총 개시물 개수 ) 의 올림 
//		int pageNum = (int)Math.ceil((double)count/10);
//		
//		// 출력할 게시물
//		int displayPost = postNum - 9;
//		
//		// 한번에 표시할 페이징 번호의 개수
//		int pageNum_cnt = 10;
//		
//		// 표시되는 페이지 번호 중 마지막 번호
//		int endPageNum = (int)(Math.ceil((double)num / (double)pageNum_cnt) * pageNum_cnt);
//		
//		// 표시되는 페이지 번호 중 첫번째 번호
//		int startPageNum = endPageNum - (pageNum_cnt - 1) ;
//		
//		// 마지막 번호 재계산
//		int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));
//		
//		if(endPageNum > endPageNum_tmp) {
//			endPageNum = endPageNum_tmp;
//		}
//		
//		boolean prev = startPageNum == 1 ? false : true;
//		boolean next = endPageNum * pageNum_cnt >= count ? false : true;
//		
//		List<ArticleVO> articlesList = null;
//		articlesList = boardService.listPage(displayPost, postNum);
//		ModelAndView mav = new ModelAndView(viewName);
//		mav.addObject("pageNum", pageNum);
//		mav.addObject("articlesList", articlesList);
//		
//		// 시작 및 끝 번호
//		mav.addObject("startPageNum", startPageNum);
//		mav.addObject("endPageNum", endPageNum);
//
//		// 이전 및 다음 
//		mav.addObject("prev", prev);
//		mav.addObject("next", next);
//		mav.addObject("num", num);
//		
//		// 현재 페이지
//		mav.addObject("select", num);
		
		return mav;
		
	}
	
	//기존 listArticles
//	@Override
//	@RequestMapping(value= "/board/listArticles.do", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = (String)request.getAttribute("viewName");
//
//		List articlesList = boardService.listArticles();
//		ModelAndView mav = new ModelAndView(viewName);
//		mav.addObject("articlesList", articlesList);
//		return mav;
//		
//	}
//	
	
	
	@Override
	@RequestMapping(value="/board/addNewArticle.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, 
	HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
		}
		
		String imageFileName= upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		articleMap.put("parentNO", 0);
		articleMap.put("member_id", member_id);
		articleMap.put("imageFileName", imageFileName);
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int articleNO = boardService.addNewArticle(articleMap);
			if(imageFileName!=null && imageFileName.length()!=0) {
				File srcFile = new 
				File(ARTICLE_IMAGE_REPO+ "\\" + "temp"+ "\\" + imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			}
	
			message = "<script>";
			message += " alert('새 글을 추가함');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do?num=1'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
			srcFile.delete();
			
			message = " <script>";
			message +=" alert('오류가 발생했다. 다시 시도해라');');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/board/articleForm.do?num=1'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	@RequestMapping(value="/board/viewArticle.do" ,method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewArticle(@RequestParam("num") int num,@RequestParam("articleNO") int articleNO,
									@RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType, 
									@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		
		Page page = new Page();
		
		page.setNum(num);
		//page.setCount(boardService.count());  
		page.setCount(boardService.searchCount(searchType, keyword));
		
		page.setSearchTypeKeyword(searchType, keyword);
		
		articleVO=boardService.viewArticle(articleNO);
		List replyVO=boardService.replyList(articleNO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("article", articleVO);
		mav.addObject("page",page);
		mav.addObject("num", num);
		mav.addObject("searchType", searchType);
		mav.addObject("keyword", keyword);
		mav.addObject("reply",replyVO);
		return mav;
	}


  @RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity modArticle(@RequestParam("num") int num, MultipartHttpServletRequest multipartRequest,
    HttpServletResponse response) throws Exception{
    multipartRequest.setCharacterEncoding("utf-8");
	Map<String,Object> articleMap = new HashMap<String, Object>();
	Enumeration enu=multipartRequest.getParameterNames();
	while(enu.hasMoreElements()){
		String name=(String)enu.nextElement();
		String value=multipartRequest.getParameter(name);
		articleMap.put(name,value);
	}

	String imageFileName= upload(multipartRequest);
	articleMap.put("imageFileName", imageFileName);
	articleMap.put("num", num);

	String articleNO=(String)articleMap.get("articleNO");
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    try {
       boardService.modArticle(articleMap);
       if(imageFileName!=null && imageFileName.length()!=0) {
         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
         FileUtils.moveFileToDirectory(srcFile, destDir, true);

         String originalFileName = (String)articleMap.get("originalFileName");
         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
         oldFile.delete();
       }
       message = "<script>";
	   message += " alert('글을 수정했습니다.');";
	   message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?num="+num+"&articleNO="+articleNO+"';";
	   message +=" </script>";
       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
    }catch(Exception e) {
      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
      srcFile.delete();
      message = "<script>";
	  message += " alert('오류가 발생했다. 다시 시도해라');";
	  message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?num=1&articleNO="+articleNO+"';";
	  message +=" </script>";
      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	  e.printStackTrace();
    }
    return resEnt;
  }


  @RequestMapping(value="/board/addReply.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addReply(@RequestParam("articleNO") int articleNO,
								   MultipartHttpServletRequest multipartRequest,
									HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> replyMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			String value=multipartRequest.getParameter(name);
			replyMap.put(name,value);
		}
		String imageFileName= upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		replyMap.put("parentNO", 0);
		replyMap.put("member_id", member_id);
		replyMap.put("articleNO", articleNO);

		System.out.println(replyMap);
		
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardService.addReply(replyMap);
			/*if(imageFileName!=null && imageFileName.length()!=0) {
				File srcFile = new 
				File(ARTICLE_IMAGE_REPO+ "\\" + "temp"+ "\\" + imageFileName);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
				FileUtils.moveFileToDirectory(srcFile, destDir,true);
			}*/
	
			message = "<script>";
			message += " alert('새 답글을 추가함');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticles.do?num=1'; ";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			
			message = " <script>";
			message +=" alert('오류가 발생했다. 다시 시도해라');');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/board/articleForm.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
		
		
	}

  @Override
  @RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity  removeArticle(@RequestParam("articleNO") int articleNO,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
	response.setContentType("text/html; charset=UTF-8");
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	try {
		boardService.removeArticle(articleNO);
		File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
		FileUtils.deleteDirectory(destDir);
		
		message = "<script>";
		message += " alert('글을 삭제했습니다.');";
		message += " location.href='"+request.getContextPath()+"/board/listArticles.do';";
		message += " location.href='"+request.getContextPath()+"/board/listArticles.do?num=1';";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	       
	}catch(Exception e) {
		message = "<script>";
		message += " alert('오류가 발생했다. 다시 시도해라.');";
		message += " location.href='"+request.getContextPath()+"/board/listArticles.do?num=1';";
		message +=" </script>";
	    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    e.printStackTrace();
	}
	return resEnt;
  }



	@RequestMapping(value = "/board/articleForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView articleForm(@RequestParam("num") int num,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("num", num);
		return mav;
	}
	
	@RequestMapping(value = "/board/replyForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	private ModelAndView replyForm(@RequestParam("articleNO") int articleNO,
								   HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");

		ArticleVO article = new ArticleVO();
		article.setArticleNO(articleNO); // int값을 articleVO 객체에 설정
		System.out.println(articleNO);
		ModelAndView mav = new ModelAndView();
		mav.addObject("article", article);
		mav.setViewName(viewName);

		return mav;
	}

	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception{
		String imageFileName= null;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()){
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			imageFileName=mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+"\\" + fileName);
			if(mFile.getSize()!=0){ //File Null Check
				if(!file.exists()){ //寃쎈줈�긽�뿉 �뙆�씪�씠 議댁옱�븯吏� �븡�쓣 寃쎌슦
					file.getParentFile().mkdirs();  //寃쎈줈�뿉 �빐�떦�븯�뒗 �뵒�젆�넗由щ뱾�쓣 �깮�꽦
					mFile.transferTo(new File(ARTICLE_IMAGE_REPO +"\\"+"temp"+ "\\"+imageFileName)); //�엫�떆濡� ���옣�맂 multipartFile�쓣 �떎�젣 �뙆�씪濡� �쟾�넚
				}
			}
			
		}
		return imageFileName;
	}
	
}