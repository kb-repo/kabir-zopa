package com.zopaukrate.task;

import com.zopaukrate.task.logic.*;
import com.zopaukrate.task.model.Lender;

import java.util.Arrays;
import java.util.List;

public class ZopaRateApplication {

    public static void main(String[] arguments) {

        try {
            UserInput input = new UserInput(arguments);
            QuoteCalculator quoteService = new QuoteCalculatorImpl(input.getLoanDurationInMonths().orElse(36));
            QuotePrinter quotePrinter = new ConsoleQuotePrinter();

            List<Lender> lenders = staticLenderList();
            if (input.getLendersFilePath().isPresent()) {
                LendersFileProcessor fileProcessor = new LendersFileProcessor(
                        input.getLendersFilePath().get()
                );
                lenders = fileProcessor.getLenders();
                if (!lenders.isEmpty()) {
                    System.out.println(String.format("using lenders information provided in: %s", input.getLendersFilePath().get()));
                }
            }

            QuoteEntryPoint entryPoint = new QuoteEntryPoint(input, quoteService, quotePrinter);
            entryPoint.printQuote(lenders);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static List<Lender> staticLenderList() {
        return Arrays.asList(
                new Lender("Bob", 0.075, 640),
                new Lender("Jane", 0.069, 480),
                new Lender("Fred", 0.071, 540),
                new Lender("Mary", 0.104, 170),
                new Lender("John", 0.081, 320),
                new Lender("Dave", 0.074, 140),
                new Lender("Angela", 0.071, 60)
        );
    }
}
