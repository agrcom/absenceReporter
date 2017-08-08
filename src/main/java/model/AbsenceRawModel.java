package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AbsenceRawModel {
    String employee;
    String legalEntity;
    String reason;
    LocalDate startDate;
    LocalDate endDate;
    Double dailyHours;
    Integer requestedDays;
    Integer bookedDays;
    String status;
}
