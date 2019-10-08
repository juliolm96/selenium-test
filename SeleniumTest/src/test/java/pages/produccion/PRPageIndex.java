package pages.produccion;
 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import helpers.Constants;

public class PRPageIndex {
	
	private WebDriver driver;
	// Page Elements 
	public PRPageIndex(WebDriver driver) {
		this.driver = driver; 
	}
	
	public void goToAsignarDiasOrden() {
		driver.navigate().to(Constants.BASE_URL+"/produccion/plazos/capturar-fecha-estimada-entrega"); 
	}
	
	public void assertPage() {  
		Assert.assertTrue(driver.getCurrentUrl().contains("/produccion/index"),"No se pudo acceder al "
				+ "index del modulo de producción");
	}

}
