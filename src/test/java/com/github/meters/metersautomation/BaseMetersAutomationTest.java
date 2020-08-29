package com.github.meters.metersautomation;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Paths;

@Testcontainers
@SpringBootTest(classes = MetersAutomationApplication.class)
abstract class BaseMetersAutomationTest {

    @Container
    BrowserWebDriverContainer container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, Paths.get("target").toFile());

}
