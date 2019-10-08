package pages.diagnostico;
 
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import helpers.Helper; 

public class DEPageCapturaInspeccion {
	
	private WebDriver driver;
	private Helper helper;
	// Page elements
	private By radioReporte;
	private By inputNoOrden;
	private By inputComentarios;
	private By dropdownPersonal;
	private By fileChooserInspeccion;
	private By formInspeccion;
	private By successTitle;
	private By buttonSwalAceptar;
	
	public DEPageCapturaInspeccion(WebDriver driver) {
		this.driver = driver;
		
		radioReporte = By.id("rep");
		inputNoOrden = By.id("numero_orden");
		inputComentarios = By.id("comentarios");
		dropdownPersonal = By.id("personal");
		fileChooserInspeccion = By.id("inspeccion");
		formInspeccion = By.id("submit_form3");
		successTitle = By.xpath("/html/body/div[4]/h2");
		buttonSwalAceptar = By.xpath("/html/body/div[4]/div[7]/div/button");
		helper = new Helper(driver);
	}
	
	public void crearReporte(String numOrden) {
		Actions actions = new Actions(driver);
		String filePath = "C:/dev/SELENIUM_TEST/files/insp.pdf";
		
		actions.moveToElement(driver.findElement(radioReporte)).click().build().perform();
		
		driver.findElement(inputNoOrden).sendKeys(numOrden);
		driver.findElement(inputNoOrden).sendKeys(Keys.TAB);
		
		Select selectPersonal = new Select(driver.findElement(dropdownPersonal));
		int optionsSize = selectPersonal.getOptions().size();
		if (optionsSize > 0) {
			Random random = new Random();
			int selectIndex = random.nextInt(optionsSize);
			selectPersonal.selectByIndex(selectIndex);
		}
		
		String comentario = "SELENIUM TEST (Inspección) " + helper.getCurrentDay() + "/";
		comentario += helper.getCurrentMonth() + " -> " + helper.getCurrentTime();
		
		driver.findElement(inputComentarios).sendKeys(comentario); 
		driver.findElement(fileChooserInspeccion).sendKeys(filePath);
		helper.sleepSeconds(1);
		
		driver.findElement(formInspeccion).submit();
		
	}
	
	public void assertUpload() {
		Assert.assertTrue(driver.findElement(successTitle).getText().contains("Inspección Creada"),
				"Error al subir archivo de inspección");
		driver.findElement(buttonSwalAceptar).click();
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("/diagnostico/capturar-inspeccion"),
				"No se pudo acceder a la pantalla de captura de inspecciones");
	} 

}
