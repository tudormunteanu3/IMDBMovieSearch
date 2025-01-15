package com.ten10.TudorIMDBProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class MovieRepositoryController {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContains(title);
    }

    public List<Movie> searchShowByType(String type) {
        return movieRepository.findByType(type);
    }

    public void importFromDataset(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    @GetMapping("/homepage")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/search")
    public String getMovies (
            @RequestParam (value = "primaryTitle") String primaryTitle, Model model) {
        if (primaryTitle != null && !primaryTitle.isEmpty()){
            model.addAttribute("primaryTitle", searchMoviesByTitle(primaryTitle));
        } else {
            model.addAttribute("titleType", searchMoviesByTitle(""));
        }
        return "searchResults";
    }

}
