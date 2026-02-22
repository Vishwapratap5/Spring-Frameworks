package com.guru.springsecurity_learning.Service;

import com.guru.springsecurity_learning.Enums.CardType;
import org.springframework.stereotype.Service;


@Service
public class CardNumberGenerationServiceImpl
        implements CardNumberGenerationService {

    private static final String DEBIT_BIN = "400000";
    private static final String CREDIT_BIN = "500000";

    @Override
    public String generateCardNumber(CardType cardType) {

        String bin = (cardType == CardType.DEBIT)
                ? DEBIT_BIN
                : CREDIT_BIN;

        // 16-digit card number:
        // BIN (6) + random (9) + check digit (1)
        String randomPart = generateRandomDigits(9);
        String partial = bin + randomPart;

        int checkDigit = luhnCheckDigit(partial);
        return partial + checkDigit;
    }

    private String generateRandomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    // Luhn algorithm (industry standard)
    private int luhnCheckDigit(String number) {
        int sum = 0;
        boolean alternate = true;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }
}

/*
 * Luhn Algorithm (Check Digit)
 *
 * Purpose:
 * - Used to validate card numbers at structural level
 * - Detects common input errors (wrong digit, swapped digits)
 *
 * What it does NOT do:
 * - Does NOT provide security
 * - Does NOT prevent fraud
 * - Does NOT authorize transactions
 *
 * Why we use it here:
 * - To generate industry-like card numbers
 * - To reject invalid card numbers early (before DB lookup)
 * - To keep design compatible with real banking systems
 *
 * In short:
 * Luhn digit = checksum for card number correctness
 */

// Card number = BIN (issuer) + random digits + Luhn check digit