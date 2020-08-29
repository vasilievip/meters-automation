package com.github.meters.metersautomation.smartmac;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class DeviceInfo {
    public final long id;
    public final long totalCounter1;
    public final long totalCounter2;

    @JsonCreator
    public DeviceInfo(@JsonProperty("ID") long id,
                      @JsonProperty("TCh1") long tCh1,
                      @JsonProperty("TCh2") long tCh2) {
        this.id = id;
        this.totalCounter1 = tCh1;
        this.totalCounter2 = tCh2;
    }
}
