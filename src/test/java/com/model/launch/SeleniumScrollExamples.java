package com.model.launch;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumScrollExamples {

    public static void main(String[] args) throws InterruptedException {


        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();

        driver.get("https://www.wikipedia.org/"); // Example site

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Scroll down by pixel (e.g., 500 pixels)
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(1000); // Pause to see the effect

        // 2. Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1000);

        // 3. Scroll back to the top of the page
        js.executeScript("window.scrollTo(0, 0)");
        Thread.sleep(1000);

        // 4. Scroll to a specific element (if you have an element)
        // For demonstration, let's pretend we have an element with id "footer"
        // (Replace with a real element if you have one)
        try {
            js.executeScript("document.getElementById('footer').scrollIntoView();");
            Thread.sleep(1000);
        } catch(Exception e){
            System.out.println("No element with id footer found, skipping scroll to element example.");
        }

        // 5. Scroll Horizontally (if applicable)
        driver.get("https://www.w3schools.com/cssref/tryit.php?filename=trycss3_scroll-snap-type");
        driver.switchTo().frame("iframeResult");
        js.executeScript("document.querySelector('.example').scrollLeft += 200;");
        Thread.sleep(1000);

        driver.quit();
    }
}
