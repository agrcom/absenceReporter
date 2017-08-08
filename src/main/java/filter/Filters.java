package filter;

import model.AbsenceRawModel;
import model.ReportModel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Filters {

    private Filters() {    }

    /**
     * Filter Raw absence collection by Approved status
     *
     * @return collection of filtered absences
     */
    public static List<AbsenceRawModel> filterByApprovedStatus(List<AbsenceRawModel> rawData){
        return rawData.stream()
                .filter(it -> it.getStatus().equals("Approved") )
                .collect(Collectors.toList());
    }

    /**
     * Filter Raw absence collection to return all not Approved statuses
     *
     * @return every statuses apart of Approved status
     */
    public static List<AbsenceRawModel> returnOtherStatuses(List<AbsenceRawModel> rawData){
        return rawData.stream()
                .filter(it -> it.getStatus().matches("^(?!Approved).*$") )
                .collect(Collectors.toList());
    }

    /**
     * Filter output data by report date
     *
     * @param reportModel analyzed and covered to output model data
     * @param localDateOfInputFile date extracted from file name
     * @return
     */
    public static List<ReportModel> filterByReportDate(List<ReportModel> reportModel, LocalDate localDateOfInputFile){

        return reportModel.stream()
                .filter(it -> it.getDate().getYear() == (localDateOfInputFile.getYear()))
                .filter(it -> it.getDate().getMonth() == (localDateOfInputFile.getMonth()))
                .collect(Collectors.toList());

    }

    /**
     * Filter to eliminate in early stage on raw input model booked days equal to 0
     *
     * @param rawData input raw data model
     * @return  skipped rows with 0 booked days
     */
    public static List<AbsenceRawModel> fiterToSkipZeroBookedDays(List<AbsenceRawModel> rawData){
        return rawData.stream()
                .filter(it -> it.getBookedDays() > 0)
                .collect(Collectors.toList());
    }

}
