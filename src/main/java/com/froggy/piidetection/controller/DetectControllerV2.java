package com.froggy.piidetection.controller;

import com.froggy.piidetection.common.dto.DetectionDto;
import com.froggy.piidetection.service.DetectService;
import froggy.winterframework.beans.factory.annotation.Autowired;
import froggy.winterframework.stereotype.Controller;
import froggy.winterframework.web.bind.annotation.HttpMethod;
import froggy.winterframework.web.bind.annotation.RequestMapping;
import froggy.winterframework.web.bind.annotation.RequestParam;
import froggy.winterframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

@Controller
@RequestMapping(urlPattern="v2/detect")
public class DetectControllerV2 {

    private final DetectService detectService;

    @Autowired
    public DetectControllerV2(DetectService detectService) {
        this.detectService = detectService;
    }

    @RequestMapping(httpMethod = HttpMethod.GET)
    @ResponseBody
    public List<DetectionDto> process(@RequestParam("inputText") String inputText)
        throws IOException, ServletException {

        List<DetectionDto> results = new ArrayList<>();
        if (inputText != null && !inputText.isEmpty()) {
            results = detectService.detectPersonalInfo(inputText);
        }

        return results;
    }

}
