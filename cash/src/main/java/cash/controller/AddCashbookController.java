package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.*;
import cash.vo.*;


@WebServlet("/addCashbook")
public class AddCashbookController extends HttpServlet {

	// 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} 
		// 세션값 저장
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		String memberId = loginMember.getMemberId();

		// request 매개값
		String cashbookDate = (String)request.getParameter("cashbookDate");
		System.out.println(cashbookDate + " <-- cashbookDate");

		// 뷰에 값넘기기(request 속성)
		request.setAttribute("cashbookDate", cashbookDate);
		request.setAttribute("memberId", memberId);
		// 나머지 데이터는 입력폼에서 사용자가 입력
		
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);		
	}

	// 입력액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		System.out.println(request.getParameter("memberId") + " <-- memberId");
		System.out.println(request.getParameter("category") + " <-- category");
		System.out.println(request.getParameter("cashbookDate") + " <-- cashbookDate");
		System.out.println(Integer.parseInt(request.getParameter("price")) + " <-- price");
		System.out.println(request.getParameter("memo") + " <-- memo");
		
		Cashbook cashbook = new Cashbook();
		CashbookDao cashbookDao = new CashbookDao();
		
		// 입력한 값들을 Cashbook 객체에 설정
		cashbook.setMemberId(request.getParameter("memberId"));
		cashbook.setCategory(request.getParameter("category"));
		cashbook.setCashbookDate(request.getParameter("cashbookDate"));
		cashbook.setPrice(Integer.parseInt(request.getParameter("price")));
		cashbook.setMemo(request.getParameter("memo"));
		
		int[] rowAndKey = cashbookDao.insertCashbook(cashbook); // 키값 반환
		int cashbookNo = rowAndKey[1];
		// 입력실패시
		if(cashbookNo == 0) {
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath() + "/calendar");
			return;
		}
	
	// 입력성공시 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
	// 해시태그 추출 알고리즘
	// # #구디    #구디 #자바
	HashtagDao hashtagDao = new HashtagDao();
	String memo = cashbook.getMemo();
	String memo2 = memo.replace("#", " #");//#구디 #아카데미 -> " #구디 #아카데미"
    // 해시태그가 여러개이면 반복해서 입력
    for(String ht : memo2.split(" ")) {
	    if(ht.startsWith("#")) {
	       String ht2 = ht.replace("#", "");
	       if(ht.length() > 0) {
	          Hashtag hashtag = new Hashtag();
	          hashtag.setCashbookNo(cashbookNo);
	          hashtag.setWord(ht2);
	          hashtagDao.insertHashtag(hashtag);
	       }
	    }
    }
    // redirect -> 날짜를 누르면 나오는 cashbookList? cashbookOne.
    response.sendRedirect(request.getContextPath() + "/calendar");
	}
	
}
