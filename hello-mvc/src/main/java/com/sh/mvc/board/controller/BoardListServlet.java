package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.common.HelloMvcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {

    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 가져오기
        int page = 1; // 기본값
        int limit = 10;
        try{

            page = Integer.parseInt((req.getParameter("page")));

        }catch (NumberFormatException ignore){}
        Map<String, Object> param = Map.of("page", page, "limit", limit);
        System.out.println(param);

        // 2. 업무 로직
        // a. content영역 : 전체조회 쿼리
        List<BoardVo> boards = boardService.findAll(param);
        req.setAttribute("boards", boards);
        System.out.println(boards);

        // 페이지바 작업
        // b. pagebar 영역
        int totalCount = boardService.totalCount();
        String url = req.getRequestURI();

        String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        // 3. view단처리
        req.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(req, resp);
    }

}