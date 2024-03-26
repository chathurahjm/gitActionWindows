package org.example;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Test
public class vpntest {

    protected WebDriver driver;


    @Test
    public void testActions2() throws InterruptedException {

        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < TimeUnit.SECONDS.toMillis(10)) {


            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("windows"))
            {
                WebDriverManager.chromedriver().setup();

                //ChromeOptions chromeOptions = new ChromeOptions();
            }
            else {


                System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  OS");


            }
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");

            chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36");

            driver = new ChromeDriver(chromeOptions);

            driver.get("https://whatismyipaddress.com/");
            Thread.sleep(3000);
            takeScreenHhot();
            driver.quit();

        }
    }


    public void takeScreenHhot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        LocalDateTime currentTime = LocalDateTime.now();

        // Define a formatter for the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss");

        // Format the current time as a string
        String currentTimeAsString = currentTime.format(formatter);

        // Define the path to save the screenshot
        Path destination = Paths.get(System.getProperty("user.dir") + "/screenshots/" + currentTimeAsString + ".png");

        try {
            // Copy screenshot to the destination
            Files.copy(source.toPath(), destination);
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX  Screenshot taken");
            System.out.println(System.getProperty("user.dir"));
            System.out.println(destination);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
