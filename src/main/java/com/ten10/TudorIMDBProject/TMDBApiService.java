package com.ten10.TudorIMDBProject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TMDBApiService {
    private final String API_KEY = "INSERT API KEY HERE";
    private final String BASE_URL = "https://api.themoviedb.org/3/search/movie?api_key=";

    public Optional<String> getPoster(String title) {
        String URL = BASE_URL + API_KEY + "&query=" + title.replace(" ", "+");
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response = restTemplate.getForEntity(URL, Map.class);
        List<?> result = (List<?>) response.getBody().get("results");
        if (result.isEmpty()) {
            return Optional.of("https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg");
        }
        String posterPath = (String) ((Map<?,?>)result.getFirst()).get("poster_path");
        return Optional.of("https://image.tmdb.org/t/p/w500" + posterPath);
    }
}
