package com.froggy.piidetection.controller;

import com.froggy.piidetection.common.dto.DetectionDto;
import com.froggy.piidetection.service.DetectService;
import froggy.winterframework.beans.factory.annotation.Autowired;
import froggy.winterframework.stereotype.Controller;
import froggy.winterframework.web.ModelAndView;
import froggy.winterframework.web.bind.annotation.RequestMapping;
import froggy.winterframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

@Controller
@RequestMapping(urlPattern="/detect")
public class DetectController {

    private DetectService detectService;

    @Autowired
    public DetectController(DetectService detectService) {
        this.detectService = detectService;
    }

    @RequestMapping
    public ModelAndView process(@RequestParam("inputText") String inputText)
        throws IOException, ServletException {

        List<DetectionDto> results = new ArrayList<>();
        if (inputText != null && !inputText.isEmpty()) {
            results = detectService.detectPersonalInfo(inputText);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("results", results);

        return new ModelAndView("/detect-result.jsp", model);
    }

}