package NaveenAutomationLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Saucedemo {

	WebDriver driver;

	@BeforeTest
	public void openBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void Login() {

		WebElement userName = driver.findElement(By.id("user-name"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("login-button"));		
		enterText(userName,"standard_user");
		enterText(password,"secret_sauce");
		loginButton.click();
	}

	@Test(priority = 2)
	public void addFunction() {
		List<Double> priceOfEach = new ArrayList<Double>();
		List<WebElement> priceAmount = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		List<WebElement> addToCart = driver.findElements(By.xpath("//*[@class='inventory_item']//descendant::button"));

		for (WebElement i : priceAmount) {
			priceOfEach.add(Double.valueOf(i.getText().replace("$", "")));
		}

		
		Double Max_val = Collections.max(priceOfEach, null);

		int pos = priceOfEach.indexOf(Max_val);

		addToCart.get(pos).click();

	}
	
	public void enterText(WebElement text,String name) {
		text.click();
		text.clear();
		text.sendKeys(name);
	}

}
