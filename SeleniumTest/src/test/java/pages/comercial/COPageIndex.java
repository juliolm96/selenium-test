package pages.comercial;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import helpers.Constants;

public class COPageIndex {
	
	private WebDriver driver;
	
	public COPageIndex(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToPageCotizaciones() {
		driver.navigate().to(Constants.BASE_URL+"/comercial/cotizaciones/capturar-cotizacion");
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("/comercial/index"), 
				"Fall� el ingreso al index del modulo comercial");
	}

}
