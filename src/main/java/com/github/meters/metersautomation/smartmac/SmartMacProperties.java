package com.github.meters.metersautomation.smartmac;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "smartmac")
public class SmartMacProperties {
    String coldCounterDeviceId;
    String apiKey;
}
