package com.oze.hospital.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.oze.hospital.entity.Patient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVHelper {
    public static ByteArrayInputStream profilesToCSV(Patient profile) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("S/NO", "NAME", "AGE", "LAST VISITED DATE", "DATE CREATED");
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

                List<String> data = Arrays.asList(
                        String.valueOf(profile.getId()),
                        String.valueOf(profile.getName()),
                        String.valueOf(profile.getAge()),
                        String.valueOf(profile.getLastVisitDate()),
                        String.valueOf(profile.getCreatedAt())
                );

                csvPrinter.printRecord(data);

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
