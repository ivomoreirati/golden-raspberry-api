package com.outsera.movie.repository;

import com.outsera.movie.entity.MovieEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MovieRepository implements PanacheRepository<MovieEntity> {
    public List<MovieEntity> getAll() {
        return listAll();
    }
    public List<MovieEntity> findAllIsWinners(boolean winner) {
        return find("winner", winner).list();
    }
}