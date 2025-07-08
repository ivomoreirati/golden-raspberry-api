package com.outsera.movie.dto;

import java.util.List;

public class AwardResponse {
    private List<Award> min;
    private List<Award> max;

    public AwardResponse(List<Award> min, List<Award> max) {
        this.min = min;
        this.max = max;
    }

    public List<Award> getMin() {
        return min;
    }

    public List<Award> getMax() {
        return max;
    }
}