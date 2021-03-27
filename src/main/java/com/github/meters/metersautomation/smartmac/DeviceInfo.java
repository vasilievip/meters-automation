package com.github.meters.metersautomation.smartmac;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public final class DeviceInfo {
    public final long id;
    public final double totalCounter1;
    public final double totalCounter2;

    @JsonCreator
    public DeviceInfo(@JsonProperty("ID") long id,
                      @JsonProperty("TCh1") long tCh1,
                      @JsonProperty("TCh2") long tCh2) {
        this.id = id;
        this.totalCounter1 = tCh1;
        this.totalCounter2 = tCh2;
    }

    public String totalCounter1AsString(){
        return BigDecimal.valueOf(getTotalCounter1() / 1000.).toString();
    }

    public String totalCounter2AsString(){
        return BigDecimal.valueOf(getTotalCounter2() / 1000.).toString();
    }
}
