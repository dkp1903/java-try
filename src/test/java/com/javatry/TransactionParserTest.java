package com.javatry;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionParserTest {

    @Test
    public void testValidDescriptions() {
        String input = "  Payment   from USER_001 at 09:00  ,payment FROM   user_002  at 13:15  ";
        List<String> expected = Arrays.asList(
            "payment from user_001 at 09:00",
            "payment from user_002 at 13:15"
        );
        assertEquals(expected, TransactionParser.normalizeDescriptions(input));
    }

    @Test
    public void testMissingTime() {
        String input = "payment from user_abc";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TransactionParser.normalizeDescriptions(input);
        });
        assertTrue(exception.getMessage().contains("Invalid transaction format"));
    }
    
    @Test
    public void testMissingTimeField() {
        String input = "Payment from user_xyz"; // no time info
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TransactionParser.normalizeDescriptions(input);
        });
        assertTrue(exception.getMessage().contains("Invalid transaction format"));
    }
    @Test
    public void testMessyInputFormatting() {
        String input = "   PAYMENT   From   User_X   At  01:30   ";
        List<String> expected = List.of("payment from user_x at 01:30");
        assertEquals(expected, TransactionParser.normalizeDescriptions(input));
    }

    @Test
    public void testMixedValidAndInvalid() {
        String input = "payment from user_1 at 08:00, payment from user_2, payment from user_3 at 10:30";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TransactionParser.normalizeDescriptions(input);
        });

        assertTrue(exception.getMessage().contains("Invalid transaction format"));
    }

} 
