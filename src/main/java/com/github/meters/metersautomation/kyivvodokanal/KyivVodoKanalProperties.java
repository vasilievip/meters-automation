package com.github.meters.metersautomation.kyivvodokanal;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "kyivvodokanal")
public class KyivVodoKanalProperties {
    String email;
    String password;
}
