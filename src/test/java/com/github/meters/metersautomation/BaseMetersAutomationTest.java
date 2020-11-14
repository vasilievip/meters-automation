package com.github.meters.metersautomation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Testcontainers
@SpringBootTest(classes = MetersAutomationApplication.class)
@Slf4j
abstract class BaseMetersAutomationTest {

    @Container
    BrowserWebDriverContainer container = new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, Paths.get("target").toFile());

    @AfterAll
    public static void cleanUpEach() {
        List<File> files = getVideoFilesFromTarget();

        for (File video : files) {
            video.deleteOnExit();
            if(!video.delete()){
                log.error("File {} was not deleted...", video.getAbsolutePath());
            }
        }
    }

    private static List<File> getVideoFilesFromTarget() {
        Path dir = Paths.get("target");
        List<File> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{flv}")) {
            for (Path entry : stream) {
                files.add(entry.toFile());
            }
        } catch (IOException x) {
            log.error("Error reading folder {}: {}", dir, x.getMessage(), x);
        }
        return files;
    }
}
