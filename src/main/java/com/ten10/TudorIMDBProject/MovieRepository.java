package com.ten10.TudorIMDBProject;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findByPrimaryTitleIsContaining(String title);
    List<Movie> findByTitleType(String typeOfShow);
}
