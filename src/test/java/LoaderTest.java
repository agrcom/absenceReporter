import gft.hr.LoadInputFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LoaderTest {

    @Test
    public void findProperFormatFile() throws IOException {

        Stream<Path> files = Files.list(Paths.get("./src/test/resources"));
        Path loadedInput = LoadInputFile.findRawDataFile(files);

        Assert.assertNotNull(loadedInput);
    }

    public void inproperFormatFile() {
    }

    public void manyProperFormatFile() {
    }
}
