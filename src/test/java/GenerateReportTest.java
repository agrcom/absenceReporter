import filter.Filters;
import filter.Translator;
import gft.hr.InputReader;
import jxl.write.WriteException;
import lombok.val;
import model.AbsenceRawModel;
import model.ReportModel;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import report.AbsencesReport;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GenerateReportTest {

    @Test @Ignore
    public void firstTry() throws IOException, WriteException {
        InputReader csv = TestDataLoader.createCsvReader();
        List<AbsenceRawModel> readCsv = csv.readCsv();

        AbsencesReport generator = new AbsencesReport();
        generator.createAbsenceReportExcel(new File("."), readCsv, "AbsenceDisplayBlock_07102016.csv");
    }

    @Test
    public void generateAbsenceReportCollection(){
        InputReader csv = TestDataLoader.createCsvReader();
        List<AbsenceRawModel> readCsv = csv.readCsv();

        AbsencesReport absencesReport = new AbsencesReport();
        val result = absencesReport.analyzeEntry(readCsv);

        Assert.assertNotNull(result);

        Assert.assertTrue(result.size()==546);
        Assert.assertTrue(result.get(0).getKey().equals("Ilona Fijalkowska"));
        Assert.assertTrue(result.get(99).getReason().equals("__Vacation"));
        Assert.assertTrue(result.get(136).getSurame().equals("Matusiak"));
    }

    @Test
    public void translatorTest(){
        InputReader csv = TestDataLoader.loadCsvTestFile("TranslateTest.csv");
        List<AbsenceRawModel> readCsv = csv.readCsv();

        AbsencesReport absencesReport = new AbsencesReport();

        List<ReportModel> report = absencesReport.analyzeEntry(Filters.filterByApprovedStatus(readCsv));
        report = Translator.translateReason(report);

        Assert.assertNotNull(report);
        Assert.assertTrue(report.get(0).getReason().equals("UW"));
        Assert.assertTrue(report.get(4).getReason().equals("Choroba"));
        Assert.assertTrue(report.get(12).getReason().equals("Zwolnienie z obowiązku świadczenia pracy"));
    }
}
