package board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.model.Board;
import board.model.BoardForm;
import board.service.BoardService;

@Controller
//@RequestMapping("/board/*")//사용가능
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//페이지요청에 대한 처리
	@RequestMapping(value = "list"
			, method = RequestMethod.GET)
	public String list(@RequestParam(defaultValue = "1") int requestPage
			, Model model) {
		//이전에는 전체 리스트를 화면에 나타냈지만 여기서는 페이지 리스트를 나타낸다.
		model.addAttribute
		("pageList",boardService.getPageList(requestPage));
		System.out.println(boardService.getPageList(requestPage));
		model.addAttribute
		("contentPage","/WEB-INF/views/board/list.jsp");
		return "layout/layout";
	}
	
	//mapping에서 name[배열/식별용], value가 존재(사용은 value/url용)
	/*
	@RequestMapping(value = "list"
			, method = RequestMethod.GET)
	public String list(Model model) {
		//이전에는 전체 리스트를 화면에 나타냈지만 여기서는 페이지 리스트를 나타낸다.
		model.addAttribute("list",boardService.getList());
		model.addAttribute
		("contentPage","/WEB-INF/views/board/list.jsp");
		return "layout/layout";
	}
	*/
	@RequestMapping(value = "write"
			, method = RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute
		("contentPage","/WEB-INF/views/board/write.jsp");
		return "layout/layout";
	}
	
	@RequestMapping(value = "write"
			, method = RequestMethod.POST)
	public String write(BoardForm board,RedirectAttributes ra) {
		System.out.println(board);
		if(boardService.insert(board)) {
			ra.addAttribute("msg","게시글이 등록되었습니다.");
			return "redirect:/";
		}else {
			ra.addAttribute("msg","게시글이 등록이 실패했습니다.");
			return "redirect:/";
		}
		//return null;
	}
	
	@RequestMapping(value = "view"
			, method = RequestMethod.GET)
	public String view(int id, Model model) {
		boardService.addViewCnt(id);//id에 대해 조회수 1증가
		
		model.addAttribute
		("page",boardService.getBoardPage(id));
		model.addAttribute
		("contentPage","/WEB-INF/views/board/view.jsp");
		
		return "layout/layout";
	}
	
	@RequestMapping(value = "update"
			, method = RequestMethod.GET)
	public String update(int id , Model model) {
		model.addAttribute
		("page",boardService.getBoardPage(id));
		model.addAttribute
		("contentPage","/WEB-INF/views/board/update.jsp");
		
		return "layout/layout";
	}
	
	@RequestMapping(value = "update"
			, method = RequestMethod.POST)
	public String update(BoardForm board, RedirectAttributes ra) {
		if(boardService.update(board)) {
			ra.addAttribute("msg","게시글이 수정되었습니다.");
			return "redirect:/board/list";
		}else {
			ra.addAttribute("msg","게시글 수정이 실패했습니다.");
			return "redirect:/board/list";
		}

	}
	@RequestMapping(value = "delete"
			, method = RequestMethod.GET)
	public String delete(int id,RedirectAttributes ra) {
		if(boardService.delete(id)) {
			ra.addAttribute("msg","게시글이 수정되었습니다.");
			return "redirect:/board/list";
		}else {
			ra.addAttribute("msg","게시글 수정이 실패했습니다.");
			return "redirect:/board/list";
		}
	}
	
}