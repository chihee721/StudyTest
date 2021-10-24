package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet 호출");

		// 0. 인코딩 설정
		// POST 방식으로 넘어온 데이터가 영어/숫자가 아닌 경우 인코딩 처리 필요
		// request 내의 파라미터 값은 기본적으로 ISO-8859-1로 인코딩되었다고 간주되기 때문(default 설정)
		request.setCharacterEncoding("UTF-8");

		// 1. 요청값에 포함된 데이터 추출
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String[] hobbyArr = request.getParameterValues("hobby");
		String introduce = request.getParameter("introduce");

		// 사용자 요청값 추출 결과 확인
		System.out.println("gender: " + gender);
		System.out.println("birth: " + birth);
		System.out.println("introduce: " + introduce);

		// 체크박스 미체크 시 foodArr 배열은 null(NullPointerException 방지를 위해 조건문)
		if (hobbyArr != null) {
			for (int i = 0; i < hobbyArr.length; i++) {
				System.out.println("hobbyArr[" + i + "]: " + hobbyArr[i]);
			}
		}

		// 2. Service - Dao - DB로 비즈니스 로직 수행(현재 프로젝트에서는 생략)

		// 3. 요청과 관련된 비즈니스 로직이 다 처리되면 응답화면 처리

		// 응답 화면에 대한 설정
		// => 응답 화면 문서 형태는 HTML이며 문자셋은 UTF-8임을 설정함
		response.setContentType("text/html; charset=UTF-8");

		// 사용자에게 응답 화면을 출력하기 위한 스트림(클라이언트와의 연결된 길) 생성
		// Response 객체의 문자 기반 출력스트림
		PrintWriter out = response.getWriter();

		// java 코드를 통해 응답할 화면을 작성
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인정보 출력화면</title>");
		out.println("<style>");
		out.println("h2{color:red;}");
		out.println("span.gender{color:orange; font-weight:bold;}");
		out.println("span.birth{color:yellow; font-weight:bold;}");
		out.println("span.hobby{color:green; font-weight:bold;}");
		out.println("span.introduce{color:blue; font-weight:bold;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인정보 결과(POST) 화면</h2>");
		out.printf("<span class='gender'>%s</span>가 성별이며 ", gender);
		out.printf("<span class='birth'>%s</span>가 생일이며 ", birth);
		out.printf("<span class='introduce'>%s</span>가 소개이며 ", introduce);
		out.print("취미는 <span class='hobby'>");

		if (hobbyArr != null) {
			for (int i = 0; i < hobbyArr.length; i++) {
				out.printf("%s ", hobbyArr[i]);
			}
			out.println("</span>입니다.");
		} else {
			out.println("</span>없습니다.");
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
