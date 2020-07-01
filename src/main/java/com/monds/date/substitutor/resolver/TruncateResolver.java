package com.monds.date.substitutor.resolver;

import com.google.common.base.Splitter;
import com.monds.date.substitutor.DateResolver;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TruncateResolver implements DateResolver {
    @Override
    public LocalDateTime resolve(LocalDateTime ldt, String lookup) {
        if (lookup.indexOf(',') != -1) {
            for (String inToken : Splitter.on(',').trimResults().split(lookup)) {

                if (StringUtils.isNumeric(inToken)) {
                    int truncatedTo = Integer.parseInt(inToken);
                    ldt = ldt.minusMinutes(ldt.getMinute() % truncatedTo);
                } else {
                    lookup = inToken;
                }
            }
        }
        return ldt.truncatedTo(ChronoUnit.valueOf(lookup));
    }
}
