package com.monds.date.substitutor;

import com.google.common.base.Splitter;
import com.monds.date.substitutor.resolver.*;
import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.lookup.StringLookup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateLookup implements StringLookup {

    public static final String DATE = "date";

    public static final String TRUNCATED_TO = "truncatedTo";

    public static final String MINUS = "minus";

    public static final String PLUS = "plus";

    public static final String WITH = "with";

    public static final String WITH_DAY_OF_MONTH = "withDayOfMonth";

    private final StringSubstitutor substitutor = new StringSubstitutor(key -> key);

    private final Map<String, DateResolver> dateResolverMap;

    private static final DateResolver EMPTY_RESOLVER = (ldt, lookup) -> ldt;

    private final LocalDateTime ldt;

    private final Splitter splitter = Splitter.on('.');
    private final Pattern DATE_FUNCTION_PATTERN = Pattern.compile("([\\D^)]+)\\(([^)]+)\\)");

    public DateLookup() {
        this(LocalDateTime.now());
    }

    public DateLookup(LocalDateTime ldt) {
        this.ldt = ldt;
        dateResolverMap = new HashMap<>();
        dateResolverMap.put(TRUNCATED_TO, new TruncateResolver());
        dateResolverMap.put(MINUS, new MinusResolver());
        dateResolverMap.put(PLUS, new PlusResolver());
        dateResolverMap.put(WITH, new WithResolver());
        dateResolverMap.put(WITH_DAY_OF_MONTH, new WithDayOfMonthResolver());
    }

    private String replace(String source) {
        LocalDateTime modifiedTime = ldt;

        String replace = substitutor.replace(source);

        Iterator<String> tokenItr = splitter.split(replace).iterator();
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        while (tokenItr.hasNext()) {
            String token = tokenItr.next();
            Matcher matcher = DATE_FUNCTION_PATTERN.matcher(token);
            while (matcher.find()) {

                String tokenType = matcher.group(1);
                String lookup = matcher.group(2);

                if (tokenType.equals(DATE)) {
                    formatter = DateTimeFormatter.ofPattern(lookup);
                    continue;
                }

                modifiedTime = dateResolverMap.getOrDefault(tokenType, EMPTY_RESOLVER).resolve(modifiedTime, lookup);
            }
        }

        return modifiedTime.format(formatter);
    }

    @Override
    public String lookup(String key) {
        return replace(key);
    }
}
