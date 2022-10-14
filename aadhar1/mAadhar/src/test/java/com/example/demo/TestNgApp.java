package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNgApp {
  WebDriver driver;
  @Test
  public void f() {
	    driver.get("http://localhost:4200/adminLogin");
		driver.findElement(By.xpath("/html/body/app-root/app-admin-login/div/div/div/div/form/div[1]/input")).sendKeys("viratkohli");
		driver.findElement(By.xpath("/html/body/app-root/app-admin-login/div/div/div/div/form/div[2]/input")).sendKeys("virat@123");
		driver.findElement(By.xpath("/html/body/app-root/app-admin-login/div/div/div/div/form/div[3]/button")).submit();
  }
  
  @BeforeMethod
	public void beforeMethod() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\syama\\\\Downloads\\\\chromedriver_win32\\\\chromedriver.exe");
		driver= new ChromeDriver();
	}
	@AfterMethod
	public void afterMethod() {
		//driver.close();
		driver=null;
	}
}
