package com.froggy.piidetection.service;

import com.froggy.piidetection.common.DetectorManager;
import com.froggy.piidetection.common.dto.DetectionDto;
import froggy.winterframework.beans.factory.annotation.Autowired;
import froggy.winterframework.stereotype.Service;
import java.util.List;

@Service
public class DetectService {

    private DetectorManager detectorManager;

    @Autowired
    public DetectService(DetectorManager detectorManager) {
        this.detectorManager = detectorManager;
    }

    // 개인정보 검출 로직
    public List<DetectionDto> detectPersonalInfo(String inputText) {
        return detectorManager.execute(inputText);
    }
}
