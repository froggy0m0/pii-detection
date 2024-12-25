package com.froggy.piidetection.phonenumber.dto;

import java.util.ArrayList;
import java.util.List;

public class DetectionPhoneNumberDto {

    String name;
    int count;
    List<String> detectedItems;

    public DetectionPhoneNumberDto(List<String> detectedItems) {
        this.name = "핸드폰번호";
        this.count = detectedItems.size();
        this.detectedItems = detectedItems;
    }

    public static DetectionPhoneNumberDto emptyData() {
        return new DetectionPhoneNumberDto(new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public List<String> getDetectedItems() {
        return detectedItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("검출 항목 : ").append(this.name).append("\n");
        sb.append("검출 갯수 : ").append(this.count).append("\n");
        sb.append("검출 데이터 :").append("\n");

        for (int i = 0; i < this.detectedItems.size(); i++) {
            sb.append("  -> ").append(this.detectedItems.get(i)).append("\n");
        }

        return sb.toString();
    }
}