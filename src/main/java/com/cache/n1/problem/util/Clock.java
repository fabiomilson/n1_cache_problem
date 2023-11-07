package com.cache.n1.problem.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Clock {

    private final long initialTime;

    public Clock() {
        this.initialTime = System.currentTimeMillis();
    }

    public void printElapsedTime() {
        final var endTime = System.currentTimeMillis();
        final var elapsedTime = endTime - initialTime;

        var seconds = elapsedTime / 1000;
        var minutes = seconds / 60;
        var hours = minutes / 60;

        seconds %= 60;
        minutes %= 60;

        final var textLog = "Tempo decorrido: %sh%sm%ss";
        log.info(textLog.formatted(hours, minutes, seconds));
    }
}
