package phonebook.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import phonebook.model.InsertPhonebookVO;
import phonebook.model.PhonebookVO;
import phonebook.service.PhonebookService;

@Controller
@RequestMapping("/phonebook/")
public class PhonebookController {
	
	@Autowired
	PhonebookService service;
	
	private static final Logger logger 
	= LoggerFactory.getLogger(PhonebookController.class);
	
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
	/*
	@RequestMapping("insertform")
	public String insertform(Model model) {
		String folder="phonebook/";
		String page="insertform";
		String contentPage
		=String.format("/WEB-INF/views/%s%s.jsp",folder,page);
		model.addAttribute("contentPage", contentPage);
		return "layout/layout"; //무조건으로 layout/layout.jsp
	}
	*/
	// 입력 처리   /phonebook/insert
	// 입력 처리 후 페이지에 전달할 값이 있는지 여부를 확인해야한다
	// (성공 또는 실패 메시지가 있다고 가정하면 ModelAndView를 사용해야한다)
	// (성공 또는 실패 메시지가 없다라고 가정할 경우 String을 사용한다)
	/*
	@RequestMapping("insert")
	public String insert(PhonebookVO pb, RedirectAttributes ra) {
		System.out.println(pb);
		ra.addFlashAttribute("kind","insert");
		if(service.insert(pb)) {
			ra.addFlashAttribute("message","success"); 
		}
		else {
			ra.addFlashAttribute("message","fail"); 
		}
		//ra.addFlashAttribute("message","success");  // 이것은 1번만 전달하는 명령이다.
		return "redirect:/phonebook/list";  // 여기로 이동한다는 뜻
		//return "redirect:/";
	}
	*/
	
	// 파일을 처리하기 위한 입력
	// 객체 변수는 폼의 name과 동일해야 처리가 된다
	/*
	@RequestMapping("insert")
	public String insert(@ModelAttribute InsertPhonebookVO ipb, MultipartFile pic) {
		System.out.println(ipb);
		System.out.println(pic.getOriginalFilename());
		return null;  // 여기로 이동한다는 뜻
	}
	*/
	
	@RequestMapping("insert")
	public String insert(@ModelAttribute InsertPhonebookVO ipb
			,@RequestParam("pic") MultipartFile file
			,RedirectAttributes ra) {
		
		// 필터가 없으면 한글이 깨진다
		System.out.println(ipb);
		System.out.println(file.getOriginalFilename());
		
		PhonebookVO pb = new PhonebookVO();
		BeanUtils.copyProperties(ipb, pb);
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
		logger.info("******:"+request.getRequestURI());
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", service.getPhonebooks());
		
		/*String folder="phonebook/";
		String page = "views";
		String contentPage
		=String.format("/WEB-INF/views/%s%s.jsp",folder,page);
		*/
		//  /phonebook/list
		// 0/1        /2
		String[] paths=request.getRequestURI().split("/");
		
		String contentPage
		=String.format("/WEB-INF/views/%s/%s.jsp",paths[1],paths[2]);
		
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
}
