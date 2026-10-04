package com.platzi.play.domain.service;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDto create(MovieDto movieDto) {
        return this.movieRepository.create(movieDto);
    }

    @Tool("Get All Movies existing in the platform")
    public List<MovieDto> getAll() {
        return this.movieRepository.getAll();
    }

    public MovieDto getById(long id) {
        return this.movieRepository.getById(id);
    }

    public MovieDto update(long id, UpdateMovieDto movieDto) {
        return this.movieRepository.update(id, movieDto);
    }

    public void delete(long id) {
        this.movieRepository.delete(id);
    }
}
