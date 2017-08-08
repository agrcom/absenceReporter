package gft.hr;

import model.AbsenceRawModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handle rading and maping csv rav file to list which going to be transformed in future
 * as far it is just a List of string list, in next iteration will be move to List of model objects
 */
public class InputReader {

    private Reader inputCsv;

    public InputReader(Reader inputCsv) {
        this.inputCsv = inputCsv;
    }

    /**
     * Get Csv File, start reading from line 2 (to skip header)
     *
     * @return List of rows, row is represent as List of records
     */
    public List<AbsenceRawModel> readCsv() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader reader = new BufferedReader(inputCsv)) {
            return reader.lines()
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(";")))
                    .map(o -> new AbsenceRawModel(o.get(0),
                            o.get(1),
                            o.get(2),
                            LocalDate.parse(o.get(3), formatter),
                            LocalDate.parse(o.get(4), formatter),
                            Double.parseDouble(o.get(5)),
                            Integer.parseInt(o.get(6)),
                            Integer.parseInt(o.get(7)),
                            o.get(8)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
