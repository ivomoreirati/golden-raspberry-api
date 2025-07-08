package com.outsera.movie.service;

import com.outsera.movie.dto.Movie;
import com.outsera.movie.repository.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MovieServiceImpl implements MovieService {

    @Inject
    MovieRepository repository;

    public List<Movie> getAll() {
        return repository.listAll().stream()
            .map(e -> new Movie(
                e.title,
                e.year,
                e.studios,
                e.producers,
                e.winner
            ))
            .collect(Collectors.toList());
    }

    public List<Movie> findAllIsWinners(boolean includeLosers) {
        return repository.findAllIsWinners(includeLosers).stream().map(e -> new Movie(
                e.title,
                e.year,
                e.studios,
                e.producers,
                e.winner
            ))
            .collect(Collectors.toList());
    }
}