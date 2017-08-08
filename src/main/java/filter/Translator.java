package filter;

import model.ReportModel;

import java.util.List;
import java.util.stream.Collectors;

public class Translator {


    public static List<ReportModel> translateReason(List<ReportModel> rawData) {
        return rawData.stream()
                .map(it -> {
                    it.setReason(it.getReason().replace("__Vacation", "UW"));
                    it.setReason(it.getReason().replace("Blood Donation", "Krwiodastwo"));
                    it.setReason(it.getReason().replace("Childcare (hours)", "Opieka nad dzieckiem art. 188"));
                    it.setReason(it.getReason().replace("Compassionate Leave", "Urlop okolicznościowy"));
                    it.setReason(it.getReason().replace("Garden Leave", "Zwolnienie z obowiązku świadczenia pracy"));
                    it.setReason(it.getReason().replace("Jury Service", "Zwolnienie na czas obowiązkowego stawienia się w urzędach państwowych"));
                    it.setReason(it.getReason().replace("Sick Leave", "Choroba"));
                    it.setReason(it.getReason().replace("ODD (on demand day)", "Dzień na żądanie"));
                    it.setReason(it.getReason().replace("Parental Leave", "Urlop rodzicielski"));
                    it.setReason(it.getReason().replace("Paternity", "Urlop ojcowski"));
                    it.setReason(it.getReason().replace("Study Leave", "Urlop szkoleniowy"));
                    it.setReason(it.getReason().replace("Unpaid Leave", "Urlop bezpłatny"));
                    return it;
                })
                .collect(Collectors.toList());
    }
}