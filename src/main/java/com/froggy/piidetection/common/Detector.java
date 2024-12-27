package com.froggy.piidetection.common;

import com.froggy.piidetection.common.dto.DetectionDto;
import java.util.List;

public interface Detector <T extends DetectionDto> {

    T detect(String inputText);

    List<String> extractMatchPattern(String inputText);
}
