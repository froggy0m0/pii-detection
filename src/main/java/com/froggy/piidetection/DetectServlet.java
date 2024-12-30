package com.froggy.piidetection;

import com.froggy.piidetection.common.DetectorRegistry;
import com.froggy.piidetection.common.dto.DetectionDto;
import java.io.IOException;
import java.util.List;
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

        List<DetectionDto> results = detectPersonalInfo(inputText);

        request.setAttribute("results", results);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detect-result.jsp");
        dispatcher.forward(request, response);
    }

    // 개인정보 검출 로직
    private List<DetectionDto> detectPersonalInfo(String inputText) {
        DetectorRegistry instance = DetectorRegistry.getInstance();

        return instance.execute(inputText);
    }
}