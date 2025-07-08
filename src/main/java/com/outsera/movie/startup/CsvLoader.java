package com.outsera.movie.startup;

import com.outsera.movie.entity.MovieEntity;
import com.outsera.movie.repository.MovieRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@ApplicationScoped
public class CsvLoader {

    @Inject
    MovieRepository movieRepository;

    @ConfigProperty(name = "csv.load", defaultValue = "false")
    boolean shouldLoadCsv;

    @Transactional
    public void resetAndLoad() {
        movieRepository.deleteAll();
        load();
    }

    public void onStart(@Observes StartupEvent ev) {
        if (shouldLoadCsv && movieRepository.count() == 0) {
            load();
        }
    }

    @Transactional
    public void load() {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("Movielist.csv")) {
            if (is == null) {
                System.err.println("CSV file not found.");
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reader.lines().skip(1).forEach(line -> {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    MovieEntity movie = new MovieEntity();
                    movie.year = Integer.parseInt(parts[0]);
                    movie.title = parts[1];
                    movie.studios = parts[2];
                    movie.producers = parts[3];
                    movie.winner = (parts.length == 5) && parts[4].equalsIgnoreCase("yes");
                    movieRepository.persist(movie);
                }
            });
        } catch (Exception ignored) {
        }
    }
}