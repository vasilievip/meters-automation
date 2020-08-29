package com.github.meters.metersautomation.kyivvodokanal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CounterData {
    String digit1;
    String digit2;
    String digit3;
    String digit4;
    String digit5;
    String digit6;
    String digit7;
    String digit8;

    public static CounterData empty() {
        return CounterData.builder()
                .digit1("0")
                .digit2("0")
                .digit3("0")
                .digit4("0")
                .digit5("0")
                .digit6("0")
                .digit7("0")
                .digit8("0")
                .build();
    }

    public static CounterData fromNumber(Number newData) {
        if (newData == null) return empty();

        if (newData.longValue() < 0)
            throw new IllegalArgumentException("Cannot convert " + newData + " negative value to counter data!");

        String s1 = String.valueOf(newData.longValue());

        if (s1.length() > 8)
            throw new IllegalArgumentException("Cannot convert " + newData + " value to 8 digits counter data!");

        s1 = String.format("%8s", s1).replace(' ', '0');

        return CounterData.builder()
                .digit1("" + s1.charAt(0))
                .digit2("" + s1.charAt(1))
                .digit3("" + s1.charAt(2))
                .digit4("" + s1.charAt(3))
                .digit5("" + s1.charAt(4))
                .digit6("" + s1.charAt(5))
                .digit7("" + s1.charAt(6))
                .digit8("" + s1.charAt(7))
                .build();
    }

    public String[] asArray() {
        return new String[]{digit1, digit2, digit3, digit4, digit5, digit6, digit7, digit8};
    }

    public String asString() {
        return digit1 + digit2 + digit3 + digit4 + digit5 + digit6 + digit7 + digit8;
    }
}
