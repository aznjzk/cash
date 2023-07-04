package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.*;

@WebServlet("/cashbookOne")
public class CashbookOneController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사	
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} 
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		String cashbookDate = (String)request.getParameter("cashbookDate");
		
		// 모델 값 구하기(dao 메서드를 호출)
		List<Cashbook> list = new CashbookDao().selectCashbookListByDate(loginMember.getMemberId(), cashbookDate);
		request.setAttribute("list", list);
		request.setAttribute("cashbookDate", cashbookDate);
		// 이번달 달력에 가계목록의 모델값을 셋팅
		request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}
}