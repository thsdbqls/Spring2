package home;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class MainController  {
	
	private String prefix = "/WEB-INF/views/";
	private String surfix = ".jsp";
	
	
	 // url이 /로 들어올 때 layout.jsp가 실행되도록 하면 된다
	// main 태그영역만 변경되므로 이에 해당 데이터는 Model에 저장해서 전송
	/*@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("contentPage","/WEB-INF/views/home/home.jsp");
		return "/layout/layout";  // WEB-INF/views/layout/layout.jsp로 이동
	}*/
	/*
	@Autowired
	@Qualifier("viewResolver")
	private InternalResourceViewResolver vr;
	 */
	
	@RequestMapping("")
	public String index(Model model, HttpServletRequest request) {
		// 전역 객체(모든 프로그램에 공유해야하는 데이터가 있을 경우 사용)
		request.getServletContext().setAttribute("insa", "Global Hello");
		
		// 같은 프로그램에서의 전역
		request.getSession().setAttribute("insa", "Program Hello");
		
		// 현재 실행하는 프로그램에서만 사용하는 변수(한번만 사용할 수 있다)
		request.setAttribute("insa", "one exec hello");
		//model.addAttribute("contentPage","/WEB-INF/views/home/home.jsp");
		
		//contentPage에 대한 문자열을 조립
		// 방식1)
		
		String folder="home/";
		String page = "home";
				//첫번째 %s는 WEB-INF 두번째 %s는 views
		//String contentPage = String.format("%s%s%s%s", prefix, folder, page, surfix);
		String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
		model.addAttribute("contentPage",contentPage);
		
		
		// 방식2) 여러줄 처리(""" """)
		/*
		String template="""
				/WEB-INF/views/{0}{1}.jsp
				""";
		String contentPage2=template.format(template, folder, page);
		*/
		
		// 방식 3) 여러줄 처리(""" """) 추가하여 key,value 처리 방법
		/*
		String template="""
				/WEB-INF/views/{0}{1}.jsp
				""";
		// key-value map 생성
		Map<String, String> map = new HashMap();
		map.put("folder", "member/");
		map.put("page", "login");
		
		// 치환
		StringSubstitutor sub = new stringSubstitutor(map);
		String contentPage = sub.replace(template);
		model.addAttribute("contentPage", contentPage);
		*/
		return "/layout/layout";
		
	}
	
	@RequestMapping("index2")
	public String index2() {
		return "index2";
	}
}
