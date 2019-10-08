package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert; 

public class PageLogin {

	private WebDriver driver;
	
	private By userInput;
	private By passwordInput;
	private By loginButton;
	private By labelInicioSesion;
	
	public PageLogin(WebDriver driver) {
		this.driver = driver;
		
		 userInput = By.id("username");
		 passwordInput = By.id("password");
		 loginButton  = By.id("kc-login");
		 labelInicioSesion = By.id("kc-header-wrapper");
	}
	
	public void makeLogin(String user, String pass) {
		driver.findElement(userInput).sendKeys(user);
		driver.findElement(passwordInput).sendKeys(pass);
		driver.findElement(loginButton).click(); 
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.findElement(labelInicioSesion).getText().contains("Inicio de Sesión"),
				"Falló el ingreso a la pantalla de Login");
	}
	
}
