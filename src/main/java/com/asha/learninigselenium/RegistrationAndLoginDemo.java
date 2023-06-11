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

public class RegistrationAndLoginDemo {

	WebDriver wd;
	WebDriverWait wait;
	String email_id = "xyz11@gmail.com";

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\ChromeDriver\\ChromeDriver.exe");
		wd = new ChromeDriver();

		wait = new WebDriverWait(wd, 10);
		wd.get("https://naveenautomationlabs.com/opencart/index.php?route=common/home");
		wd.manage().window().maximize();

	}

	@Test(priority = 1)
	public void validateSignUp() {
		System.out.println("Inside validateRegistration");

		WebElement myAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));
		myAccount.click();

		WebElement register = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Register']")));
		register.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Register Account", "Incorrect page loaded");

		WebElement firstName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-firstname")));
		firstName.sendKeys("Asha");

		WebElement lastName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-lastname")));
		lastName.sendKeys("Jay");

		WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		email.sendKeys(email_id);

		WebElement telephone = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-telephone")));
		telephone.sendKeys("123456789");

		WebElement password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		password.sendKeys("password1");

		WebElement passwordConfirm = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-confirm")));
		passwordConfirm.sendKeys("password1");

		WebElement radioButtonNo = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='radio'][value='0']")));
		// boolean isNoButtonSelected = radioButtonNo.isSelected();
		// Assert.assertTrue(isNoButtonSelected, "Radio Button No is not selected");
		radioButtonNo.click();

		WebElement checkBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='agree']")));
		checkBox.click();

		WebElement continueBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Continue']")));
		boolean isButtonEnabled = continueBtn.isEnabled();
		Assert.assertTrue(isButtonEnabled, "Button is not enabled");
		continueBtn.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Your Account Has Been Created!", "Registration Failed");
		WebElement continueAccountBtn = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Continue']")));
		continueAccountBtn.click();

	}

	@Test(priority = 2)
	public void validateLogin() {
		System.out.println("Inside validateLogin");

		WebElement myAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));

		myAccount.click();

		WebElement Login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Login']")));
		Login.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Account Login", "Invalid page");

		WebElement returningCustomerEmailID = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		returningCustomerEmailID.sendKeys(email_id);

		WebElement returningCustomerPassword = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		returningCustomerPassword.sendKeys("password1");

		WebElement loginBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Login']")));
		loginBtn.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "My Account", "Login failed");
	}

	@Test(priority = 3)
	public void validateChangePassword() {
		System.out.println("Inside validateChangePassword");

		WebElement myAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));

		myAccount.click();

		WebElement Login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Login']")));
		Login.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Account Login", "Invalid page");

		WebElement returningCustomerEmailID = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		returningCustomerEmailID.sendKeys(email_id);

		WebElement returningCustomerPassword = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		returningCustomerPassword.sendKeys("password1");

		WebElement loginBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Login']")));
		loginBtn.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "My Account", "Login failed");

		WebElement changePassword = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Change your password']")));
		changePassword.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Change Password", "failed to change password");

		WebElement change_Password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		change_Password.sendKeys("password2");

		WebElement changePasswordConfirm = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-confirm")));
		changePasswordConfirm.sendKeys("password2");

		WebElement changePasswordContinue = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[value='Continue']")));
		changePasswordContinue.click();

		WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[text()=' Success: Your password has been successfully updated.']")));
		System.out.println("Page title: " + wd.getTitle());
		System.out.println("Messsage:  " + successMessage.getText());
		Assert.assertEquals(successMessage.getText(), "Success: Your password has been successfully updated.",
				"Unable to change the password");

	}

	@Test(priority = 4)
	public void loginWithCurrentPassword() {
		WebElement myAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));

		myAccount.click();

		WebElement Login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Login']")));
		Login.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Account Login", "Invalid page");

		WebElement returningCustomerEmailID = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		returningCustomerEmailID.sendKeys(email_id);

		WebElement returningCustomerPassword = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		returningCustomerPassword.sendKeys("password2");

		WebElement loginBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Login']")));
		loginBtn.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "My Account", "Login failed");

	}

	@AfterMethod
	public void tearDown() {
		wd.close();
	}

}
