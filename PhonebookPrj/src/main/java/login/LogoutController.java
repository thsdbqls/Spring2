package login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@RequestMapping("")
	public String logout(HttpServletRequest request) {
		//request.getSession().invalidate();
		request.getSession().setAttribute("id", null);
		request.getSession().setMaxInactiveInterval(0);
		return "redirect:/login";
	}
}
