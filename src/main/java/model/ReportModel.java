package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReportModel {
    String key; //name surname
    String reason;
    LocalDate date;
    String Name;
    String Surame;
}
