import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import report.ReportBase;

import java.io.File;
import java.io.IOException;

public class ReportBaseTest extends ReportBase{

    @Ignore @Test
    public void formatSheet() throws WriteException, IOException {
        File file = new File("./test.xls");
        WritableWorkbook testReport = Workbook.createWorkbook(file);
        WritableSheet excelSheet = testReport.createSheet("test", 0);

        WritableSheet sheetResult = formatSheet(excelSheet);
        Assert.assertTrue(sheetResult.getCell(0,0).getContents().equals("Nieobecność"));
        Assert.assertTrue(sheetResult.getCell(1,0).getContents().equals("Imię"));
        Assert.assertTrue(sheetResult.getCell(4,0).getContents().equals("Data"));

//        Assert.assertTrue(file.delete());
    }

    @Test
    public void checkKey(){
        String employee = "Matusiak, Marta";

        String result = createKey(employee);
        Assert.assertEquals("Marta Matusiak", result);
    }

    @Test
    public void checkKeyWithDoubleName(){
        String employee = "Matusiak, Marta, Kasia";

        String result = createKey(employee);
        Assert.assertEquals("Marta Kasia Matusiak", result);
    }

    @Test
    public void checkName(){
        String employee = "Matusiak, Marta";

        String result = getName(employee);
        Assert.assertEquals("Marta", result);
    }

    @Test
    public void checkDoubleName(){
        String employee = "Matusiak, Marta, Kasia";

        String result = getName(employee);
        Assert.assertEquals("Marta, Kasia", result);
    }

    @Test
    public void checkSurname(){
        String employee = "Matusiak, Marta";

        String result = getSurname(employee);
        Assert.assertEquals("Matusiak", result);
    }

    @Test
    public void getDateFromFileName(){
        Assert.assertEquals("2017-09-11", ReportBase.analyzeFileNameToGetDate("AbsenceDisplayBlock_11092017.csv").toString());
    }
}
