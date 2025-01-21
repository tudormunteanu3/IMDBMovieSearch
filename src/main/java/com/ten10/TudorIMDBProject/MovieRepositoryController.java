package com.ten10.TudorIMDBProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieRepositoryController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TMDBApiService tmdbApiService;

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByPrimaryTitleIsContaining(title);
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/search")
    public String getMovies(
            @RequestParam(value = "primaryTitle") String primaryTitle,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size, Model model) {
        Page<Movie> moviesPage;
        if (primaryTitle != null && !primaryTitle.isEmpty()) {
            moviesPage = movieRepository.findByPrimaryTitleContainingIgnoreCaseOrderByNumVotesDesc(primaryTitle, PageRequest.of(page, size));
        } else {
            moviesPage = movieRepository.findAllByOrderByNumVotesDesc(PageRequest.of(page, size));
        }

        moviesPage.getContent().forEach(movie -> {
            Optional<String> posterUrl = tmdbApiService.getPoster(movie.getPrimaryTitle());
            movie.setPosterUrl(posterUrl.orElse("https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"));
        });

        model.addAttribute("movies", moviesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviesPage.getTotalPages());
        model.addAttribute("primaryTitle", primaryTitle);
        return "searchResults";
    }
}
