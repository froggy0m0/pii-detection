package com.froggy.piidetection;

import com.froggy.piidetection.common.DetectorRegistry;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetectServlet", value = "/detect")
public class DetectServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String inputText = request.getParameter("inputText");

        String result = detectPersonalInfo(inputText);

        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detect-result.jsp");
        dispatcher.forward(request, response);
    }

    // 개인정보 검출 로직
    private String detectPersonalInfo(String inputText) {
        DetectorRegistry instance = DetectorRegistry.getInstance();

        return instance.execute(inputText);
    }




}