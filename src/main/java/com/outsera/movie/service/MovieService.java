package com.outsera.movie.service;

import com.outsera.movie.dto.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    List<Movie> findAllIsWinners(boolean includeLosers);
}