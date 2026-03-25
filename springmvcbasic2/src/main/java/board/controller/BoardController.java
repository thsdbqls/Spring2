package board.controller;

import org.h2.engine.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import board.model.Board;

@Controller
//@RequestMapping("/board/")  // 사용 가능
@RequestMapping("/board")
public class BoardController {

	// mapping에서 name, value가 존재하는데 name은 배열 처리할 때 사용하고 여기서는 value를 사용한다
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("contentPage", "/WEB-INF/views/board/list.jsp");
		return "layout/layout";
	}
	
	@RequestMapping(value="/write", method = RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("contentPage", "/WEB-INF/views/board/write.jsp");
		return "layout/layout";
	}
	@RequestMapping(value="/write", method = RequestMethod.POST)
	public String write(Board board) {
		
		return null;
	}
	@RequestMapping(value="view", method = RequestMethod.GET)
	public String view() {
		
		return null;
	}
	@RequestMapping(value="update", method = RequestMethod.GET)
	public String update() {
		
		return null;
	}
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(Board board) {
		
		return null;
	}
	@RequestMapping(value="delete", method = RequestMethod.GET)
	public String delete() {
		
		return null;
	}

}
