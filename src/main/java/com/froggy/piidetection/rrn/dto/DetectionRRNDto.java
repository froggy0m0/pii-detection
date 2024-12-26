package com.froggy.piidetection.rrn.dto;

import static com.froggy.piidetection.common.constants.DetectionItemNames.RRN_NAME;

import com.froggy.piidetection.common.dto.DetectionDto;
import java.util.ArrayList;
import java.util.List;

public class DetectionRRNDto extends DetectionDto {

    public DetectionRRNDto(List<String> detectedItems) {
        super(RRN_NAME, detectedItems);
    }

    public static DetectionRRNDto createEmptyData() {
        return new DetectionRRNDto(new ArrayList<>());
    }
}
