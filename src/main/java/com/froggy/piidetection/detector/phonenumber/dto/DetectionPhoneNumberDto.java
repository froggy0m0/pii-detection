package com.froggy.piidetection.detector.phonenumber.dto;

import static com.froggy.piidetection.common.constants.DetectionItemNames.PHONE_NUMBER_NAME;

import com.froggy.piidetection.common.dto.DetectionDto;
import java.util.ArrayList;
import java.util.List;

public class DetectionPhoneNumberDto extends DetectionDto {

    public DetectionPhoneNumberDto(List<String> detectedItems) {
        super(PHONE_NUMBER_NAME, detectedItems);
    }

    public static DetectionPhoneNumberDto createEmptyData() {
        return new DetectionPhoneNumberDto(new ArrayList<>());
    }
}