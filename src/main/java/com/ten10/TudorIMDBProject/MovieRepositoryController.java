package com.ten10.TudorIMDBProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieRepositoryController {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByPrimaryTitleIsContaining(title);
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/search")
    public String getMovies (
            @RequestParam (value = "primaryTitle") String primaryTitle, Model model) {
        List<Movie> movies;
        movies = movieRepository.findByPrimaryTitleIsContaining(primaryTitle);
        model.addAttribute("movies", movies);
        return "searchResults";
    }

}
