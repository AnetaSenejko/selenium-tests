package com.anetasenejko.selenium.clicktrans.register;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class RegisterFormHappyPath {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {        
    	System.setProperty("webdriver.chrome.driver", "chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After    
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void shouldRegisterNewUser() throws Exception {
    	driver.get("https://dev-1.clicktrans.pl/register-test/courier");
    	fillFieldById("user_register_company_name","aneta-company");
    	fillFieldById("user_register_email","aneta-company@aneta.pl");
    	fillFieldById("user_register_name","Aneta Senejko");
    	fillFieldById("user_register_phone","696432053");
    	fillSelect("user_register_phoneCode",6);
    	fillFieldById("user_register_plainPassword","anetasenejko");
    	clickOnField("user_register_settings_agreementRegulations");
    	clickOnField("user_register_settings_agreementPersonalData");
    	clickOnField("user_register_submit");
    	//OK - some registration logic is mocked
    	assertEquals(driver.findElement(By.cssSelector(".success")).getText(), 
    			"OK - some registration logic is mocked");
    	
    }
    
    @Test
    public void shouldRegister() throws Exception {
    	driver.get("https://dev-1.clicktrans.pl/register-test/courier");
    	driver.findElement(By.id("user_register_company_name")).sendKeys("aneta-company");    	
    	driver.findElement(By.id("user_register_email")).sendKeys("aneta-company@aneta.pl");
    	
    	driver.findElement(By.id("user_register_name")).sendKeys("Aneta Senejko");
    	driver.findElement(By.id("user_register_phone")).sendKeys("696432053");
    	new Select(driver.findElement(By.id("user_register_phoneCode"))).selectByIndex(6);
    	driver.findElement(By.id("user_register_plainPassword")).sendKeys("anetasenejko");
    	driver.findElement(By.id("user_register_settings_agreementRegulations")).click();
    	driver.findElement(By.id("user_register_settings_agreementPersonalData")).click();
    	driver.findElement(By.id("user_register_submit")).click();
    	assertEquals(driver.findElement(By.cssSelector(".success")).getText(), 
    			"OK - some registration logic is mocked");
    }

    @Test
    public void shouldFailOnWrongMail() throws Exception {
    	driver.get("https://dev-1.clicktrans.pl/register-test/courier");
    	driver.findElement(By.id("user_register_company_name")).sendKeys("aneta-company");    	
    	driver.findElement(By.id("user_register_email")).sendKeys("aneta-company@.pl");
    	
    	driver.findElement(By.id("user_register_name")).sendKeys("Aneta Senejko");
    	driver.findElement(By.id("user_register_phone")).sendKeys("696432053");
    	new Select(driver.findElement(By.id("user_register_phoneCode"))).selectByIndex(6);
    	driver.findElement(By.id("user_register_plainPassword")).sendKeys("anetasenejko");
    	driver.findElement(By.id("user_register_settings_agreementRegulations")).click();
    	driver.findElement(By.id("user_register_settings_agreementPersonalData")).click();
    	driver.findElement(By.id("user_register_submit")).click();
    	assertEquals(driver.findElement(By.cssSelector(".negative")).getText(), 
    			"Error");
    }
    
    private void fillFieldById(String id, String value) {
        WebElement textBox = driver.findElement(By.id(id));
        textBox.sendKeys(value);    	
    }
    
    private void fillSelect(String id, int idx) {
        Select selectComponent = new Select(driver.findElement(By.id(id)));
        selectComponent.selectByIndex(idx);
    }
    
    private void clickOnField(String id) {
        WebElement buttonField = driver.findElement(By.id(id));
        buttonField.click();    	    	
    }
}
