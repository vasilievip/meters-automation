package com.github.meters.metersautomation.kyivvodokanal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CounterDataTest {

    @Test
    void shouldExtractCounterDataFromLong() {
        long data1 = 123L;
        CounterData counterData = CounterData.fromNumber(data1);
        assertThat(counterData.asString()).isEqualTo("00000123");
    }

    @Test
    void shouldExtractEmptyCounterDataOnNullValue() {
        CounterData counterData = CounterData.fromNumber(null);
        assertThat(counterData.asString()).isEqualTo("00000000");
    }

    @Test
    void shouldThrowExceptionIfCounterDataTooBig() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CounterData.fromNumber(111111111));
    }

    @Test
    void shouldThrowExceptionIfCounterDataNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> CounterData.fromNumber(-1));
    }

    @Test
    void shouldExtractOnlyIntegerAndDropFractional() {
        double data1 = 1.2;
        CounterData counterData = CounterData.fromNumber(data1);
        assertThat(counterData.asString()).isEqualTo("00000001");
    }
}