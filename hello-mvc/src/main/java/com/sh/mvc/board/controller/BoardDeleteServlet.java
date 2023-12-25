package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
    private BoardService boardService = new BoardService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 사용자 입력값처리
        long id = Long.parseLong(req.getParameter("id"));

        // 업무로직
        int result = boardService.deleteBoard(id);
        System.out.println(result);

        req.getSession().setAttribute("msg", "게시글을 성공적으로 삭제했습니다.");

        // 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/board/boardList");
    }
}