package com.model.launch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetWebElemenlist {

	public void launch()

	{
		WebDriver driver = new ChromeDriver();

		WebDriverManager.chromedriver().setup();

		driver.manage().window().maximize();

		try {
			// Navigate to a webpage
			driver.get("https://www.bikewale.com"); // Replace with your target URL

			// Find all elements matching a specific locator (e.g., all links)
			List<WebElement> links = driver.findElements(By.tagName("a"));

			// Check if any elements were found
			if (!links.isEmpty()) {
				// Iterate through the list and perform actions on each element
				System.out.println("Number of links found: " + links.size());
				for (WebElement link : links) {
					String linkText = link.getText();
					String linkUrl = link.getAttribute("href");

					System.out.println("Text: " + linkText + ", URL: " + linkUrl);

					// You can perform other actions on each element here, such as clicking, getting
					// attributes, etc.
				}
			} else {
				System.out.println("No links found on the page.");
			}
		}

		finally {
			// Close the browser
			driver.quit();
		}

	}
	
	public static void main(String[] args) {
		
		GetWebElemenlist ob = new GetWebElemenlist();
		ob.launch();
		
		
		
	}
}
