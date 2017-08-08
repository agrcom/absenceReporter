package report;

import branding.GftColour;
import branding.GftFont;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportBase {

    /**
     * Generate Header and set formatting
     *
     * @param excelSheet
     * @return
     */
    protected WritableSheet formatSheet(WritableSheet excelSheet) throws WriteException {
        WritableSheet result = excelSheet;

        WritableCellFormat cFormat = new WritableCellFormat();
        WritableFont font = new WritableFont(GftFont.Calibri, 11, WritableFont.BOLD);
        cFormat.setFont(font);
        cFormat.setBackground(GftColour.GFT_HEADER_BLUE);
        cFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

        Label label = new Label(0, 0, "Nieobecność", cFormat);
        result.addCell(label);
        label = new Label(1, 0, "Imię", cFormat);
        result.addCell(label);
        label = new Label(2, 0, "Nazwisko", cFormat);
        result.addCell(label);
        label = new Label(3, 0, "key", cFormat);
        result.addCell(label);
        label = new Label(4, 0, "Data", cFormat);
        result.addCell(label);

        return result;
    }

    public WritableCellFormat setFormattingForTable() throws WriteException {
        WritableCellFormat cFormat = new WritableCellFormat();
        WritableFont font = new WritableFont(GftFont.Calibri, 11, WritableFont.NO_BOLD);
        cFormat.setFont(font);
        cFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        return cFormat;
    }


    protected String createKey(String employee) {
        String[] array = employee.split(",");
        if (array.length > 2) {
            return array[1].substring(1) + array[2] + " " + array[0];
        }
        return array[1].substring(1) + " " + array[0];
    }

    protected String getSurname(String employee) {
        String[] array = employee.split(",");
        return array[0];
    }

    protected String getName(String employee) {
        String[] array = employee.split(",");
        if (array.length > 2) {
            return array[1].substring(1) + "," + array[2];
        }

        return array[1].substring(1);
    }

    public static LocalDate analyzeFileNameToGetDate(String fileName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        LocalDate reportDate = LocalDate.parse(fileName.subSequence(fileName.length() - 12, fileName.length() - 4).toString(), formatter);
        return reportDate;
    }
}
