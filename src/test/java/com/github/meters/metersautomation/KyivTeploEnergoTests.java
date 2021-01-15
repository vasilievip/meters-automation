package com.github.meters.metersautomation;

import com.github.meters.metersautomation.kyivteploenergo.KyivTeploEnergoProperties;
import com.github.meters.metersautomation.kyivteploenergo.LoginPage;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.RetryingTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class KyivTeploEnergoTests extends BaseMetersAutomationTest {

    @Autowired
    KyivTeploEnergoProperties teploEnergoProperties;

    @Test
    @RetryingTest(3)
    void teploEnergo() {
        assertThat(teploEnergoProperties.getEmail()).isNotEmpty();
        assertThat(teploEnergoProperties.getPassword()).isNotEmpty();

        String successPopupText = new LoginPage(container.getWebDriver())
                .loginAs(teploEnergoProperties.getEmail(), teploEnergoProperties.getPassword())
                .openCountersPage()
                .copyCountersDataFromPrevious()
                .submit()
                .getSuccessPopupText();
        assertThat(successPopupText).isNotEmpty();
    }

}
