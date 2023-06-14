package com.asha.learninigselenium;

import java.util.Random;

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
	Random random = new Random();

	String randString = String.valueOf(random.nextInt(1000));
	String email_id = "xyz" + randString + "@gmail.com";

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
		System.out.println(email_id);
		System.out
				.println("Scenario: validate user is able to register if all mandatory fields are provided correctly");

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

		WebElement lastName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-lastname")));

		WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));

		WebElement telephone = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-telephone")));

		WebElement password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));

		WebElement passwordConfirm = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-confirm")));

		WebElement radioButtonNo = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='radio'][value='0']")));

		radioButtonNo.click();

		firstName.sendKeys("Asha");
		lastName.sendKeys("Jay");
		email.sendKeys(email_id);
		telephone.sendKeys("123456789");
		password.sendKeys("password1");
		passwordConfirm.sendKeys("password1");

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

	}

//	@Test(priority = 2)
	public void validateSignUpWhenPasswordAndConfirmPasswordIsDifferent() {
		System.out.println(
				"Scenario: Validate Correct error message (Password confirmation does not match password!) is displayed when user provided Password and Confirm password are different");
		String emailid = "test001@gmail.com";

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

		WebElement lastName = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-lastname")));

		WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));

		WebElement telephone = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-telephone")));

		WebElement password = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));

		WebElement passwordConfirm = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-confirm")));

		WebElement radioButtonNo = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='radio'][value='0']")));

		WebElement checkBox = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='agree']")));

		firstName.sendKeys("Asha");
		lastName.sendKeys("Jay");
		email.sendKeys(emailid);
		telephone.sendKeys("123456789");
		password.sendKeys("password1");
		passwordConfirm.sendKeys("password3");
		radioButtonNo.click();
		checkBox.click();

		WebElement continueBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Continue']")));
		continueBtn.click();
		WebElement passwordConfrimationError = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='text-danger' and text()='Password confirmation does not match password!']")));

		Assert.assertEquals(passwordConfrimationError.getText(), "Password confirmation does not match password!",
				"Invalid message displayed when password and Password Confirm are different");

	}

	@Test(priority = 3)
	public void validateLogin() {
		System.out.println("Scneario: Validate Login when user provided valid email id and password");

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

	// @Test(priority = 4)
	public void validateLoginWhenUserProvidedInvalidEmailID() {
		System.out.println(
				"Scenario: When user provided incorrect email id not registered before, User will get an error message 'Warning: No match for E-Mail Address and/or Password.'");

		WebElement myAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));

		myAccount.click();

		WebElement Login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Login']")));
		Login.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Account Login", "Invalid page");

		WebElement returningCustomerEmailID = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		returningCustomerEmailID.sendKeys("invalid001@gmailinvalid.com");

		WebElement returningCustomerPassword = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		returningCustomerPassword.sendKeys("password1");

		WebElement loginBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Login']")));
		loginBtn.click();
		WebElement LoginWarningMesasge = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]")));

		Assert.assertEquals(LoginWarningMesasge.getText(), "Warning: No match for E-Mail Address and/or Password.",
				"Invalid warning message displayed when user provided invalid email id");

	}

//	@Test(priority = 5)
	public void validateLoginWhenUserProvidedInvalidPassword() {
		System.out.println(
				"Scenario: When user provided incorrect password, User will get an error message 'Warning: No match for E-Mail Address and/or Password.'");

		WebElement myAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']")));

		myAccount.click();

		WebElement Login = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Login']")));
		Login.click();

		System.out.println("Page title: " + wd.getTitle());
		Assert.assertEquals(wd.getTitle(), "Account Login", "Invalid page");

		WebElement returningCustomerEmailID = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-email")));
		returningCustomerEmailID.sendKeys("xyz8@gmail.com");

		WebElement returningCustomerPassword = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#input-password")));
		returningCustomerPassword.sendKeys("passwordInvalid");

		WebElement loginBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[value='Login']")));
		loginBtn.click();
		WebElement LoginWarningMesasge = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class=\"alert alert-danger alert-dismissible\"]")));

		Assert.assertEquals(LoginWarningMesasge.getText(), "Warning: No match for E-Mail Address and/or Password.",
				"Invalid warning message displayed when user provided incorrect password");

	}

	@Test(priority = 6)
	public void validateChangePassword() {
		System.out.println("Scenario : User is able to change the password successfully");

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

	@Test(priority = 7)
	public void loginWithCorrectCurrentPassword() {
		System.out.println("Scenario: User is able to login with Updated password");
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
