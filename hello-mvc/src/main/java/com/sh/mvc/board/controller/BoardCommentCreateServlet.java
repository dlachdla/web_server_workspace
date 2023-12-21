package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.board.model.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/boardCommentCreate")
public class BoardCommentCreateServlet extends HttpServlet {

    private BoardService boardService= new BoardService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        long boarId = Long.parseLong(req.getParameter("boardId"));
        String memberId = req.getParameter("memberId");
        String content = req.getParameter("content");
        int commentLevel = Integer.parseInt(req.getParameter("commentLevel"));
        Long parentCommentId = null;

        try {
            parentCommentId = Long.parseLong(req.getParameter("parentCommentId"));
        } catch (NumberFormatException ignore) {} // 댓글인 경우 무시 대댓글인 경우만 값을 사용

        BoardComment comment = new BoardComment();
        comment.setBoardId(boarId);
        comment.setMemberId(memberId);
        comment.setContent(content);
        comment.setCommentLevel(commentLevel);
        comment.setParentCommentId(parentCommentId);
        System.out.println(comment);

        // 2. 업무로직
        int result = boardService.insertBoardComment(comment);

        req.getSession().setAttribute("msg", "댓글을 정상등록했습니다.😁");

        // 3. redirect
        resp.sendRedirect(req.getContextPath() + "/board/boardDetail?id=" + boarId);
    }
}