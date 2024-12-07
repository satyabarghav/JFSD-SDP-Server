package com.klef.jfsd.sdp.DTO;

import java.time.LocalDate;

public class DailyEventReportDTO {
    private LocalDate date;
    private long eventCount;

    public DailyEventReportDTO(LocalDate date, long eventCount) {
        this.date = date;
        this.eventCount = eventCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getEventCount() {
        return eventCount;
    }

    public void setEventCount(long eventCount) {
        this.eventCount = eventCount;
    }
}
