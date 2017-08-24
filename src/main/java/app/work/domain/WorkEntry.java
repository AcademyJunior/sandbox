package app.work.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class WorkEntry {

    private long entryId;
    private long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public long getEmployeeId() {
        return employeeId;
    }
}
