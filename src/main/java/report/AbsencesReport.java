package report;

import filter.Filters;
import filter.Translator;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;
import model.AbsenceRawModel;
import model.ReportModel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbsencesReport extends ReportBase {

    public void createAbsenceReportExcel(File dirPath, List<AbsenceRawModel> inputData, String inputFileName) throws IOException, WriteException {
        WorkbookSettings workbookSettings = new WorkbookSettings();
        workbookSettings.setEncoding("UTF-8");

        WritableWorkbook absencesReport = Workbook.createWorkbook(new File(dirPath + "/"+ LocalDate.now()+"_nieobecnosci-calosc.xls"), workbookSettings);
        WritableSheet excelSheet = absencesReport.createSheet("Deloitte", 0);
        excelSheet = formatSheet(excelSheet);

        WritableCellFormat cellFormat = setFormattingForTable();

        List<ReportModel> report = analyzeEntry(Filters.filterByApprovedStatus(Filters.fiterToSkipZeroBookedDays(inputData)));
        report = Filters.filterByReportDate(report, analyzeFileNameToGetDate(inputFileName));

        report = Translator.translateReason(report);

        int i = 1;
        for (ReportModel model : report) {
            Label label = new Label(0, i, model.getReason(), cellFormat);
            excelSheet.addCell(label);
            label = new Label(1, i, model.getName(), cellFormat);
            excelSheet.addCell(label);
            label = new Label(2, i, model.getSurame(), cellFormat);
            excelSheet.addCell(label);
            label = new Label(3, i, model.getKey(), cellFormat);
            excelSheet.addCell(label);
            label = new Label(4, i, model.getDate().toString(), cellFormat);
            excelSheet.addCell(label);
            i++;
        }

        absencesReport.write();
        absencesReport.close();
    }

    /**
     * Map entry to output model, split booked days into separate entries
     *
     * @param inputData pre filtered collection, if we would like to generate Absence report we have to put here
     *                  data filtered by Approved status
     * @return list of entries which should appear in final report
     */
    public List<ReportModel> analyzeEntry(List<AbsenceRawModel> inputData) {

        return inputData.stream()
                .map(it -> Stream.iterate(
                        new Tuple2<>(it, it.getStartDate()),
                        o -> new Tuple2<>(
                                o.getItem1(),
                                o.getItem2().plusDays(1)))
                        .limit(ChronoUnit.DAYS.between(it.getStartDate(), it.getEndDate().plusDays(1)))) // I've set plus one day, because this return day before end of absence
                .flatMap(it -> it)
                .map(it -> new ReportModel(
                        createKey(it.getItem1().getEmployee()),
                        it.getItem1().getReason(),
                        it.getItem2(),
                        getName(it.getItem1().getEmployee()),
                        getSurname(it.getItem1().getEmployee())
                ))
                .collect(Collectors.toList());
    }
}
