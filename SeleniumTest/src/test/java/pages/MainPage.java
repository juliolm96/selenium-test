package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MainPage {
	
	private WebDriver driver; 
	// Main Page Button elements
	private By mainPageButton; 
	private String mainPageButtonText; 
	private By modulesButton; 
	private By diagnosticoLink;
	private By comercialLink;
	private By produccionLink;
	private By userButton;
	private By logOutButton;
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		
		mainPageButton = By.xpath("/html/body/div[1]/div[1]/div/ul/li/a/span");
		mainPageButtonText = "Página principal";
		modulesButton = By.xpath("/html/body/header/div[1]/div/div[2]/div/button");
		diagnosticoLink = By.id("moduloDiagnostico"); 
		comercialLink = By.id("moduloComercial");
		produccionLink = By.id("moduloProduccion"); 
		userButton = By.xpath("/html/body/header/div[1]/div/div[2]/div/div/ul/li[2]/a");
		logOutButton = By.xpath("/html/body/header/div[1]/div/div[2]/div/div/ul/li[2]/ul/li[2]/a");
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.findElement(mainPageButton).getText().contains(mainPageButtonText),
				"No se pudo acceder a la pantalla principal del sistema");
	}
	
	public void moduleSelect(String moduleName) {
		By module;
		
		if (moduleName.equalsIgnoreCase("comercial")) {
			module = comercialLink;
		} else if (moduleName.equalsIgnoreCase("diagnostico")) {
			module = diagnosticoLink;
		} else if (moduleName.equalsIgnoreCase("produccion")) {
			module = produccionLink;
		} else {
			module = By.xpath("");
		}
		
		driver.findElement(modulesButton).click();
		driver.findElement(module).click();
	}
	
	public void logOut() {
		driver.findElement(userButton).click();
		driver.findElement(logOutButton).click();
	}

}
