package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsutil;

	public ElementUtil(WebDriver driver) { // pass driver as argument
		this.driver = driver; // to initialize with local variables with class variables
		jsutil=new JavaScriptUtil(driver);
	}

	// to get element
	public WebElement getWebElement(By loc) {
		WebElement ele=driver.findElement(loc);
		if (Boolean.parseBoolean(DriverFactory.highlight))
		{
			jsutil.flash(ele);
		}
		
		return ele;
	}

	// to get elements
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doActionsSendKeys(By loca, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getWebElement(loca), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getWebElement(locator)).perform();
	}

	public void Dosendkeys(By loca, String Value) {
		WebElement element=getWebElement(loca);
		element.clear();
		getWebElement(loca).sendKeys(Value);
	}

	public void doclick(By locator) {
		getWebElement(locator).click();
	}

	public String dogetText(By LOC) {
		return getWebElement(LOC).getText();
	}
	

	public boolean iseledisplayed(By Loc) {
		return getWebElement(Loc).isDisplayed();
	}

	public List<String> getElementsTextList(By LOC) {
		List<WebElement> elelist = getElements(LOC);
		List<String> eletextlist = new ArrayList<String>();

		for (WebElement e : elelist) {
			if (!e.getText().isEmpty()) {
				eletextlist.add(e.getText());
			}
		}
		return eletextlist;
	}

	public List<String> getElementsAttribute(By loca, String attname) {
		List<WebElement> elelist = getElements(loca);
		List<String> altlist = new ArrayList<String>();

		for (WebElement e : elelist) {
			if (!e.getText().isEmpty()) {
				altlist.add(e.getAttribute(attname));
			}
		}
		return altlist;
	}

	// Dropdown utilities.
	public void DoSelectDropdownbyindex(By loca, int index) {
		Select selind = new Select(getWebElement(loca));
		selind.selectByIndex(index);
	}

	// we are avoiding method overloading here as user will get confused b/w value
	// and
	// visible text as args are same for both methods.

	public void DoSelectDropdownbyvisibletext(By loca, String visibletext) {
		Select selind = new Select(getWebElement(loca));
		selind.selectByVisibleText(visibletext);
	}

	public void DoSelectDropdownbyvalue(By loca, String valuetext) {
		Select selind = new Select(getWebElement(loca));
		selind.selectByValue(valuetext);
	}

	public void DoSelectDropdownvalue(By locator, String value) {
		Select sel = new Select(getWebElement(locator));
		List<WebElement> optionslist = sel.getOptions();
		System.out.println(optionslist.size());
		for (WebElement e : optionslist) {
			String text = e.getText();
			// System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void DoSelectDropdownwithoutselect(By locator, String value) {
		List<WebElement> lists = getElements(locator);
		for (WebElement e : lists) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public String switchtowindowandgettitle(String windowid) {
		driver.switchTo().window(windowid);
		String title = driver.getTitle();
		return title;
	}

	public void doactionsmovetoelement(By loca) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getWebElement(loca)).perform(); // perform the action
	}

	public void doactionsmovetoelementandclick(By locator) throws InterruptedException {
		doactionsmovetoelement(locator);
		getWebElement(locator).click();
	}

	// wait utils
	/*
	 * presenceOfElementLocated : An expectation for checking that an element is
	 * present on the DOM of a page. This does not necessarily mean that the element
	 * is visible
	 */
	public WebElement waitforelementpresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitforelementvisiblelocated(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitforWebelementvisiblewithelement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getWebElement(locator)));
	}

	public List<WebElement> waitforvisibilityofelements(By loc, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loc));
	}

	// wait for Alert generic Utils
	public Alert waitforAlertPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeout) {
		return waitforAlertPresent(timeout).getText();
	}

	public void acceptalert(int timeout) {
		waitforAlertPresent(timeout).accept();
	}

	public void dismissalert(int timeout) {
		waitforAlertPresent(timeout).dismiss();
	}

	public String waitfortitle(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public String waitfortitle(String title, int timeout, int polltime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout, polltime);
		// poll frequency for every 0.2 secs
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public String waitfortitlecontains(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitforurl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.urlContains(url));
		return driver.getCurrentUrl();
	}
	
	public String waitforpageurl() {
		return driver.getCurrentUrl();
	}

	public Boolean waitforurlwithoutreturningurl(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));

	}

	public void switchtoframeusingname(int timeout, String frameidorname) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameidorname));
	}

	public void switchtoframeusingloc(int timeout, By loc) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(loc));
	}

	public void switchtoframeusingWebElement(int timeout, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}

	public void switchtoframeusingindex(int timeout, int index) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public List<WebElement> waitforvisibilityofallelementslocatedby(By loc, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(loc));
	}

	// apply stream on List<WebElement> using
	// waitforvisibilityofallelementslocatedby().
	public void printelementstext(By loc, int timeout) {
		waitforvisibilityofallelementslocatedby(loc, timeout).stream().forEach(e -> System.out.println(e.getText()));
	}

	// print all the elements text which has List<String> as return type.
	public void printListElements(List<String> elelist) {
		elelist.forEach(e -> System.out.println(e));
	}

	// use map to get List<String> to filter values based on String.
	public List<String> getelementslistwithtext(By loc, int timeout, String filtervalue) {
		return waitforvisibilityofallelementslocatedby(loc, timeout).stream()
				.filter(e -> e.getText().contains(filtervalue)).map(e -> e.getText()).collect(Collectors.toList());
	}

	// don't use map to get List<WebElement> to filter values based on String.
	public List<WebElement> getelementslist(By loc, int timeout, String filtervalue) {
		return waitforvisibilityofallelementslocatedby(loc, timeout).stream()
				.filter(e -> e.getText().contains(filtervalue)).collect(Collectors.toList());
	}

	// method created specifically for locators tagname, to get their size, print
	// their text.
	public List<WebElement> waitforpresenceofallelementslocated(By loc, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(loc));
	}

	public List<String> getElementsTextList(By locator, int timeout) {
		List<WebElement> elelist = waitforvisibilityofallelementslocatedby(locator, timeout);
		List<String> eletextlist = new ArrayList<String>(); // list for storing elements text

		for (WebElement e : elelist) {
			if (!e.getText().isEmpty())
			/// if text is not empty, store them in another list and return that list.
			{
				eletextlist.add(e.getText());
			}
		}
		return eletextlist;
	}

	public WebElement waitforelementtobeclickable(By loca, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(loca));
	}

	public void clickwhenready(By loca, int timeout) {
		waitforelementtobeclickable(loca, timeout).click();
	}

	public WebElement fluentwaitforelement(By locator, int timeout, long pollout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public Alert fluentwaitforalert(int timeout, long pollout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(200)).ignoring(NoAlertPresentException.class);

		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebDriver fluentwaitforframe(String frameloc, int timeout, long pollout) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchFrameException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameloc));
	}

}
