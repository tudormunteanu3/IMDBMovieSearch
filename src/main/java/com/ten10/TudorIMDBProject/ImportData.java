package com.ten10.TudorIMDBProject;

import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static java.nio.file.Files.newInputStream;

@Component
public class ImportData {
    @Autowired
    private MovieRepository movieRepository;

    @PostConstruct
    public void initializeData() throws IOException {
        String basicPath = "title.basics.tsv.gz";
        String ratingPath = "title.ratings.tsv.gz";
        importData(ratingPath, basicPath);
    }

    private static class RatingInfo {
        private final double averageRating;
        private final int numVotes;

        public RatingInfo(double averageRating, int numVotes) {
            this.averageRating = averageRating;
            this.numVotes = numVotes;
        }

        public double getAverageRating() {
            return averageRating;
        }

        public int getNumVotes() {
            return numVotes;
        }
    }

    public void importData(String ratingPath, String basicPath) throws IOException {
        Map<String, RatingInfo> ratingMap = new HashMap<>();
        int batchSize = 500;
        List<Movie> movieBatch = new ArrayList<>();
        int count = 0;


        System.out.println("Importing ratings...");
        var ratingInputStream = newInputStream(Paths.get(ratingPath));
        GZIPInputStream ratingGzipInputStream = new GZIPInputStream(ratingInputStream);
        Reader ratingReader = new InputStreamReader(ratingGzipInputStream, StandardCharsets.UTF_8);

        CSVParser csvParserRatings = CSVFormat.TDF.builder()
                .setDelimiter('\t')
                .setHeader("tconst", "averageRating", "numVotes")
                .setSkipHeaderRecord(true)
                .setQuote(null)
                .build()
                .parse(ratingReader);

        for (CSVRecord record : csvParserRatings) {

            String tconst = record.get(IMDBHeaders.tconst).substring(2);
            double averageRating = Double.parseDouble(record.get("averageRating"));
            int numVotes = Integer.parseInt(record.get("numVotes"));
            ratingMap.put(tconst, new RatingInfo(averageRating, numVotes));
        }
        System.out.println(ratingMap.size() + " ratings imported.");


        System.out.println("Importing movie data...");
        var basicInputStream = newInputStream(Paths.get(basicPath));
        GZIPInputStream basicGzipInputStream = new GZIPInputStream(basicInputStream);
        InputStreamReader basicReader = new InputStreamReader(basicGzipInputStream, StandardCharsets.UTF_8);
        CSVParser csvParserBasic = CSVFormat.TDF.builder()
                .setHeader(IMDBHeaders.class)
                .setQuote(null)
                .setIgnoreSurroundingSpaces(true)
                .setSkipHeaderRecord(true)
                .build()
                .parse(basicReader);
        for (CSVRecord record : csvParserBasic) {
            String tconst = record.get(IMDBHeaders.tconst).substring(2);
            if (ratingMap.containsKey(tconst)) {
                RatingInfo ratingInfo = ratingMap.get(tconst);
                String primaryTitle = record.get(IMDBHeaders.primaryTitle);
                String titleType = record.get(IMDBHeaders.titleType);

                Movie movie = new Movie(tconst, titleType, primaryTitle, ratingInfo.averageRating, ratingInfo.numVotes);
                movieBatch.add(movie);
                if (movieBatch.size() == batchSize) {
                    movieRepository.saveAll(movieBatch);
                    count += movieBatch.size();
                    System.out.println("Saved " + count + " movies.");
                    movieBatch.clear();
                }
            }
        }
        if (!movieBatch.isEmpty()) {
            movieRepository.saveAll(movieBatch);
            count += movieBatch.size();
            System.out.println("Saved " + count + " movies.");
        }
        System.out.println("Importing finished.");

    }
}