package com.sh.ajax.json;

import com.google.gson.Gson;
import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.celeb.model.service.CelebService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/json/celeb/findById")
public class JsonCelebFindByIdServlet extends HttpServlet {
    private CelebService celebService = new CelebService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        System.out.println(id);

        // 2. 업무로직
        Celeb celeb = celebService.findById(id);
        System.out.println(celeb);

        // 3. 응답처리 (json)
        resp.setContentType("application/json; charset=utf-8");
        new Gson().toJson(celeb, resp.getWriter());

    }
}