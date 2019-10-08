package pages.diagnostico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import helpers.Constants;

public class DEPageIndex {

	private WebDriver driver;
	// Elements of DiagnosticoMainPage
	private By goToDiagButton; 
	
	public DEPageIndex(WebDriver driver) {
		this.driver = driver;
		
		goToDiagButton = By.xpath("/html/body/div[1]/div[1]/div/ul/li[3]/a"); 
	}
	
	public void goToCreateOrders() {
		driver.navigate().to(Constants.BASE_URL+"/general/ordenes/capturar-orden"); 
	}

	public void goToCapturarInspeccion() {
		driver.navigate().to(Constants.BASE_URL+"/diagnostico/capturar-inspeccion"); 
	}
	
	public void goToCapturarRecomendacion() {
		driver.navigate().to(Constants.BASE_URL+"/diagnostico/capturar-diagnostico");
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.findElement(goToDiagButton).getAttribute("href").contains("/diagnostico/index"),
				"No se pudo acceder al index del modulo de diagnóstico.");
	} 
	
}
