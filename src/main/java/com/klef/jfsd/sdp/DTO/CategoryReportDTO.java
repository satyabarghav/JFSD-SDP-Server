package com.klef.jfsd.sdp.DTO;

import com.klef.jfsd.sdp.models.AchievementType;

public class CategoryReportDTO {
    private AchievementType category;
    private long value;

    public CategoryReportDTO(AchievementType category, long value) {
        this.category = category;
        this.value = value;
    }

    public AchievementType getCategory() {
        return category;
    }

    public void setCategory(AchievementType category) {
        this.category = category;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
