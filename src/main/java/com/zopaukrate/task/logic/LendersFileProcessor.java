package com.zopaukrate.task.logic;

import com.zopaukrate.task.model.Lender;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LendersFileProcessor {


    private static final int NAME_COL = 0;
    private static final int RATE_COL = 1;
    private static final int AMOUNT_COL = 2;
    private final String file;

    public LendersFileProcessor(String file) {
        this.file = file;
    }

    public List<Lender> getLenders() {
        try (Stream<String> stream = Files.lines(Paths.get(file)).skip(1)) {
            return stream.map(
                    line -> {
                        List<String> lenderValues = Arrays.stream(line.split(","))
                                .map(String::trim)
                                .collect(Collectors.toList());
                        return new Lender(
                                lenderValues.get(NAME_COL),
                                Double.parseDouble(lenderValues.get(RATE_COL)),
                                Integer.parseInt(lenderValues.get(AMOUNT_COL))
                        );
                    }
            )
            .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("You've provided a file/path that cannot be translated to lenders. " +
                    "We're defaulting to pre-configured lenders");
            return Collections.emptyList();
        }
    }

}