package com.monds.date.substitutor;

import org.apache.commons.text.StringSubstitutor;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "date-mapper-gen")
public class DateMapperGenerator implements Callable<Integer> {

    @Option(names = "--date-rules")
    private String dateRules = "classpath:/daterules.json";

    @Option(names = "--out", required = true)
    private String out;

    @Option(names = "--datetime", required = true, converter = LocalDateTimeConverter.class)
    private LocalDateTime dateTime;

    @Option(names = "--minus-days")
    private Integer amountToMinusDays = 0;

    public static class LocalDateTimeConverter implements CommandLine.ITypeConverter<LocalDateTime> {
        @Override
        public LocalDateTime convert(String value) {
            return PredefinedDateFormatter.parse(value);
        }
    }

    private String readDateRules() throws IOException {
        if (dateRules.startsWith("classpath:")) {
            InputStream inputStream = getClass().getResourceAsStream(dateRules.replace("classpath:", ""));
            return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().collect(Collectors.joining("\n"));
        }
        return new String(Files.readAllBytes(Paths.get(dateRules)));
    }

    @Override
    public Integer call() throws Exception {

        dateTime = dateTime.minusDays(amountToMinusDays);

        StringSubstitutor substitutor = new StringSubstitutor(new DateLookup(dateTime));

        String rules = readDateRules();

        Files.write(Paths.get(out), substitutor.replace(rules).getBytes(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);

        return 0;
    }


    public static void main(String[] args) {
        new CommandLine(new DateMapperGenerator()).execute(args);
    }
}
