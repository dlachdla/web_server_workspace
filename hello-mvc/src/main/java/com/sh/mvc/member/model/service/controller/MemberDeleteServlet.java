package com.sh.mvc.member.model.service.controller;

import com.sh.mvc.member.model.service.model.entity.Member;
import com.sh.mvc.member.model.service.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {

    private MemberService memberService = new MemberService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 로그인 된 아이디 가져 오기
        Member loginMember = (Member) req.getSession().getAttribute("loginMember");
        String id = loginMember.getId();

        // 아이디 조회
        Member member = memberService.findById(id);

        // 업무 로직
        int result = memberService.deleteMember(id);

        // 세션가져오기
        HttpSession session = req.getSession();

        // 세션해제
        session.invalidate();

        // 세션 새로 생성. msg속성 저장
        session = req.getSession();
        session.setAttribute("msg", "성공적으로 탈퇴했습니다. \n 다음에 더좋은 서비스로 만나요❤");

        // 인덱스 페이지 이동
        resp.sendRedirect(req.getContextPath() + "/");
    }
}