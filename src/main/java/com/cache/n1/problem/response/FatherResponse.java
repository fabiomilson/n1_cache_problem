package com.cache.n1.problem.response;

import java.util.List;

import com.cache.n1.problem.entity.Father;

public record FatherResponse(
    Long id,
    String desc,
    List<Children> childrens
) {

    public record Children(
        Long id,
        String desc,
        Father father
    ) {

    }
}
