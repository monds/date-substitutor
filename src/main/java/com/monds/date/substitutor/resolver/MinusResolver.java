package com.monds.date.substitutor.resolver;

import com.google.common.base.Splitter;
import com.monds.date.substitutor.DateResolver;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

public class MinusResolver implements DateResolver {
    @Override
    public LocalDateTime resolve(LocalDateTime ldt, String lookup) {
        long amountToMinus = 0;
        Iterator<String> insideItr = Splitter.on(',').trimResults().split(lookup).iterator();
        while (insideItr.hasNext()) {

            String inToken = insideItr.next();
            if (StringUtils.isNumeric(inToken)) {
                amountToMinus = Long.parseLong(inToken);
            } else {
                lookup = inToken;
            }
        }
        return ldt.minus(amountToMinus, ChronoUnit.valueOf(lookup));
    }
}
