package com.ten10.TudorIMDBProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TudorImdbProjectApplication implements CommandLineRunner {

	@Autowired
	private ImportData dataImporter;

	public static void main(String[] args) {
		SpringApplication.run(TudorImdbProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String imdbDatasetPath = "C:\\Users\\tudor.munteanu\\Desktop\\Dev Academy\\Spring\\TudorIMDBProject\\title.basics.tsv.gz";
		dataImporter.importData(imdbDatasetPath);
	}
}