package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.exception.BoardException;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.common.HelloMvcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * xss 공격
 *  - Cross-site-Scripting공격
 *  - 악성스크립트 웹페이지에 삽입해서 클라이언트 개인정보를 탈취하는 공격법
 *  - Script태그로 구성된 내용을 필터링없이 그대로 화면에 출력할때 취약할 수 있다.
 *  - Escape Html처리를 통해서 사용자 입력값이 html요소로 작동하지 못하게 해야 한다.
 */
@WebServlet("/board/boardDetail")
public class BoardDetailServlet extends HttpServlet {

    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. 사용자 입력값 처리
            long id = Long.parseLong(req.getParameter("id"));
            System.out.println(id);

            // 2. 업무로직
            // 조회수 관련처리
            Cookie[] cookies = req.getCookies();
            List<String> boardCookieValues = getBoardCookieValues(cookies);
            boolean hasRead = boardCookieValues.contains(String.valueOf(id)); // valueOf -> long값을 문자열로

            // 조회
            BoardVo board = boardService.findById(id, hasRead);


            // xss공격대비 escapeHtml처리
            String safeHtml = HelloMvcUtils.escapeHtml(board.getContent());

            // 개행문자 (\n) -> <br>
            board.setContent(HelloMvcUtils.convertLineFeedToBr(safeHtml));
            req.setAttribute("board", board);
            System.out.println(board);

            // 응답 쿠키 생성
            if (!hasRead) { // 읽었을때
                boardCookieValues.add(String.valueOf(id)); // 현재 게시글 id를 추가
                String value = String.join("/", boardCookieValues); // [12,34,56] -> "12/34/56"
                Cookie cookie = new Cookie("board", value);
                cookie.setMaxAge(365 * 24 * 60 * 60); // 1년동안 보관, 음수인경우 session종료시 삭제, 0인경우 즉시삭제
                cookie.setPath(req.getContextPath() + "/board/boardDetail"); // 지정한 경로일때만 클라이언트에서 서버로 쿠키전송
                resp.addCookie(cookie);
            }

            // 3. forward
            req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(req, resp);
        } catch (Exception e) {
            // 예외 전환해서 던지기 : 사용자 친화적 메세지, 원인 예외 wrapping
            throw new BoardException("게시글 상세보기 오류", e);
        }

    }

    private List<String> getBoardCookieValues(Cookie[] cookies) {
        List<String> boardCookieValues = new ArrayList<>();
        if (cookies != null) {
            for (Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + " = " + value);
                if ("board".equals(name)) {
                    String[] temp = value.split("/"); // 23/34/56
                    for (String _id : temp) {
                        boardCookieValues.add(_id);
                    }
                }
            }

        }
        return  boardCookieValues;
    }
}