package com.froggy.piidetection;

import com.froggy.piidetection.common.DetectorRegistry;
import com.froggy.piidetection.common.dto.DetectionDto;
import froggy.winterframework.stereotype.Controller;
import froggy.winterframework.web.ModelAndView;
import froggy.winterframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(url="/detect")
public class DefaultController {

    @RequestMapping(urlPattern = "")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String inputText = request.getParameter("inputText");
        List<DetectionDto> results = detectPersonalInfo(inputText);

        Map<String, Object> model = new HashMap<>();
        model.put("results", results);

        return new ModelAndView("/detect-result.jsp", model);
    }

    // 개인정보 검출 로직
    private List<DetectionDto> detectPersonalInfo(String inputText) {
        DetectorRegistry instance = DetectorRegistry.getInstance();
        
        return instance.execute(inputText);
    }
}