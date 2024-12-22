package com.froggy.piidetection.detect;

import static com.froggy.piidetection.detect.constants.RegexPatternConsts.RNN_PATTERN;

import com.froggy.piidetection.detect.constants.GenderCode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetectRRN {

    // 입력된 텍스트에 유효한 주민등록번호(RRN)가 포함되어 있는지 확인
    public boolean containsRRN(String inputText) {

        List<String> extractTexts = extractMatchPattern(inputText);
        if (extractTexts.isEmpty()) {
            return false; // 매칭된 패턴이 없다면 false 반환
        }

        // 추출된 결과 중 날짜, 성별 코드 유효성 체크 
        List<String> filterTexts = filterValidDates(extractTexts);
        if (filterTexts.isEmpty()) {
            return false;
        }

        System.out.println("검출 항목");
        for (int i = 0 ; i < filterTexts.size(); i++) {
            System.out.println((i + 1) + ". " + filterTexts.get(i));
        }

        return true;
    }

    // 정규표현식에 매칭되는 문자열을 추출
    public List<String> extractMatchPattern(String inputText) {
        Pattern pattern = Pattern.compile(RNN_PATTERN);
        Matcher matcher = pattern.matcher(inputText);

        List<String> extractTexts = new ArrayList<>();

        while (matcher.find()) {
            extractTexts.add(matcher.group());
        }

        return extractTexts;
    }

    // 추출된 항목 중 유효한 날짜를 포함한 항목만 필터링
    private List<String> filterValidDates(List<String> extractTexts) {
        Iterator<String> iterator = extractTexts.iterator();

        while(iterator.hasNext()) {
            String dateStr = iterator.next();

            if(!validateRRNDate(dateStr)) {
                iterator.remove(); // 유효하지 않은 항목 제거
            }
        }

        return extractTexts;
    }

    // 주민등록번호의 생년월일과 성별 코드가 유효한지 검증
    private boolean validateRRNDate(String dateStr) {
        String birthDate = dateStr.substring(0, 6); // 생년월일 추출 
        char genderCode = dateStr.charAt(7); // 성별 코드 추출

        /**
         * 성별 코드에 따라 출생 연도 계산
         * 19xx -> 1, 2, 5, 5
         * 20xx -> 3, 4, 7, 8
         */
        int year = Integer.parseInt(birthDate.substring(0, 2));
        if (
            genderCode == GenderCode.MALE_1900_1999 ||
            genderCode == GenderCode.FEMALE_1900_1999 ||
            genderCode == GenderCode.FOREIGN_MALE_1900_1999 ||
            genderCode == GenderCode.FOREIGN_FEMALE_1900_1999
        ) {
            year += 1900;
        } else if (
            genderCode == GenderCode.MALE_2000_2099 ||
            genderCode == GenderCode.FEMALE_2000_2099 ||
            genderCode == GenderCode.FOREIGN_MALE_2000_2099 ||
            genderCode == GenderCode.FOREIGN_FEMALE_2000_2099
        ) {
            year += 2000;
        } else {
            return false; // 잘못된 성별 코드
        }

        String fullDate = year + birthDate.substring(2);
        return isValidDate(fullDate);
    }

    // 주어진 날짜 문자열이 유효한지 확인
    public static boolean isValidDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return true; // 유효한 날짜
        } catch (DateTimeParseException e) {
            return false; // 유효하지 않은 날짜
        }
    }
}