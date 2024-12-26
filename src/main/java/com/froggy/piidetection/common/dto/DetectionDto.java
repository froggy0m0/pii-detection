package com.froggy.piidetection.common.dto;

import java.util.List;

public abstract class DetectionDto {

    protected String name;
    protected int count;
    protected List<String> detectedItems;

    public DetectionDto(String name, List<String> detectedItems) {
        this.name = name;
        this.count = detectedItems.size();
        this.detectedItems = detectedItems;
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
        sb.append("검출 데이터 :\n");

        for (String item : this.detectedItems) {
            sb.append("  -> ").append(item).append("\n");
        }

        return sb.toString();
    }
}
