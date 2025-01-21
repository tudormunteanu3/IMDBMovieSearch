package com.ten10.TudorIMDBProject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findByPrimaryTitleIsContaining(String title);

    Page<Movie> findByPrimaryTitleContainingIgnoreCaseOrderByNumVotesDesc(String primaryTitle, Pageable pageable);

    Page<Movie> findAllByOrderByNumVotesDesc(Pageable pageable);
}
