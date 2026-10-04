package com.platzi.play.persistence.mapper;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface MovieMapper {

    @Mapping(target = "title", source = "title")
    @Mapping(target = "duration", source = "duration")
    @Mapping(target = "genre", source = "genre", qualifiedByName = "stringToGenre")
    @Mapping(target = "releaseDate", source = "releaseDate")
    @Mapping(target = "rating", source = "classification")
    MovieDto toDto(MovieEntity entity);

    List<MovieDto> toDtoList(Iterable<MovieEntity> entities);

    @InheritInverseConfiguration
    @Mapping(target = "genre", source = "genre", qualifiedByName = "genreToString")
    MovieEntity toEntity(MovieDto dto);

    @Mapping(target = "title", source = "title")
    @Mapping(target = "releaseDate", source = "releaseDate")
    @Mapping(target = "classification", source = "rating")
    void updateEntityFromDto(UpdateMovieDto dto, @MappingTarget MovieEntity entity);

}
