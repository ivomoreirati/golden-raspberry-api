package com.outsera.movie.service;

import com.outsera.movie.dto.AwardResponse;
import com.outsera.movie.dto.Award;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
public class AwardServiceImpl implements AwardService {

    @Inject
    MovieService movieService;

    public AwardResponse calculateAwardIntervals() {
        var intervals = movieService.findAllIsWinners(true).stream()
            .flatMap(movie -> splitProducers(movie.getProducers()).stream()
                .map(producer -> Map.entry(producer, movie.getYear())))
            .collect(Collectors.groupingBy(
                Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toSet())
            ))
            .entrySet().stream()
            .filter(entry -> entry.getValue().size() > 1)
            .flatMap(entry -> buildIntervals(entry.getKey(), entry.getValue()).stream())
            .toList();

        if (intervals.isEmpty()) {
            return new AwardResponse(List.of(), List.of());
        }

        int minInterval = intervals.stream()
            .mapToInt(Award::getInterval)
            .min()
            .orElseThrow();

        int maxInterval = intervals.stream()
            .mapToInt(Award::getInterval)
            .max()
            .orElseThrow();

        var minList = intervals.stream()
            .filter(a -> a.getInterval() == minInterval)
            .toList();

        var maxList = intervals.stream()
            .filter(a -> a.getInterval() == maxInterval)
            .toList();

        return new AwardResponse(minList, maxList);
    }

    private List<Award> buildIntervals(final String producer, final Set<Integer> yearsSet) {
        var years = yearsSet.stream().sorted().toList();
        return IntStream.range(1, years.size())
            .mapToObj(i -> new Award(
                producer,
                years.get(i) - years.get(i - 1),
                years.get(i - 1),
                years.get(i)))
            .collect(Collectors.toList());
    }

    private List<String> splitProducers(final String raw) {
        return Arrays.stream(raw.replaceAll(" and ", ",").split(","))
            .map(String::trim)
            .filter(p -> !p.isEmpty())
            .collect(Collectors.toList());
    }
}