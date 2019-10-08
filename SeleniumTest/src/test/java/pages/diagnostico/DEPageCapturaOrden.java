package pages.diagnostico;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import helpers.Constants;
import helpers.Helper;

public class DEPageCapturaOrden {

	private WebDriver driver;
	// Page elements
	private By titleLabel;
	private By areasDropdown;
	private By fechaIngresoInput;
	private By checkboxPrecotizacion;
	private By textareaDescripcion; 
	private By marcasDropdown;
	private By modelosDropdown;
	private By buttonGuardar;
	private By spanNumOrden;
	private By buttonSWALConfirm;
	
	public DEPageCapturaOrden(WebDriver driver) {
		this.driver = driver;
		
		titleLabel = By.id("spanTitulo");
		areasDropdown = By.id("areasfree");
		fechaIngresoInput = By.id("fecha_ingreso"); 
		textareaDescripcion = By.id("desc_orden"); 
		marcasDropdown = By.id("marca");
		modelosDropdown = By.id("modelo");
		checkboxPrecotizacion = By.id("precotizacion");
		buttonGuardar = By.xpath("//*[@id=\"submit_form3\"]/div[2]/div/button");
	}
	
	public String createOrder() {
		Helper helper = new Helper(driver);
		Actions actions = new Actions(driver);
		
		Select areasSelect = new Select(driver.findElement(areasDropdown));
		int areasSize = areasSelect.getOptions().size();
		if (areasSize > 1) {
			Random random = new Random();
		    int randomNumber = random.nextInt(areasSize); 
		    
		    areasSelect.selectByIndex(randomNumber);
		}
		
		String day = helper.getCurrentDay();
		String month = helper.getCurrentMonth();
		String year = helper.getCurrentYear();
		String time = helper.getCurrentTime();
		
		driver.findElement(fechaIngresoInput).sendKeys(day); 
		driver.findElement(fechaIngresoInput).sendKeys(month); 
		driver.findElement(fechaIngresoInput).sendKeys(year);

		driver.findElement(textareaDescripcion).sendKeys("Orden TEST selenium: "+day+"/"+month+" -> " + time);	
		 
		actions.moveToElement(driver.findElement(checkboxPrecotizacion)).click().build().perform();  
		
		helper.sendKeysFromJavascript(Constants.NUM_CLIENTE, "numcliente");
		helper.sendKeysFromJavascript(Constants.NUM_GIRO, "numgiro"); 
		helper.executeJavascriptCommand("document.getElementById('numcliente').onchange();");

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		Select modelosSelect = new Select(driver.findElement(modelosDropdown));
		int modelosSize = modelosSelect.getOptions().size();
		if (areasSize > 0) {
			Random random = new Random();
		    int randomNumber = random.nextInt(modelosSize); 
		    
		    modelosSelect.selectByIndex(randomNumber);
		}
		
		Select marcasSelect = new Select(driver.findElement(marcasDropdown));
		int marcasSize = marcasSelect.getOptions().size();
		if (marcasSize > 0) {
			Random random = new Random();
		    int randomNumber = random.nextInt(marcasSize); 
		    
		    marcasSelect.selectByIndex(randomNumber);
		}
		 
		driver.findElement(buttonGuardar).click();
		
		helper.sleepSeconds(4);
		
		spanNumOrden = By.xpath("/html/body/div[4]/p/p/strong[2]");
		String numOrden = driver.findElement(spanNumOrden).getText();	
		
		buttonSWALConfirm = By.xpath("/html/body/div[4]/div[7]/div/button");
		driver.findElement(buttonSWALConfirm).click();
		
		return numOrden;
	}
	
	public void assertPage() { 
		Assert.assertTrue(driver.findElement(titleLabel).getText().contains("CAPTURA ORDENES PLANTA"),
				"No se pudo acceder a la pantalla de captura de ordenes de trabajo");
	}
}
