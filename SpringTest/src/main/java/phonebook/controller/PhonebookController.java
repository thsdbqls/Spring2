package phonebook.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import phonebook.service.PhonebookService;
import phonebook.vo.PhonebookVO;


@Controller
@RequestMapping("/contencts/")
public class PhonebookController {
	@Autowired
	PhonebookService service;
	/*
	private static final Logger logger 
	= LoggerFactory.getLogger(PhonebookController.class);
	
	@RequestMapping("")
	public String index(Model model) {
		String folder="/";
		String page = "index";
		String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
		model.addAttribute("contentPage", contentPage);
		return "/layout/layout";
	}
	
	// 입력폼 /phonebook/insertform
	@RequestMapping("insertform")
	public String insertform(Model model) {
		
		String folder="phonebook/";
		String page = "insertform";
		String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
		model.addAttribute("contentPage",contentPage);
		
		//return "insertform";
		return "layout/layout";
	}
	
	@RequestMapping("insert")
	public String insert(@ModelAttribute PhonebookVO pb
			,
			,RedirectAttributes ra) {
		
		// 필터가 없으면 한글이 깨진다
		System.out.println(pb);
		System.out.println(file.getOriginalFilename());
		
	
		pb.setPic(file.getOriginalFilename());
		
		if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
               File dest 
                = new File("E:/spring-workspace/phonebookprj/src/main/webapp/fileupload/" + fileName);
                file.transferTo(dest);  // 파일을 지정된 경로에 저장
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		ra.addFlashAttribute("kind","insert");
		if(service.insert(pb)) {
			ra.addFlashAttribute("message","success"); 
		}
		else {
			ra.addFlashAttribute("message","fail"); 
		}

		return "redirect:/phonebook/list";
	}
	
	
	// 전체 출력
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", service.getPhonebooks());

		String[] paths=request.getRequestURI().split("/");
		
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp","phonebook",paths[2]);
		
		mv.addObject("contentPage", contentPage);
		
		System.out.println("list call");
		
		
		mv.setViewName("layout/layout");
		return mv;
	}
	// 선택 출력  /phonebook/view?id=1
	@RequestMapping("view")
	public ModelAndView view(int id, HttpServletRequest request) {
		logger.info("******:"+request.getRequestURI());
		ModelAndView mv=new ModelAndView();
		mv.addObject("pb", service.getPhonebook(id));
		
		String[] paths=request.getRequestURI().split("/");
		
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp",paths[1],paths[2]);
		
		mv.addObject("contentPage", contentPage);
		
		mv.setViewName("layout/layout");
		return mv;
	}
	
	// 수정폼 /phonebook/updateform?id=1 => view함수와 유사하다
	@RequestMapping("updateform")
	public ModelAndView updateform(int id, RedirectAttributes ra, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pb", service.getPhonebook(id));
		
String[] paths=request.getRequestURI().split("/");
		
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp",paths[1],paths[2]);
		
		mv.addObject("contentPage", contentPage);
		
		mv.setViewName("layout/layout");
		return mv;
	}
	
	// 수정 처리
	@RequestMapping("update")
	public String update(PhonebookVO pb, RedirectAttributes ra) {
		System.out.println(pb);
		ra.addFlashAttribute("kind","update");
		if(service.update(pb)) {
			ra.addFlashAttribute("message","success"); 
		}
		else {
			ra.addFlashAttribute("message","fail"); 
		}

		return "redirect:/phonebook/view?id="+pb.getId();
	}
	
	// 삭제
	@RequestMapping("delete")
	public String delete(int id) {
		service.delete(id);
		return "redirect:/phonebook/list";
	}
	*/
	
	// /contencts/
	@RequestMapping("")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", service.getPhonebooks());

		String folder="phonebook/";
		String page = "list";
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp",folder,page);
		
		mv.addObject("contentPage", contentPage);
		
		mv.setViewName("layout/layout");
		return mv;
	}
	
	@RequestMapping("/view")
	public ModelAndView view(int id, Model model) {
		/*ModelAndView mv = new ModelAndView();
		mv.addObject("view", service.getPhonebook(id));
		
		String folder="phonebook/";
		String page = "view";
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp",folder,page);
		
		mv.addObject("contentPage", contentPage);
		
		mv.setViewName("layout/layout");
		return mv;*/
		
		
			//logger.info("******:"+request.getRequestURI());
			ModelAndView mv=new ModelAndView();
			mv.addObject("pb", service.getPhonebook(id));
			
			String folder="phonebook/";
			String page = "view";
			String contentPage
			=String.format("/WEB-INF/views/%s/%s.jsp",folder,page);
			
			mv.addObject("contentPage", contentPage);
			
			mv.setViewName("layout/layout");
			return mv;
	}
	
	@RequestMapping("delete/")
	public String delete(int id) {
		service.delete(id);
		return "redirect:/contencts/";
	}
	
	// 입력 폼
	@RequestMapping("/new")
	public ModelAndView insertform() {
		ModelAndView mv = new ModelAndView();

		String folder="phonebook/";
		String page = "insertform";
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp",folder,page);
		
		mv.addObject("contentPage", contentPage);
		
		mv.setViewName("layout/layout");
		return mv;
	}
	
	// 입력 처리
	@RequestMapping("/insert")
	public String insert(PhonebookVO pb,RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView();
		//mv.addObject("insert", service.insert(pb));
		
		ra.addFlashAttribute("kind","insert");
		if(service.insert(pb)) {
			ra.addFlashAttribute("message","success"); 
		}
		else {
			ra.addFlashAttribute("message","fail"); 
		}

		
		return "redirect:/contencts/";
	}
	
	// 수정폼 /phonebook/updateform?id=1 => view함수와 유사하다
	@RequestMapping("edit/")
	public ModelAndView updateform(int id, RedirectAttributes ra, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pb", service.getPhonebook(id));
		
		String folder="phonebook/";
		String page = "updateform";
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp",folder,page);
		
		mv.addObject("contentPage", contentPage);
		
		mv.setViewName("layout/layout");
		return mv;
	}
	
	// 수정 처리
	@RequestMapping("update")
	public String update(PhonebookVO pb, RedirectAttributes ra) {
		System.out.println(pb);
		ra.addFlashAttribute("kind","update");
		if(service.update(pb)) {
			ra.addFlashAttribute("message","success"); 
		}
		else {
			ra.addFlashAttribute("message","fail"); 
		}

		return "redirect:/contencts/";
	}

}
