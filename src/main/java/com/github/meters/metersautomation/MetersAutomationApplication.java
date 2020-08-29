package com.github.meters.metersautomation;

import com.github.meters.metersautomation.kyivteploenergo.KyivTeploEnergoProperties;
import com.github.meters.metersautomation.kyivvodokanal.KyivVodoKanalProperties;
import com.github.meters.metersautomation.smartmac.DeviceInfoClient;
import com.github.meters.metersautomation.smartmac.SmartMacProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({
		KyivTeploEnergoProperties.class,
		KyivVodoKanalProperties.class,
		SmartMacProperties.class
})
@EnableFeignClients(clients = DeviceInfoClient.class)
public class MetersAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetersAutomationApplication.class, args);
	}

}
