package com.asha.learninigselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstSeleniumClass {
	WebDriver wd;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\ChromeDriver.exe");
		wd = new ChromeDriver();

		wait = new WebDriverWait(wd, 10);
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wd.manage().window().maximize();
		// wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void validateEnquirySubmit() {

		WebElement contactUsLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Contact Us']")));

		contactUsLink.click();

		WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-name")));
		WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		WebElement enquiry = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-enquiry")));

		name.sendKeys("John");
		email.sendKeys("john@gmail.com");
		enquiry.sendKeys("Sample Enquiry");

		WebElement submitButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit']")));
		submitButton.click();

		WebElement successMessage = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[id='content'] > p")));
		System.out.println("Page title: " + wd.getTitle());
		System.out.println("Messsage:  " + successMessage.getText());

		Assert.assertEquals(successMessage.getText(), "Your enquiry has been successfully sent to the store owner!",
				"Unable to submit Enquiry form successfully");

	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}
}
