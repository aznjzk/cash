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
		
		request.setCharacterEncoding("utf-8");
		
		// session 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} 
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		String memberId = loginMember.getMemberId();
		String category = request.getParameter("category");
		String cashbookDate = request.getParameter("cashbookDate");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		// 메모에 해시태그 중복 및 내용없는 해시태그 제거
		String rememo = memo.replace("#", " #"); // #구디 #아카데미 -> " #구디 #아카데미"
		memo = "";
		for(String w : rememo.split(" ")) {
			if(w.startsWith("#")) {
				String word = w.replace("#", "");
				if(word.length() > 0) {
					memo +="#" + word + " ";
				}
			} else {
				memo += w + " ";
			}
		}
		
		System.out.println(memberId + " <-- AddCashbookController memberId");
		System.out.println(category + " <-- AddCashbookController category");
		System.out.println(cashbookDate + " <-- AddCashbookController cashbookDate");
		System.out.println(price + " <-- AddCashbookController price");
		System.out.println(memo + " <-- AddCashbookController memo");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		CashbookDao cashbookDao = new CashbookDao();
		int cashbookNo = cashbookDao.insertCashbook(cashbook);
		
		if(cashbookNo == 0) {
			System.out.println("입력 실패");
			response.sendRedirect(request.getContextPath() + "/addCashbook?cashbookDate="+cashbook.getCashbookDate());
			return;
		}
	
	// 입력성공시 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
	// 해시태그 추출 알고리즘
	// # #구디    #구디 #자바
	HashtagDao hashtagDao = new HashtagDao();
    // 해시태그가 여러개이면 반복해서 입력
	// 입력 성공 시 해시태그 존재 여부 확인
	// 해시태그 있을 경우 추출하여 데이터베이스에 입력
	for(String w : memo.split(" ")) {
		if(w.startsWith("#")) {
			String word = w.replace("#", "");
			if(word.length() > 0) {
				Hashtag hashtag = new Hashtag();
				hashtag.setCashbookNo(cashbookNo);
				hashtag.setWord(word);
				hashtagDao.insertHashtag(hashtag);
			}
		}
	}
	
    // redirect -> 날짜를 누르면 나오는 cashbookList? cashbookOne.
	int targetYear = Integer.parseInt(cashbook.getCashbookDate().substring(0, 4));
	int targetMonth = Integer.parseInt(cashbook.getCashbookDate().substring(5, 7)) - 1;
	int targetDate = Integer.parseInt(cashbook.getCashbookDate().substring(8, 10));
	response.sendRedirect(request.getContextPath()+"/cashbook?targetYear="+targetYear+"&targetMonth="+targetMonth+"&targetDate="+targetDate);
	}
	
}
