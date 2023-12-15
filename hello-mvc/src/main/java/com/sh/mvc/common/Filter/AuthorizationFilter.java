package com.sh.mvc.common.Filter;

import com.sh.mvc.member.model.service.model.entity.Member;
import com.sh.mvc.member.model.service.model.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 관리자 권한검사 필터
 *  - authorization 권한 : 인증받은 사용자가 이 서비스르 이용할 수 있는지 체크
 *  - 검사할 URL : /admin/*
 *  - 인증확인 및 로그인된 사용자의 권한검사 Role.A인지 체크하기
 */

@WebFilter("/admin/*")
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null || loginMember.getRole() != Role.A) {
            session.setAttribute("msg", "관리자만 접근가능합니다.💚");
            response.sendRedirect(request.getContextPath() + "/");
            return; // redirect/forward 이후 실행코드는 없어야 한다.
        }
        super.doFilter(request, response, chain);
    }
}
