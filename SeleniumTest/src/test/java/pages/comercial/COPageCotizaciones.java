package pages.comercial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class COPageCotizaciones {
	
	private WebDriver driver;
	// Page Elements
	private By inputNoOrden;
	
	public COPageCotizaciones(WebDriver driver) {
		this.driver = driver;
		inputNoOrden = By.id("numero_precotizacion");
	}
	
	public void crearCotización(String numOrden) {
		// TODO: Completar este segmento
		
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("/comercial/cotizaciones/capturar-cotizacion"),
				"Falló el ingreso a la pantalla de captura de cotizaciones");
	}

}
