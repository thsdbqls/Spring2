package config.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoggingFilter init 초기화");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("request before: " + request.getRemoteAddr());

        /*
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 두개의 조건 다 맞으면 true 1개라도 아니면 false가 된다.
        boolean loginState = id.equals("admin") && password.equals("1234") ? true : false;

        if(!loginState){
        response.getWriter().print("login fail!");
        return;
        } 
        */
        // 자식                        부모
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        
        // uri가 /login은 Session을 확인하지 않고 통과시켜야 한다
        // 이것을 통과를 안 시킬 경우 모든 url을 처리할 수 없게 된다.)
        //1)URL을 확인해야 한다

        //2)통과할 URL에 대해서 Controller로 이동시켜주는 코드를 만들어 줘야 한다.(doFilter 를 사용해야함)
        /*
        if(req.getRequestURI().equals("/login")) {
        	chain.doFilter(request, response);
        	return;
        }
        // 이 URI에 관해서는 통과 시켜준다는 것이다
        */
        // 만약에 method까지 확인하고 싶은 경우
        if(req.getRequestURI().equals("/login")
        		&& req.getMethod().equals("POST")) {
        	chain.doFilter(request, response);
        	return;
        }
        
        // 회원가입, 회원가입 처리, 로그인, 로그인 처리등은 필터에서 통과시키는 uri이다
        
        // 세션을 확인하여 페이지를 이동하는 역활을 함(세션 값이 없으면 다 로그인 창으로 간다)
        if(req.getSession().getAttribute("id") == null){
        	System.out.println("Session:"+req.getSession().getAttribute("id"));
        	//로그인 폼으로 이동;
        	//response.getWriter().print("login form!");
        	
        	// 아래 코드는 url을 호출하는 코드이므로 계속해서 필터를 실행하게 되는 코드이므로
        	// 리다이렉션 횟수가 너무 많기 때문에 아래 코드는 사용이 불가하다
        	//resp.sendRedirect("/phonebook/insertform");
        	
        	// 위의 url을 대체하기 위한 코드 작성
        	//req.getSession().setAttribute("id", "admin");
        	req.setAttribute("contentPage", "/WEB-INF/views/login/login.jsp");
        	RequestDispatcher dispatcher
        	= req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp");
        	dispatcher.forward(req, resp);
        	return;
        	 }
        // 로그인 성공 시 메인 페이지로 이동
        
        // 다음 필터 또는 서블릿 호출
        chain.doFilter(request, response);

        System.out.println("request after");
    }

    @Override
    public void destroy() {
        System.out.println("LegacyLoggingFilter destory");
    }
}