package com.model.launch;

import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchNewTest {

	static String Mytitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	
	
	@Test
	public void launch()
	
	{
		WebDriver driver = new ChromeDriver();
		
		

		WebDriverManager.chromedriver().setup();

		
		driver.manage().window().maximize();
		Actions actions = new Actions(driver);

		driver.navigate().to("https://www.amazon.in/");

		String title = driver.getTitle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		
		WebElement hover = driver.findElement(By.xpath("//*[@id='nav-tools']/a/span/span[2]/div"));
		actions.moveToElement(hover).perform();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nav-flyout-icp\"]/div[2]/a[2]/span/span[1]")));
		

		if (title.equalsIgnoreCase(Mytitle))

		{

			System.out.println("Pass");

		}

		else {

			System.out.println("fail");
		}

		System.out.println(title);

		driver.quit();

	}
	
	
	public static void main(String[] args) throws InterruptedException
	
	{

		LaunchNewTest lt = new LaunchNewTest();
		lt.launch();
		
		

}}
