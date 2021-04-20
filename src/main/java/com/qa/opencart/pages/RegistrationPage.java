package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private ElementUtil eleut;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		eleut = new ElementUtil(driver);
	}

	public boolean fillregisterform(String firstName, String lastName, String email, String telephone, String password,
			String subsribe) {
		eleut.Dosendkeys(this.firstName, firstName); // to access class and method variables
		eleut.Dosendkeys(this.lastName, lastName);
		eleut.Dosendkeys(this.email, email);
		eleut.Dosendkeys(this.telephone, telephone);
		eleut.Dosendkeys(this.password, password);
		eleut.Dosendkeys(this.confirmpassword, password);

		if (subsribe.equals("yes")) {
			eleut.doclick(subscribeYes);
		} else {
			eleut.doclick(subscribeNo);
		}
		eleut.doclick(agreeCheckBox);
		eleut.doclick(continueButton);

		WebElement ele = eleut.waitforelementvisiblelocated(sucessMessg, 5);
		String successtext = ele.getText();
		System.out.println(successtext);

		if (successtext.contains(Constants.Register_Successtext)) {
			eleut.doclick(logoutLink);
			eleut.doActionsClick(registerLink);
			return true;
		}

		return false;
	}

}
