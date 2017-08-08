import gft.hr.InputReader;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDataLoader {

    public static InputReader createCsvReader() {
        try {
            Path path = Paths.get("src/test/resources", "TestSample.csv");
            Reader reader = Files.newBufferedReader(
                    path, Charset.forName("UTF-8"));
            return new InputReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static InputReader loadCsvTestFile(String testFileName) {
        try {
            Path path = Paths.get("src/test/resources", testFileName);
            Reader reader = Files.newBufferedReader(
                    path, Charset.forName("UTF-8"));
            return new InputReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
