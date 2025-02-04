package com.froggy.piidetection;

import com.froggy.piidetection.common.DetectorRegistry;
import com.froggy.piidetection.common.dto.DetectionDto;
import froggy.winterframework.beans.factory.annotation.Autowired;
import froggy.winterframework.stereotype.Service;
import java.util.List;

@Service
public class DetectService {

    private DetectorRegistry detectorRegistry;

    @Autowired
    public DetectService(DetectorRegistry detectorRegistry) {
        this.detectorRegistry = detectorRegistry;
    }

    // 개인정보 검출 로직
    public List<DetectionDto> detectPersonalInfo(String inputText) {
        return detectorRegistry.execute(inputText);
    }
}
