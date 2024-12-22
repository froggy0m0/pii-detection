package com.froggy.piidetection;

import com.froggy.piidetection.detect.DetectRRN;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetectServlet", value = "/detect")
public class DetectServlet extends HttpServlet {

    DetectRRN detectRRN = new DetectRRN();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String inputText = request.getParameter("inputText");


        String result = detectPersonalInfo(inputText);

        // 결과를 JSP로 전달
        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detect-result.jsp");
        dispatcher.forward(request, response);
    }

    // 개인정보 검출 로직
    private String detectPersonalInfo(String inputText) {

        boolean isDetectRRN = detectRRN.containsRRN(inputText);

        return
            "검출된 개인정보: \n\n" +
            "주민등록번호 " + isDetectRRN + "\n\n" +
            "핸드폰번호 " + "미구현";
    }


}