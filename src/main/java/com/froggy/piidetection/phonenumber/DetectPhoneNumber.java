package com.froggy.piidetection.phonenumber;

import static com.froggy.piidetection.common.constants.RegexPatternConsts.PHONE_NUMBER_PATTERN;

import com.froggy.piidetection.phonenumber.dto.DetectionPhoneNumberDto;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetectPhoneNumber {

    public DetectionPhoneNumberDto detect(String inputText) {

        List<String> extractTexts = extractMatchPattern(inputText);
        if (extractTexts.isEmpty()) {
            return DetectionPhoneNumberDto.createEmptyData();
        }

        return new DetectionPhoneNumberDto(extractTexts);
    }

    public List<String> extractMatchPattern(String inputText) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
        Matcher matcher = pattern.matcher(inputText);

        List<String> extractTexts = new ArrayList<>();

        while (matcher.find()) {
            extractTexts.add(matcher.group());
        }

        return extractTexts;
    }
}
