package com.toby959.hello_m1.output;

import java.util.List;

public record ActorFilms(
        String actor,
        List<String> movies
) {
}
