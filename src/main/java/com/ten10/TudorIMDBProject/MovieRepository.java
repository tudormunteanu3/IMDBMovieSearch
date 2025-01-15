package com.ten10.TudorIMDBProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, String> {
    List<Movie> findByTitleContains(String title);
    List<Movie> findByType(String typeOfShow);
}
