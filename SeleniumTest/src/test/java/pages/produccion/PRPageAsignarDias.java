package pages.produccion;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class PRPageAsignarDias {

	private WebDriver driver;
	// Page Elements
	private By radioOrdenes;
	private By inputNoOrden;
	private By inputDias;
	private By formulario;
	
	public PRPageAsignarDias(WebDriver driver) {
		this.driver = driver;
		
		radioOrdenes = By.id("ordenes");
		inputNoOrden = By.id("inputNumeroOrden");
		inputDias = By.id("diasUsuario");
		formulario = By.id("submit_form3");
	}
	
	public void asignarDiasOrden(String numOrden) {
		Actions actions = new Actions(driver);
		
		actions.moveToElement(driver.findElement(radioOrdenes)).click().build().perform();
		driver.findElement(inputNoOrden).sendKeys(numOrden);
		driver.findElement(inputNoOrden).sendKeys(Keys.TAB);
		
		Random random = new Random();
	    int diasRandom = random.nextInt(120); 
	    diasRandom++;
	    
	    driver.findElement(inputDias).sendKeys(Integer.toString(diasRandom));
	    driver.findElement(formulario).submit();	
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("/produccion/plazos/capturar-fecha-estimada-entrega"),
				"No se pudo acceder a la pantalla de captura de dias estimados de entrega de ordenes");
	}
}
