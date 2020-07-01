package com.monds.date.substitutor;

import static org.junit.Assert.*;

import org.junit.Test;
import picocli.CommandLine;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DateMapperGeneratorTest {

    @Test
    public void testDateMapperGenerator() {

        String out = "C:\\Users\\suahn\\IdeaProjects\\date-substitutor\\log\\test.json";

        String[] args = {
            "--date-rules=C:\\Users\\suahn\\IdeaProjects\\date-substitutor\\src\\main\\resources\\daterules.json",
            "--out=" + out,
            "--datetime=20200528145300",
            "--minus-days=1"
        };

        new CommandLine(new DateMapperGenerator()).execute(args);

        assertTrue(Files.exists(Paths.get(out)));
    }
}