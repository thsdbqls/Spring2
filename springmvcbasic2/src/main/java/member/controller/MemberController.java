package member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.User;

@Controller
@RequestMapping("/")
public class MemberController {
	@Autowired
	private member.service.MemberService memberService;
	@RequestMapping(value="member", method=RequestMethod.GET)
	public String member(Model model){

		// 해당 페이지 작성
		model.addAttribute("contentPage","/WEB-INF/views/member/member.jsp");

		return "layout/layout";
	}
	
	@RequestMapping(value="member", method=RequestMethod.POST)
	public String member(User user, Model model) {
		System.out.println(user);
		boolean result = memberService.register(user);
		// result값을 확인하여 성공, 실패 시 알람 및 페이지 이동을 처리를 해야 한다.
		if(result) {
			
		}
		else {
			
		}
		return null;
		
	}
}
