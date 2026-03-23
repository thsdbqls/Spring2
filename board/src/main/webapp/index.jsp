<%@page import="board.ListPage"%>
<%@page import="board.Writing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.naming.spi.DirStateFactory.Result"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String driver="org.h2.Driver";
    String url = "jdbc:h2:tcp://localhost/~/test";
    String username = "sa";
    String password = "";
    Class.forName(driver);
    Connection conn
    =DriverManager.getConnection(url, username, password);
    out.print(conn+"<br>");
    
    // 첫번째로 구해햐 하는 것은 전체 글의 갯수
    String sql="";
    int totalCount=0; //글의 전체 갯수 ->자동계산
    sql="select count(*) cnt from board";
    PreparedStatement ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    //if(rs.next()) totalCount = rs.getInt("count(*)");
    if(rs.next()) totalCount = rs.getInt("cnt");
    out.print("전체 글의 갯수 :"+totalCount+"<br>");
    
    // 페이지 당 글의 갯수
    int pagePerCount=10; //1페이지의 글의 갯수 ->수동입력(하드코딩, 직접 입력)
    out.print("페이지 당 글의 갯수 :"+pagePerCount+"<br>");
    
    // 전체 페이지 수
    int totalPage=0;
    totalPage = totalCount/pagePerCount;  //111/10=10 => 우리는 12page가 나와야 한다.
    if((totalPage%pagePerCount)!=0) totalPage+=1;
    out.print("전체 글의 페이지 :"+totalPage+"<br>");
    
    // 요청한 페이지 & 현재 페이지 구하기 
    int requestPage=1;//요청한페이지 or 현재페이지 ->수동입력
    // 전달 받은 requestPage가 있는지 여부를 확인해야 한다
    // (index.jsp or index.jsp?requestPage=2)
    String _requestPage=request.getParameter("requestPage");
    if(_requestPage!=null){
    	requestPage=Integer.parseInt(_requestPage);
    }
    
    out.print("현재페이지 :"+requestPage+"<br>");
    
   
    //요청한 페이지에 대한 시작글번호와 끝글번호가 필요
	//글의 시작번호, 글의 끝번호
	int startnum; //글의 시작번호 -> 자동계산 => 1
	int endnum; //글의 끝번호 ->자동계산 => 12
	startnum = (requestPage-1)*pagePerCount+1;
	endnum = requestPage*pagePerCount;
	out.print("글의 시작 번호 :"+startnum+"<br>");
	out.print("글의 끝 번호 :"+endnum+"<br>");
	// 마지막 페이지를 연산하면 전체 글의 번호가 111이라고 가정할 경우 최대 111이 된다
	if(endnum>totalCount) endnum=totalCount;
	out.print("최종 글의 끝 번호 :"+endnum+"<br>");
	
	// 글 시작 번호와 글 끝 번호를 알면 무엇을 알 수 있을까? 글의 페이지 목록 List<Board>
	List<Writing> list = new ArrayList<Writing>();
	//sql="select * from board where id >= ? and id <= ?";
	
	sql="select * from (select rownum as rn,* from board ) where rn>=? and rn<=?";
    ps=conn.prepareStatement(sql);
    ps.setInt(1, startnum);
    ps.setInt(2, endnum);
    rs=ps.executeQuery();
    
    while(rs.next()){
    	list.add(new Writing(
    			rs.getInt("id"),
    			rs.getString("title"),
    			rs.getString("author"),
    			rs.getDate("createdate"),
    			rs.getString("content"),
    			rs.getString("attachment"),
    			rs.getInt("viewcnt"),
    			rs.getString("type")
    			));
    	out.print("글 목록:"+list+"<br>");
    }
 
    // 네비게이트 처리
    //요청한페이지의 네비게이트 시작페이지 번호, 끝페이지 번호
    //requestPage=10;  // 요청한 페이지
    //int startPage=requestPage;
    //int endPage=(requestPage)+5;
	int startPage=1+((requestPage-1)/5*5); //네비게이트 시작번호 ->자동계산
	int endPage=startPage+5-1;//네비게이트 끝번호 ->자동계산
	out.print("요청한 페이지 : "+requestPage+"<br>");
	out.print("네비게이트 시작 번호 : "+startPage+"<br>");
	// 전체 페이지가 끝 페이지를 넘어가서는 안된다(조건)
	// 끝 페이지가 전체 페이지보다 큰 경우 끝 페이지로 처리하라
	if(endPage>totalPage) endPage=totalPage;
	out.print("최종 네비게이트 끝 번호 : "+endPage+"<br>");
	
	//이전페이지 다음페이지 표시 여부 확인 속성
	boolean Pre=false;
	boolean Next=false;
	// 시나리오 : 요청한 페이지가 1~5 페이지일 경우 이전 페이지 표시 비활성화
	// 시나리오 : 요청한 페이지가 6~10 페이지일 경우 이전 페이지 표시 활성화, 다음 페이지 표시 활성화
	// 시나리오 : 요청한 페이지가 11 페이지일 경우 이전 페이지 표시 활성화, 다음 페이지 표시 비활성화
	
	if(endPage<=5) Pre=false; Next=true;
	if(endPage>5) Pre=true; Next=true;
	// endPage와 totalPage를 이용하여 isNext를 확인
	if(endPage==totalPage) Next=false;
	out.print("요청한 페이지 : "+requestPage+"<br>");
	out.print("이전 페이지 네비게이트 표시 여부 : "+Pre+"<br>");
	out.print("다음 페이지 네비게이트 표시 여부 : "+Next+"<br>");
    
	//전체 글 갯수 -> 페이지 당 글의 갯수 -> 전체 페이지 수
	//-> 요청한 페이지 -> 글의 시작 번호/글의 끝 번호 -> 글의 목록
	//-> 요청한 페이지 -> 네비게이트 시작번호/끝 번호, 이전/다음 표시 여부
	
	ListPage listpage = new ListPage();
	listpage.setPagePerCount(pagePerCount);
	listpage.setTotalCount(totalCount);
	listpage.setTotalPage(totalPage);
	listpage.setRequestPage(requestPage);
	listpage.setStartPage(startPage);
	listpage.setEndPage(endPage);
	listpage.setPre(Pre);
	listpage.setNext(Next);
	listpage.setList(list);
	// 위의 정보를 request.setAttribute에 저장하고 list.jsp파일에 넘기기
	request.setAttribute("page", listpage);
	request.getRequestDispatcher("list.jsp").forward(request, response);
    %>
        <%-- <jsp:forward page="list.jsp"/> --%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>전체 글 보기</h1>
</body>
</html>