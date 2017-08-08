import filter.Filters;
import gft.hr.InputReader;
import lombok.val;
import model.AbsenceRawModel;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;

public class FilterTest {

    @Test
//    @DisplayName("Filter collection by approved status, check result")
    public void filterRawCollectionByStatus(){
        InputReader csv = TestDataLoader.createCsvReader();
        List<AbsenceRawModel> readCsv = csv.readCsv();

        val result = Filters.filterByApprovedStatus(readCsv);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.get(0).getStatus().equals("Approved"));
        Assert.assertTrue(result.size()==196);
    }

    @Test
//    @DisplayName("Filter collection to get all statuses apart of Approved - to get separate report")
    public void filterCollectionBYRestOfStatuses(){
        InputReader csv = TestDataLoader.createCsvReader();
        List<AbsenceRawModel> readCsv = csv.readCsv();

        val result = Filters.returnOtherStatuses(readCsv);

        Assert.assertNotNull(result);
        Assert.assertFalse(result.get(0).getStatus().equals("Approved"));
        Assert.assertTrue(result.size()==3);
    }

    @Test
    public void filterByReportDate(){


    }

    @Test
    public void skipAllZeroBooked(){
        InputReader csv = TestDataLoader.loadCsvTestFile("TranslateTest.csv");
        List<AbsenceRawModel> readCsv = csv.readCsv();

        val result = Filters.fiterToSkipZeroBookedDays(readCsv);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.size()==5);
    }
}
