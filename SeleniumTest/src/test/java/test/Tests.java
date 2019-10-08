package test;
 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import helpers.Constants;
import helpers.Helper;
import pages.comercial.COPageCotizaciones;
import pages.comercial.COPageIndex;
import pages.diagnostico.DEPageCapturaInspeccion;
import pages.diagnostico.DEPageCapturaOrden;
import pages.diagnostico.DEPageCapturarRecomendacion;
import pages.diagnostico.DEPageIndex;
import pages.produccion.PRPageAsignarDias;
import pages.produccion.PRPageIndex; 

public class Tests {
	
	private WebDriver driver;
	private Helper helper;
	private final static boolean GENERAL_ENABLE_TEST = true;
	// Flow Variables
	private String numOrden = "012/19"; 
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://localhost:8282"); 
		helper = new Helper(driver);
		helper.sleepSeconds(1);
	}

	@Test(priority=1, enabled=GENERAL_ENABLE_TEST)
	public void createOT() { 

		helper.loginAndSelectModule("083", "083", "diagnostico");
		
		// Ir hacia la opción de crear ordenes desde la pagina principal de diagnostico.
		DEPageIndex diagMainPage = new DEPageIndex(driver);
		diagMainPage.assertPage();
		diagMainPage.goToCreateOrders();
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		
		// Crear una orden desde la página correspondiente.
		DEPageCapturaOrden pageCapturaOrden = new DEPageCapturaOrden(driver);
		pageCapturaOrden.assertPage();
		numOrden = pageCapturaOrden.createOrder();

		// Esperar 2 segundos a que esté creada la orden para que se habilite el botón de logout.
		helper.sleepSeconds(2);
		helper.assertLogOut();
	}
	
	@Test(priority=2, dependsOnMethods="createOT", enabled=GENERAL_ENABLE_TEST)
	@DataProvider(parallel=true)
	public void generarReporteInspeccion() {
		// Iniciar sesión como "Alfredo Ramírez" para diagnostico
		helper.loginAndSelectModule("083", "083", "diagnostico");
		
		// Ir hacia la opción de capturar inspecciones desde la pagina principal de diagnostico
		DEPageIndex diagMainPage = new DEPageIndex(driver);
		diagMainPage.assertPage();
		diagMainPage.goToCapturarInspeccion();
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		
		// Crear una inspacción desde la página correspondiente
		DEPageCapturaInspeccion capturaInspeccion = new DEPageCapturaInspeccion(driver);
		capturaInspeccion.assertPage();
		capturaInspeccion.crearReporte(numOrden);
		capturaInspeccion.assertUpload();

	}
	
	@Test(priority=2,  dependsOnMethods="createOT", enabled=GENERAL_ENABLE_TEST)
	@DataProvider(parallel=true)
	public void crearRecomendacionesDiagnostico() {
		// Inicio de sesión como "Alfredo Ramírez" para diagnóstico
		helper.loginAndSelectModule("083", "083", "diagnostico");
		
		DEPageIndex mainPage = new DEPageIndex(driver);
		mainPage.assertPage();
		mainPage.goToCapturarRecomendacion();
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		
		DEPageCapturarRecomendacion pageCapturaRecomendacion = new DEPageCapturarRecomendacion(driver);
		pageCapturaRecomendacion.assertPage();
		pageCapturaRecomendacion.capturarRecomendacion(numOrden);
		pageCapturaRecomendacion.assertCreacionRecomendacion();
		
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		
		pageCapturaRecomendacion.crearDetallesRecomendacion(); 
		helper.sleepSeconds(2);
		helper.assertLogOut();
	}
	
	
	@Test(priority=2, dependsOnMethods="createOT", enabled=GENERAL_ENABLE_TEST)
	@DataProvider(parallel=true)
	public void asignarDiasOT() {
		
		// Inicio de sesión cómo "Arturo Ramos" para produción
		helper.loginAndSelectModule("020", "020", "produccion");
		
		// Ir hacia la opción de crear ordenes desde la pagina principal de diagnostico.
		PRPageIndex mainPage = new PRPageIndex(driver);
		mainPage.assertPage(); 
		mainPage.goToAsignarDiasOrden();
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		
		PRPageAsignarDias pageAsignarDias = new PRPageAsignarDias(driver);
		pageAsignarDias.assertPage();
		pageAsignarDias.asignarDiasOrden(numOrden); 
		
	}
	
	@Test(priority=3, dependsOnMethods="asignarDiasOT", enabled=false)
	public void crearCotizacion() {
		// Inicio de sesión como "Noemi" para comercial
		helper.loginAndSelectModule("258", "258", "comercial");
		
		// Ir a la opción de crear cotizaciones desde la página principal de comercial
		COPageIndex mainPage = new COPageIndex(driver);
		mainPage.assertPage();
		mainPage.goToPageCotizaciones();
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		COPageCotizaciones pageCotizaciones = new COPageCotizaciones(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if (!result.isSuccess()) {
			helper.takeScreenShot(result.getMethod().getMethodName());
		}
		
		helper.sleepSeconds(1);
		driver.close();
	}

}
