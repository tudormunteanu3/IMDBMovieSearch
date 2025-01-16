package com.ten10.TudorIMDBProject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import static com.ten10.TudorIMDBProject.IMDBHeaders.tconst;

@Component
public class ImportData {
    @Autowired
    private MovieRepository movieRepository;

    public void importData(String filePath) throws IOException {
        InputStream fileStream = new FileInputStream(filePath);
        InputStream gzipStream = new GZIPInputStream(fileStream);
        Reader reader = new InputStreamReader(gzipStream);
        BufferedReader bufferedReader = new BufferedReader(reader);


        CSVFormat format = CSVFormat.TDF.builder()
                .setHeader(IMDBHeaders.class)
                .setSkipHeaderRecord(true)
                .setQuote(null)
                .get();

        CSVParser records = format.parse(bufferedReader);

        List<Movie> movies = new ArrayList<>();
        int batchSize = 1000;
        int count = 0;

        for (CSVRecord record : records) {
                int tconst = Integer.parseInt(record.get("tconst").substring(2));
                String titleType = record.get("titleType");
                String primaryTitle = record.get("primaryTitle");

                movies.add(new Movie(tconst, titleType, primaryTitle));
                count++;
            if (count % batchSize == 0){
                movieRepository.saveAll(movies);
                movies.clear();
                System.out.println("Saved " + count + " records so far");
            }
        }

        if (!movies.isEmpty()) {
            movieRepository.saveAll(movies);
            System.out.println("Saved remaining " + movies.size() + " movies.");
        }



    }
}
