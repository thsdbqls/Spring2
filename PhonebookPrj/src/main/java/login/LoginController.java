package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

	// get : form 활성화
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("contentPage", "WEB-INF/views/login/login.jsp");
		return "layout/layout";
	}
	
	// post : form 처리
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String login(String id, String password, HttpServletRequest request) {
		boolean loginState = id.equals("admin") && password.equals("1234") ? true : false;

		System.out.println(id+" "+password+" "+loginState);
        if(loginState){
        request.getSession().setAttribute("id",id);
        } 
		return "layout/layout";
	}
}
