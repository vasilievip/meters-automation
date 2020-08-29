package com.github.meters.metersautomation.kyivteploenergo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kyivteploenergo")
public class KyivTeploEnergoProperties {
    String email;
    String password;
}
