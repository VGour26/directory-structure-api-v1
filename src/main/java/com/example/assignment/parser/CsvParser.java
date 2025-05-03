package com.example.assignment.parser;

import com.example.assignment.model.DirectoryStructure;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class CsvParser {

    public List<DirectoryStructure> parseCsv(String fileName) {
        try {
            // Load the CSV file as an InputStream
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            // Define the CSV schema based on the DirectoryStructure class
            CsvSchema schema = CsvSchema.emptySchema().withHeader();

            // Create a CsvMapper instance
            CsvMapper csvMapper = new CsvMapper();

            // Map the CSV rows to DirectoryStructure objects
            MappingIterator<DirectoryStructure> iterator = csvMapper.readerFor(DirectoryStructure.class)
                    .with(schema)
                    .readValues(inputStream);

            // Return the list of DirectoryStructure objects
            return iterator.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse CSV file: " + fileName, e);
        }
    }
}