package auth;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AuthController {

	@RequestMapping(value="login", method=RequestMethod.GET)
    public String login(Model model) {
		String contentPage="/WEB-INF/views/login/login.jsp";
		model.addAttribute("contentPage",contentPage);
		return "layout/layout";  // views/layout/layout.jsp를 여는 것이고
	}
    		
    @RequestMapping(value="login", method=RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {

        if ("admin".equals(username) && "1234".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/";
        }

        return "redirect:/login?error=true";
    }
    // http://localhost:8888/logout
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}