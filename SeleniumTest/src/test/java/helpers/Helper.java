package helpers;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pages.MainPage;
import pages.PageLogin; 

public class Helper {
	
	private WebDriver driver;
	
	public Helper(WebDriver driver) {
		this.driver = driver;
	}
	
	public void sleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	 
    public String getCurrentDay () { 
    	
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
 
        //Get Current Day as a number
        int currentDayInt = calendar.get(Calendar.DAY_OF_MONTH); 
        
        String currentDayStr = "";
        
        //Integer to String Conversion
        if (currentDayInt < 10) {
        	currentDayStr = "0"+Integer.toString(currentDayInt);
        } else {
        	currentDayStr = Integer.toString(currentDayInt); 
        } 
        
        return currentDayStr;
 
    }
    
    public String getCurrentMonth() {
    	
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
 
        //Get Current Month as a Integer
        int currentMonthInt = calendar.get(Calendar.MONTH);
        currentMonthInt++;
        
        //Integer to String Conversion
        String currentMonthStr = Integer.toString(currentMonthInt); 
        
        return currentMonthStr;
    }

    public String getCurrentYear() {
    	
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
 
        //Get Current Year as a Integer
        int currentYearInt = calendar.get(Calendar.YEAR); 
        
        //Integer to String Conversion
        String currentYearStr = Integer.toString(currentYearInt); 
        System.err.println(currentYearStr);
        return currentYearStr;
    }
    
    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        
        //Get Current Time as a Integers
        int currentHourInt = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinuteInt = calendar.get(Calendar.MINUTE);
        
        //Integers to String Conversion
        String currentHourStr = "";
        String currentMinuteStr = "";
        if (currentHourInt < 10) {
        	currentHourStr = "0" + currentHourInt;
        } else {
        	currentHourStr = Integer.toString(currentHourInt);
        }
        
        if (currentMinuteInt < 10) {
        	currentMinuteStr = "0" + currentMinuteInt;
        } else {
        	currentMinuteStr = Integer.toString(currentMinuteInt);
        }
        
        return currentHourStr + ":" + currentMinuteStr;
    }
    
    public void sendKeysFromJavascript(String keys, String elementId) { 
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
    	jse.executeScript(" tempComponent = document.getElementById('"+elementId+"');");
    	jse.executeScript(" tempComponent.value='" + keys + "';"); 
    }
    
    public void executeJavascriptCommand(String command) {
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
    	jse.executeScript(command);
    }
    
    public void loginAndSelectModule(String user, String password, String module) {
		
		// Inicio de session con Diagnostico
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.makeLogin(user, password);
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		
		// Selección de módulo DIAGNOSTICO
		MainPage mainPage = new MainPage(driver);
		mainPage.moduleSelect(module); 
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
    }
    
    public void assertLogOut() {
    	MainPage mainPage = new MainPage(driver);
		mainPage.logOut();
		
		PageLogin pageLogin = new PageLogin(driver);
		// Verificar el correcto cierre de sesion.
		driver.manage().timeouts().implicitlyWait(Constants.MAX_WAIT, TimeUnit.SECONDS);
		pageLogin.assertPage();	
    }
    
    public void takeScreenShot(String fileName) { 
    	File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try { 
    		FileUtils.copyFile(screenshot, new File("screenshots/"+fileName+"_"+getCurrentTime()+".png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    }
    
}
