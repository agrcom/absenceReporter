import gft.hr.InputReader;
import model.AbsenceRawModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ReaderTest {

    @Test
//    @DisplayName("Load Raw file and check if it contains proper amount of lines")
    public void countLinesFromInputReader(){
        InputReader csv = TestDataLoader.createCsvReader();
        List<AbsenceRawModel> readCsv = csv.readCsv();
        Assert.assertTrue(readCsv.size()==199);
    }

    @Test
//    @DisplayName("getFirst record and check if Data are correct")
    public  void readRecordsFromInputReader(){
        InputReader csv = TestDataLoader.createCsvReader();
        List<AbsenceRawModel> readCsv = csv.readCsv();
        AbsenceRawModel record = readCsv.get(0);
        Assert.assertTrue(record.getEmployee().equals("Fijalkowska, Ilona"));
        Assert.assertTrue(record.getLegalEntity().equals("GFT Poland Sp.  z o.o."));
        Assert.assertTrue(record.getReason().equals("__Vacation"));
        Assert.assertTrue(record.getStartDate().toString().equals("2017-06-26"));
        Assert.assertTrue(record.getEndDate().toString().equals("2017-06-26"));
        Assert.assertTrue(record.getDailyHours().toString().equals("8.0"));
        Assert.assertTrue(record.getRequestedDays().toString().equals("1"));
        Assert.assertTrue(record.getBookedDays().toString().equals("1"));
        Assert.assertTrue(record.getStatus().toString().equals("Approved"));
    }



}
