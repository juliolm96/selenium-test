package pages.diagnostico;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import helpers.Helper;

public class DEPageCapturarRecomendacion {
	
	private WebDriver driver; 
	private Helper helper;
	// Page Elements
	private By inputNoOrden;
	private By dropdownPersonal;
	private By formCrearDiag;
	private By swalButtonConfirm;
	private By buttonImportarRecomendaciones;
	private By buttonGuardarRecomendaciones;
	private By fileChooserRecomendaciones;
	
	public DEPageCapturarRecomendacion(WebDriver driver) {
		this.driver = driver;
		inputNoOrden = By.id("numero_orden");
		dropdownPersonal = By.id("personal");
		formCrearDiag = By.id("submit_form3");
		swalButtonConfirm = By.cssSelector(".confirm.btn.btn-lg.btn-primary");
		buttonImportarRecomendaciones =  By.id("btnImportar");
		buttonGuardarRecomendaciones = By.id("create");
		fileChooserRecomendaciones = By.id("archivo");
		helper = new Helper(driver);
	}
	
	public void capturarRecomendacion(String numOrden) {
		driver.findElement(inputNoOrden).sendKeys(numOrden);
		driver.findElement(dropdownPersonal).sendKeys(Keys.TAB);
		
		Select selectPersonal = new Select(driver.findElement(dropdownPersonal));
		int personalSize = selectPersonal.getOptions().size();
		if (personalSize > 0) {
			Random random = new Random();
			int indexPersonal = random.nextInt(personalSize);
			
			selectPersonal.selectByIndex(indexPersonal);
		}
		
		driver.findElement(formCrearDiag).submit();
		helper.sleepSeconds(2);
	}
	
	public void crearDetallesRecomendacion() {
		String filePath = "C:/dev/SELENIUM_TEST/files/reco.txt";
		
		driver.findElement(buttonImportarRecomendaciones).click(); 
		driver.findElement(fileChooserRecomendaciones).sendKeys(filePath);
		driver.findElement(buttonGuardarRecomendaciones).click();
		helper.sleepSeconds(4);
		
	}
	
	public void assertPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("/diagnostico/capturar-diagnostico"),
				"No se pudo acceder a la pantalla de captura de recomendaciones");
	}
	
	public void assertCreacionRecomendacion() {
	
		boolean creacionExitosa = false;
		try {
			driver.findElement(swalButtonConfirm).click();
			creacionExitosa = true;
		} catch (Exception e) {
			e.printStackTrace();
			creacionExitosa = false;
		}
			Assert.assertTrue(creacionExitosa, "Error al generar recomendación. Paso 1");
	}

}
