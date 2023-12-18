package com.sh.mvc.member.model.service.controller;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.service.model.entity.Member;
import com.sh.mvc.member.model.service.model.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {

    private MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp")
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 사용자 입력값 가져오기
        HttpSession session = req.getSession(); // 세션생성
        Member loginMember = (Member)session.getAttribute("loginMember");
        String id = loginMember.getId();

        // 기존비밀번호/신규비밀번호 암호화처리
        String oldPassword = HelloMvcUtils.getEncryptedPassword(req.getParameter("oldPassword"), id);
        String newPassword = HelloMvcUtils.getEncryptedPassword(req.getParameter("newPassword"), id);


        // 업무로직 - 기존비밀번호 비교 session의 loginMember객체 이용
        if(oldPassword.equals(loginMember.getPassword())) {
            // 일치하면 신규비밀번호로 업데이트
            loginMember.setPassword(newPassword);
            int result = memberService.updateMemberPassWord(loginMember);
            session.setAttribute("msg", "비밀번호 수정을 축하드립니다💛💛💛");
            // view - redirect
            resp.sendRedirect(req.getContextPath() + "/member/memberDetail");
        }
        else {
            session.setAttribute("msg", "비밀번호가 일치하지 않습니다😓😓😓");
            // view - redirect
            resp.sendRedirect(req.getContextPath() + "/member/updatePassword");
        }

    }
}
