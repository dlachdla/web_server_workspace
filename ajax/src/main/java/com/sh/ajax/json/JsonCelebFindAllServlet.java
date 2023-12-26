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
import java.util.List;

@WebServlet("/json/celeb/findAll")
public class JsonCelebFindAllServlet extends HttpServlet {
    private CelebService celebService = new CelebService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값처리

        // 2. 업무로직
        List<Celeb> celebs = celebService.findAll();

        // 3. 응답처리 : 자바컬렉션데이터를 json으로 변환후 출력 by gson
        resp.setContentType("application/json; charset=utf-8");
//        String jsonData = new Gson().toJson(celebs);
//        System.out.println(jsonData);
//        resp.getWriter().print(jsonData);
        new Gson().toJson(celebs, resp.getWriter());
    }
}