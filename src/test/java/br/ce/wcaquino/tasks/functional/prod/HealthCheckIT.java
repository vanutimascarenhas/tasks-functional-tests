package br.ce.wcaquino.tasks.functional.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {
	@Test
	public void healthCheck() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
//		options.addArguments(String.format("--remote-debugging-port=%s", valorGerado));
		options.setHeadless(true);
//		WebDriver driver = new ChromeDriver(options);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		
		try {
			driver.navigate().to("http://192.168.72.130:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
			driver.quit();
		} finally {
		}
	}
}
