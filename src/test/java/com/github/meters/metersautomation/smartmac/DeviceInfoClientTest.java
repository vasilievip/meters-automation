package com.github.meters.metersautomation.smartmac;

import com.github.meters.metersautomation.MetersAutomationApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {MetersAutomationApplication.class},
        properties = "feign.client.config.deviceinfo.url=http://localhost:${wiremock.server.port}"
)
@AutoConfigureWireMock(port = 0)
class DeviceInfoClientTest {

    @Autowired
    DeviceInfoClient deviceInfoClient;

    @Value("classpath:smartmacresponse.json")
    private Resource response;

    @Test
    void shouldGetDeviceInfo() throws IOException {
        stubFor(get(urlEqualTo("/api?devid=1&apikey=2"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON.toString())
                                .withBody(IOUtils.toString(response.getInputStream()))
                ));

        List<DeviceInfo> deviceInfos = deviceInfoClient.get("1", "2");

        assertThat(deviceInfos).hasSize(1);
        assertThat(deviceInfos.get(0).id).isEqualTo(1234567890);
        assertThat(deviceInfos.get(0).totalCounter1).isEqualTo(5034);
        assertThat(deviceInfos.get(0).totalCounter2).isEqualTo(5034);
    }

}