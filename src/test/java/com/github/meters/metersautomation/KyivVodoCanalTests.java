package com.github.meters.metersautomation;

import com.github.meters.metersautomation.kyivvodokanal.KyivVodoKanalProperties;
import com.github.meters.metersautomation.kyivvodokanal.LoginPage;
import com.github.meters.metersautomation.smartmac.DeviceInfo;
import com.github.meters.metersautomation.smartmac.DeviceInfoClient;
import com.github.meters.metersautomation.smartmac.SmartMacProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KyivVodoCanalTests extends BaseMetersAutomationTest {

    @Autowired
    DeviceInfoClient deviceInfoClient;

    @Autowired
    KyivVodoKanalProperties vodoKanalProperties;

    @Autowired
    SmartMacProperties smartMacProperties;

    @Test
    void vodokanal() {
        assertThat(smartMacProperties.getApiKey()).isNotEmpty();
        assertThat(smartMacProperties.getColdCounterDeviceId()).isNotEmpty();

        List<DeviceInfo> deviceInfos = deviceInfoClient.get(smartMacProperties.getColdCounterDeviceId(), smartMacProperties.getApiKey());

        assertThat(deviceInfos.size()).isEqualTo(1);

        DeviceInfo coldCounterRealtimeData = deviceInfos.get(0);

        assertThat(vodoKanalProperties.getEmail()).isNotEmpty();
        assertThat(vodoKanalProperties.getPassword()).isNotEmpty();

        new LoginPage(container.getWebDriver())
                .loginAs(vodoKanalProperties.getEmail(), vodoKanalProperties.getPassword())
                .openCountersPage()
                .setColdCounterData(coldCounterRealtimeData.totalCounter1AsString())
                .copyHotCounterDataFromPrevious()
                .submitCounters();
    }
}
