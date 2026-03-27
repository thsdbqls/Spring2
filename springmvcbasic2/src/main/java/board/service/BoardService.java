package board.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.model.Board;
import board.model.BoardForm;
import board.model.BoardPage;
import board.model.BoardTable;
import board.model.PageList;
import board.repository.BoardDAO;
import member.model.User;
import member.repository.UserDAO;

@Service
public class BoardService {

	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	UserDAO userDAO;
	
	public boolean insert(BoardForm board) {
		//form에서 데이터와 테이블에 전달되는 데이터가 다르기 때문에 일치하도록 코드 변환
		BoardTable tboard=new BoardTable();
		BeanUtils.copyProperties(board, tboard);
		User user=userDAO.findByUsername(board.getAuthor());
		tboard.setUserid(user.getId());
		System.out.println(tboard);
		
		return boardDAO.save(tboard)>0 ? true : false;
	}

	public List<Board> getList() {
		return boardDAO.findAll(0, 0);
	}

	public PageList getPageList(int requestPage) {
		PageList pageList=new PageList();
		
		//글의 전체 갯수
		int totalCount=boardDAO.count(); 
		pageList.setTotalCount(totalCount);
		
		//페이지당 리스트갯수
		int pagePerCount=10;
		pageList.setPagePerCount(pagePerCount);
		
		//전체페이지
		int totalPage=0; 
		totalPage=totalCount/pagePerCount; 
		if((totalCount%pagePerCount)!=0) totalPage++;
		pageList.setTotalPage(totalPage);
		
		//요청페이지=현재페이지
		pageList.setCurrentPage(requestPage);
		
		//글의 시작번호, 글의 끝번호(페이지리스트의 입력값으로 처리(dao))
		int startnum=(requestPage-1)*pagePerCount+1; //글의 시작번호 -> 자동계산
		int endnum=requestPage*pagePerCount; //글의 끝번호 ->자동계산
		if(endnum>totalCount) endnum=totalCount;
		List<Board> list=boardDAO.findAll(startnum, endnum);
		pageList.setList(list);
		
		int startPage=1+((requestPage-1)/5*5); //네비게이트 시작번호 ->자동계산
		int endPage=startPage+5-1;//네비게이트 끝번호 ->자동계산
		if(endPage>totalPage) endPage=totalPage;
		pageList.setStartPage(startPage);
		pageList.setEndPage(endPage);
		
		//이전페이지 다음페이지 표시 여부 확인 속성
		boolean isPre=false;
		boolean isNext=false;
		if(endPage<=5){isPre=false;isNext=true;}
		if(endPage>5) {isPre=true;isNext=true;}
		// endPage, totalPage를 이용하여 isNext를 확인
		if(endPage==totalPage) {isPre=true;isNext=false;}
		pageList.setPre(isPre);
		pageList.setNext(isNext);
		
		return pageList;
	}

	//페이지에 표시될 데이터 가져오는 함수
	public BoardPage getBoardPage(int id) {
		BoardTable bt=boardDAO.findById(id);
		String username
		=userDAO.findById(bt.getUserid()).getUsername();
		BoardPage boardpage=new BoardPage();
		BeanUtils.copyProperties(bt,boardpage);
		boardpage.setUsername(username);
		return boardpage;
	}
	
	//조회수 1증가
	public void addViewCnt(int id) {
		boardDAO.viewcntup(id);		
	}

	public boolean update(BoardForm board) {
		BoardTable tboard=new BoardTable();
		BeanUtils.copyProperties(board, tboard);	
		return boardDAO.update(tboard)>0 ? true : false;
	}

	public boolean delete(int id) {
		return boardDAO.delete(id)>0 ? true : false;
	}
}