package com.monds.date.substitutor.resolver;

import com.monds.date.substitutor.DateResolver;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class WithResolver implements DateResolver {

    @Override
    public LocalDateTime resolve(LocalDateTime ldt, String lookup) {

        switch (lookup) {
            case "MIDNIGHT":
                return ldt.with(LocalTime.MIDNIGHT);
            case "MIN":
                return ldt.with(LocalTime.MIN);
            case "NOON":
                return ldt.with(LocalTime.NOON);
            case "MAX":
                return ldt.with(LocalTime.MAX);
            default:
                return ldt;
        }
    }
}
