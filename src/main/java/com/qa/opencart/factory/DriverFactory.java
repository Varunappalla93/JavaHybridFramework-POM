package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author VARUN This class is used to initialize the webdriver. Shortcuts-
 *         Ctrl+Shift+O for importing the packages, Ctrl+Shift+F for code
 *         formatting.
 */

public class DriverFactory {

	WebDriver driver; // initialize the driver at class level
	Properties prop; // initialize the properties at class level

	private OptionsManager optionsmanager;

	// for JS Flash
	public static String highlight = null;

	// Threadlocal concept for making all the threads use same Webdriver instance
	// for parallel execution.

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param browsername
	 * @return this method will return WebDriver object driver
	 */

	// method to initialize the webdriver
	public WebDriver init_driver(Properties prop) { // also used cross browser testing

		// get from config file
		highlight = prop.getProperty("highlight");
		optionsmanager = new OptionsManager(prop);

		String browsername = prop.getProperty("browser").trim();
		System.out.println("Browser name is " + browsername);

		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsmanager.getChromeOptions());
			// set threadlocaldriver using set()
			tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));

		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsmanager.getFireFoxOptions());
			tldriver.set(new FirefoxDriver(optionsmanager.getFireFoxOptions()));
		} else {
			System.out.println("Please enter the correct browser");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		// driver.get("https://demo.opencart.com/index.php?route=account/login");
		getDriver().get(prop.getProperty("url").trim());

		return getDriver(); // return the driver, so now void becomes WebDriver
	}

	// to get threadlocal webdriver
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * 
	 * @return this method will return the Properties object prop
	 */

	// method to read data from config.properties
	public Properties init_prop() {
		FileInputStream fis = null;
		prop = new Properties();

		String env = System.getProperty("env");

		// code to get our req environments- qa, staging etc via cmd
		if (env == null) {
			System.out.println("Running on Environment:--> PROD");
			try {
				fis = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on Environment:--> " + env);
			try {
				switch (env) {
				case "qa":
					fis = new FileInputStream(
							"C:\\Users\\VARUN\\eclipse-workspace\\DesignPattern_POM\\src\\test\\resources\\config\\qa.config.properties");
					break;

				case "stage":
					fis = new FileInputStream(
							"C:\\Users\\VARUN\\eclipse-workspace\\DesignPattern_POM\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "dev":
					fis = new FileInputStream(
							"C:\\Users\\VARUN\\eclipse-workspace\\DesignPattern_POM\\src\\test\\resources\\config\\dev.config.properties");
					break;

				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception");
			e.printStackTrace();
		}

		return prop;
	}

	/*
	 * Take screenshot method
	 */

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";

		File dest=new File(path);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {		
			e.printStackTrace();
		}
		return path;
	}
}
