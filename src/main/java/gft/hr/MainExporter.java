package gft.hr;

import jxl.write.WriteException;
import lombok.extern.java.Log;
import report.AbsencesReport;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Log
public class MainExporter {

    public static void main(String[] args) throws IOException, WriteException {
        File dir = new File("./Absence Report - " + LocalDate.now().toString()+"_"+LocalDateTime.now().getHour()+"_"+LocalDateTime.now().getMinute());
        dir.mkdir();

        log.info("Looking for AbsenceDisplayBlock_DDMMYYYY.csv");
        Stream<Path> files = Files.list(Paths.get("."));


        Path pathToRawFile = LoadInputFile.findRawDataFile(files);
        InputReader loadedInput = LoadInputFile.loadInputFile(pathToRawFile);

        log.info("Loading file"+pathToRawFile.getFileName());

        AbsencesReport absencesReport = new AbsencesReport();
        absencesReport.createAbsenceReportExcel(dir, loadedInput.readCsv(), pathToRawFile.getFileName().toString());
    }


}
