package com.platzi.play.domain.repository;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;

import java.util.List;

public interface MovieRepository {
    MovieDto create(MovieDto movieDto);
    List<MovieDto> getAll();
    MovieDto getById(long id);
    MovieDto update(long id, UpdateMovieDto updateMovieDto);
    void delete(long id);
}
