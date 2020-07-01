package com.monds.date.substitutor.resolver;

import com.monds.date.substitutor.DateResolver;

import java.time.LocalDateTime;

public class WithDayOfMonthResolver implements DateResolver {
    @Override
    public LocalDateTime resolve(LocalDateTime ldt, String lookup) {
        return ldt.withDayOfMonth(Integer.parseInt(lookup));
    }
}
