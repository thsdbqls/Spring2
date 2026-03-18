package home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("")
	public String index(Model model) {
		String folder="/";
		String page = "index";
		String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
		model.addAttribute("contentPage", contentPage);
		return "/layout/layout";
	}
	
	//http://localhost:8888/msg?msg=안녕하세요
	//@GetMapping(value = "/msg", produces = "text/plain; charset=UTF-8")
	//produces는 응답할 메시지의 헤드에 해당 코드를 인코딩하는 것이다
	@RequestMapping(value = "msg",produces = "text/plain; charset=UTF-8")
	@ResponseBody //페이지를 리턴하는 것이 아니라 문자열을 리턴하여 화면에 표시하는 것
	public String koreaChar(String msg/*, HttpServletRequest response*/) {
		System.out.println(msg);
		return msg;
	}

}
