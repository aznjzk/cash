package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;


@WebServlet("/modifyMember")
public class ModifyMemberController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} 
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		String memberPw = request.getParameter("memberPw");
		MemberDao memberDao = new MemberDao();
		int row = memberDao.modifyMember(loginMember.getMemberId(), memberPw);
		if(row == 0) { // 회원수정 실패시
	    	 System.out.println("회원수정 실패");
	         response.sendRedirect(request.getContextPath()+"/modifyMember");
	         return;
	    } else if(row == 1) { // 회원수정 성공시
	    	System.out.println("회원수정 성공");
	    	// 수정성공
	 		response.sendRedirect(request.getContextPath()+"/memberOne");
	        return;
	     }
	}
}
