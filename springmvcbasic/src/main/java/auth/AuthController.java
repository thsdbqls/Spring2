package auth;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AuthController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(Model model) {
		String folder="login/";
		String page = "login";
		String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
		model.addAttribute("contentPage", contentPage);
		return "/layout/layout";
	}
    		
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(String id, String password, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {

        if ("admin".equals(id) && "1234".equals(password)) {
            session.setAttribute("loginUser", id);
            //return "redirect:/";
            /*
            String folder="";
    		String page = "index";
    		String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
    		model.addAttribute("contentPage", contentPage);
            return "/layout/layout";*/
            //response.seta
            //request.setAttribute("contentPage", "index");
            
            //return "/layout/layout";
            return "redirect:/";
        }

        //String folder="login/";
		//String page = "login";
		//String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
		//model.addAttribute("contentPage", contentPage);
        return "redirect:/login?error=true";
        //return "/layout/layout";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        String folder="login/";
		String page = "login";
		String contentPage = String.format("/WEB-INF/views/%s%s.jsp", folder, page);
		model.addAttribute("contentPage", contentPage);
		return "/layout/layout";
    }
}