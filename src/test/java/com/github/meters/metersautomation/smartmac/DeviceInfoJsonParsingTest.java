package com.github.meters.metersautomation.smartmac;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DeviceInfoJsonParsingTest {

    @Autowired
    ObjectMapper objectMapper;

    @Value("classpath:smartmacresponse.json")
    private Resource response;

    @Test
    void testJsonParsing() throws IOException {
        List<DeviceInfo> devices = objectMapper.readValue(response.getInputStream(), new TypeReference<List<DeviceInfo>>() {
        });

        assertThat(devices).hasSize(1);
        assertThat(devices.get(0)).isNotNull();
        assertThat(devices.get(0).id).isGreaterThan(0);
        assertThat(devices.get(0).totalCounter1).isGreaterThan(0);

    }
}