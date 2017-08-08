package gft.hr;

import lombok.extern.java.Log;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Load Raw Data
 */
@Log
public class LoadInputFile {

    private LoadInputFile() {}

    public static Path findRawDataFile(Stream<Path> files) {
         return files
                .filter(path -> path.toString().contains("AbsenceDisplayBlock"))
                .filter(path -> path.toString().endsWith(".csv"))
                 .findFirst().orElse(null);
    }

    public static InputReader loadInputFile(Path dir) {
        try {
            Path path = Paths.get(dir.toString());
            Reader reader = Files.newBufferedReader(
                    path, Charset.forName("UTF-8"));
            return new InputReader(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
